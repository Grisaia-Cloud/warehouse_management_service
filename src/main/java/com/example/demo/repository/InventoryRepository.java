package com.example.demo.repository;

import com.example.demo.constants.RepositoryConstants;
import com.example.demo.model.Inventory;
import com.example.demo.requestBodyModel.UpdateInventoryRequestBody;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.List;
import java.util.Map;

import static com.example.demo.config.DynamoDBConfig.INVENTORY_TABLE_SCHEMA;

@Repository
public class InventoryRepository {
    @Autowired
    @Qualifier("inventoryDynamoDB")
    private DynamoDbEnhancedClient client;

    @Autowired
    private Logger logger;

    public PageIterable<Inventory> getInventoryItems(String type, String region, String brand, Integer value, String status, String code, String order_number, Integer count) {
        DynamoDbTable<Inventory> clientTable = client.table("inventory", INVENTORY_TABLE_SCHEMA);
        Key keyFrom = Key.builder()
                .partitionValue(type)
                .sortValue(Inventory.createStartInfoValue(region, brand, value, status, code))
                .build();
        Key keyTo = Key.builder()
                .partitionValue(type)
                .sortValue(Inventory.createEndInfoValue(region, brand, value, status, code))
                .build();
        QueryConditional queryCondition = QueryConditional.sortBetween(keyFrom, keyTo);

        String filterExpression = "#order_number = :order_number";
        Map<String, String> expressionNamesMap = Map.of("#order_number", "order_number");
        Map<String, AttributeValue> expressionValuesMap = Map.of(":order_number", AttributeValue.builder().s(order_number).build());
        Expression expression = Expression.builder()
                .expression(filterExpression)
                .expressionNames(expressionNamesMap)
                .expressionValues(expressionValuesMap)
                .build();

        QueryEnhancedRequest.Builder queryEnhancedRequestBuilder = QueryEnhancedRequest.builder()
                .queryConditional(queryCondition)
                .filterExpression(order_number != null ? expression : null);
        if (count != null) {
            queryEnhancedRequestBuilder.limit(count);
        }
        QueryEnhancedRequest queryEnhancedRequest = queryEnhancedRequestBuilder.build();

        logger.info(String.valueOf(queryEnhancedRequest));
        try {
            return clientTable.query(queryEnhancedRequest);
        } catch (Exception e) {
            logger.error(String.format(RepositoryConstants.INVENTORY_GET_FAIL_MESSAGE, type, region, brand, value.toString(), status, code, order_number));
            throw e;
        }
    }

    public PageIterable<Inventory> getAllInventoryItems() {
        try {
            PageIterable<Inventory> inventoryPageIterable = client.table("inventory", INVENTORY_TABLE_SCHEMA).scan();
            logger.info(RepositoryConstants.INVENTORY_GET_ALL_SUCCESSFUL_MESSAGE);
            return inventoryPageIterable;
        } catch (Exception e) {
            logger.error(RepositoryConstants.INVENTORY_GET_ALL_FAIL_MESSAGE);
            throw e;
        }
    }

    public void addInventoryItems(List<Inventory> items) {
        DynamoDbTable<Inventory> clientTable = client.table("inventory", INVENTORY_TABLE_SCHEMA);

        TransactWriteItemsEnhancedRequest.Builder transactionWriteItemsEnhancedRequestBuilder = TransactWriteItemsEnhancedRequest.builder();
        for (Inventory item : items) {
            transactionWriteItemsEnhancedRequestBuilder.addPutItem(
                    clientTable,
                    TransactPutItemEnhancedRequest.builder(Inventory.class)
                            .item(item)
                            .conditionExpression(
                                Expression.builder()
                                    .expression("attribute_not_exists(#type) AND attribute_not_exists(#info)")
                                    .expressionNames(Map.of("#type", "type", "#info", "info"))
                                    .build()
                            ).build()
            );
        }
        TransactWriteItemsEnhancedRequest transactWriteItemsEnhancedRequest = transactionWriteItemsEnhancedRequestBuilder.build();
        try {
            client.transactWriteItems(transactWriteItemsEnhancedRequest);
        } catch (TransactionCanceledException e) {
            logger.error(e.cancellationReasons().toString());
            throw e;
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public Inventory updateInventoryItem(UpdateInventoryRequestBody requestBody, String code) {
        DynamoDbTable<Inventory> clientTable = client.table("inventory", INVENTORY_TABLE_SCHEMA);

        QueryConditional queryConditional = QueryConditional
                .keyEqualTo(
                        Key.builder()
                        .partitionValue(code)
                                .build()
                );

        SdkIterable<Page<Inventory>> sdkIterable = clientTable.index("code").query(queryConditional);
        if (sdkIterable.iterator().next().items().size() != 1) {
            logger.error("duplicate code exist");
        }
        Inventory record = sdkIterable.iterator().next().items().iterator().next();
        Inventory new_record = new Inventory(requestBody, record);
        clientTable.putItem(new_record);
        logger.info("successfully added new record");
        if (requestBody.getStatus() != null) {
            clientTable.deleteItem(record);
            logger.info("successfully deleted old record");
        }
        return new_record;
    }
}
