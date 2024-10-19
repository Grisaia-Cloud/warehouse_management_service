package com.example.demo.repository;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.example.demo.model.Merchandise;
import com.example.demo.requestBodyModel.NewMerchandiseRequestBody;
import com.example.demo.requestBodyModel.UpdateMerchandiseRequestBody;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class MerchandiseRepository {

    @Autowired
    private DynamoDBMapper client;

    @Autowired
    private Logger logger;

    public Optional<Merchandise> getMerchandiseInfoBySku(String sku) {
        return Optional.ofNullable(client.load(Merchandise.class, sku));
    }

    public Merchandise createNewMerchandise(NewMerchandiseRequestBody requestBody) {
        Merchandise record = new Merchandise(requestBody);
        try {
            client.save(record);
            logger.info("Successfully created new merchandise with SKU {} to merchandise DynamoDB Table", requestBody.getSku());
            return record;
        } catch (AmazonClientException e){
            logger.error("Exception occurred while attempting to save dynamoDB merchandise: {}", requestBody);
            throw new RuntimeException();
        }
    }

    public List<Merchandise> getAllMerchandise() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        try {
            List<Merchandise> merchandiseList = client.scan(Merchandise.class, scanExpression);
            return merchandiseList;
        } catch (AmazonClientException e) {
            logger.error("Exception occurred while attempting to scan dynamoDB merchandise");
            return Collections.emptyList();
        }
    }

    public void deleteMerchandiseBySku(String sku) {
        try {
            Merchandise itemToDelete = new Merchandise();
            itemToDelete.setSku(sku);
            client.delete(itemToDelete);
        } catch (AmazonClientException e){
            logger.error("Exception occurred while attempting to delete merchandise with sku {} from DynamoDB table: 'merchandise'", sku);
        }
    }

    public Merchandise updateMerchandiseBySku(String sku, UpdateMerchandiseRequestBody requestBody) {
        Merchandise record = new Merchandise(sku, requestBody);
        try {
            client.save(record);
            logger.info("Successfully updated new merchandise with SKU {} to merchandise DynamoDB Table", sku);
            return record;
        } catch (AmazonClientException e){
            logger.error("Exception occurred while attempting to update dynamoDB merchandise {}", sku);
            throw new RuntimeException();
        }
    }
}
