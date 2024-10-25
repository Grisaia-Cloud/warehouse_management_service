package com.example.demo.repository;

import com.example.demo.constants.RepositoryConstants;
import com.example.demo.model.Merchandise;
import com.example.demo.requestBodyModel.NewMerchandiseRequestBody;
import com.example.demo.requestBodyModel.UpdateMerchandiseRequestBody;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;

import java.util.Map;

import static com.example.demo.config.DynamoDBConfig.MERCHANDISE_TABLE_SCHEMA;

@Repository
public class MerchandiseRepository {

    @Autowired
    @Qualifier("merchandiseDynamoDB")
    private DynamoDbEnhancedClient client;

    @Autowired
    private Logger logger;

    public Merchandise getMerchandiseInfoBySku(String sku) {
        try {
            Key key = Key.builder().partitionValue(sku).build();
            return client.table("merchandise", MERCHANDISE_TABLE_SCHEMA).getItem(key);
        } catch (Exception e) {
            logger.error(String.format(RepositoryConstants.MERCHANDISE_GET_SINGLE_FAIL_MESSAGE, sku));
            throw e;
        }
    }

    public Merchandise createNewMerchandise(NewMerchandiseRequestBody requestBody) {
        Merchandise record = new Merchandise(requestBody);
        PutItemEnhancedRequest<Merchandise> putItemEnhancedRequest = PutItemEnhancedRequest.builder(Merchandise.class)
                .item(record)
                .conditionExpression(
                        Expression.builder()
                                .expression("attribute_not_exists(#sku)")
                                .expressionNames(Map.of("#sku", "sku"))
                                .build()
                ).build();
        try {
            client.table("merchandise", MERCHANDISE_TABLE_SCHEMA).putItem(putItemEnhancedRequest);
            logger.info(String.format(RepositoryConstants.MERCHANDISE_CREATE_SUCCESSFUL_MESSAGE,requestBody.getSku()));
            return record;
        } catch (Exception e){
            logger.error(String.format(RepositoryConstants.MERCHANDISE_CREATE_FAIL_MESSAGE,requestBody.toString()));
            throw e;
        }
    }

    public PageIterable<Merchandise> getAllMerchandise() {
        try {
            PageIterable<Merchandise> merchandiseList = client.table("merchandise", MERCHANDISE_TABLE_SCHEMA).scan();
            logger.info(RepositoryConstants.MERCHANDISE_GET_ALL_SUCCESSFUL_MESSAGE);
            return merchandiseList;
        } catch (Exception e) {
            logger.error(RepositoryConstants.MERCHANDISE_GET_ALL_FAIL_MESSAGE);
            throw e;
        }
    }

    public void deleteMerchandiseBySku(String sku) {
        Key key = Key.builder().partitionValue(sku).build();
        try {
            client.table("merchandise", MERCHANDISE_TABLE_SCHEMA).deleteItem(key);
            logger.error(String.format(RepositoryConstants.MERCHANDISE_DELETE_SUCCESSFUL_MESSAGE, sku));
        } catch (Exception e){
            logger.error(String.format(RepositoryConstants.MERCHANDISE_DELETE_FAIL_MESSAGE, sku));
            throw e;
        }
    }

    public Merchandise updateMerchandiseBySku(String sku, UpdateMerchandiseRequestBody requestBody, Merchandise currentMerchandise) {
        Merchandise record = new Merchandise(sku, requestBody, currentMerchandise);
        try {
            client.table("merchandise", MERCHANDISE_TABLE_SCHEMA).updateItem(record);
            logger.info(String.format(RepositoryConstants.MERCHANDISE_UPDATE_SUCCESSFUL_MESSAGE, sku));
            return record;
        } catch (Exception e) {
            logger.error(String.format(RepositoryConstants.MERCHANDISE_UPDATE_FAIL_MESSAGE, sku));
            throw e;
        }
    }
}
