package com.example.demo.repository;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutRequest;
import com.amazonaws.services.dynamodbv2.model.WriteRequest;
import com.example.demo.constants.RepositoryConstants;
import com.example.demo.model.Inventory;
import com.example.demo.requestBodyModel.NewInventoryRequestBody;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class InventoryRepository {
    @Autowired
    @Qualifier("inventoryDynamoDB")
    private DynamoDBMapper client;

    @Autowired
    private Logger logger;

    public List<Inventory> getFromInventory(String type, String region, String brand, Integer value, String status, String code, String order_number) {
        DynamoDBQueryExpression<Inventory> expression = new DynamoDBQueryExpression<Inventory>()
                .withKeyConditionExpression(buildKeyConditionExpression(type, region, brand, value, status, code))
                .withFilterExpression(buildFilterExpression(order_number))
                .withExpressionAttributeValues(
                        Map.of(
                                RepositoryConstants.TYPE_KEY_EXPRESSION, new AttributeValue().withS(type),
                                RepositoryConstants.REGION_KEY_EXPRESSION, new AttributeValue().withS(region),
                                RepositoryConstants.BRAND_KEY_EXPRESSION, new AttributeValue().withS(brand),
                                RepositoryConstants.VALUE_KEY_EXPRESSION, new AttributeValue().withN(String.valueOf(value)),
                                RepositoryConstants.STATUS_KEY_EXPRESSION, new AttributeValue().withS(status),
                                RepositoryConstants.CODE_KEY_EXPRESSION, new AttributeValue().withS(code),
                                RepositoryConstants.ORDER_NUMBER_ATTRIBUTE_EXPRESSION, new AttributeValue().withS(order_number)
                        )
                );
        logger.info(String.valueOf(expression));
        try {
            return client.query(Inventory.class, expression);
        } catch (AmazonClientException e) {
            logger.error(RepositoryConstants.INVENTORY_GET_ALL_FAIL_MESSAGE);
            throw new RuntimeException();
        }
    }

    public void addToInventory(List<NewInventoryRequestBody> items) {
//        try {
//            List<WriteRequest> writeRequests = new ArrayList<>();
//            for (NewInventoryRequestBody item: items) {
//                Inventory model = new Inventory(item);
//                WriteRequest writeRequest = new WriteRequest()
//                        .withPutRequest(new PutRequest()
//
//
//                        )
//            }
//        }
    }

    // combination sort key: china:Apple:100:unused
    private String buildKeyConditionExpression(String type, String region, String brand, Integer value,  String status, String code) {
        List<String> expression = new ArrayList<>();
        if (type != null) {
            expression.add(RepositoryConstants.TYPE_KEY_EXPRESSION);
        }
        if (region != null) {
            expression.add(RepositoryConstants.REGION_KEY_EXPRESSION);
        }
        if (brand != null) {
            expression.add(RepositoryConstants.BRAND_KEY_EXPRESSION);
        }
        if (value != null) {
            expression.add(RepositoryConstants.VALUE_KEY_EXPRESSION);
        }
        if (status != null) {
            expression.add(RepositoryConstants.STATUS_KEY_EXPRESSION);
        }
        if (code != null) {
            expression.add(RepositoryConstants.CODE_KEY_EXPRESSION);
        }
        return String.join(RepositoryConstants.DELIMINATOR, expression);
    }

    private String buildFilterExpression(String order_number) {
        List<String> expression = new ArrayList<>();
        if (order_number != null) {
           expression.add(RepositoryConstants.ORDER_NUMBER_ATTRIBUTE_EXPRESSION);
        }
        return String.join(RepositoryConstants.DELIMINATOR, expression);
    }
}
