package com.example.demo.repository;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.example.demo.constants.RepositoryConstants;
import com.example.demo.model.Merchandise;
import com.example.demo.requestBodyModel.NewMerchandiseRequestBody;
import com.example.demo.requestBodyModel.UpdateMerchandiseRequestBody;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MerchandiseRepository {

    @Autowired
    @Qualifier("merchandiseDynamoDB")
    private DynamoDBMapper client;

    @Autowired
    private Logger logger;

    public Merchandise getMerchandiseInfoBySku(String sku) {
        try {
            return client.load(Merchandise.class, sku);
        } catch (AmazonClientException e) {
            logger.error(String.format(RepositoryConstants.MERCHANDISE_GET_SINGLE_FAIL_MESSAGE, sku));
            throw new RuntimeException();
        }
    }

    public Merchandise createNewMerchandise(NewMerchandiseRequestBody requestBody) {
        Merchandise record = new Merchandise(requestBody);
        try {
            client.save(record);
            logger.info(String.format(RepositoryConstants.MERCHANDISE_CREATE_SUCCESSFUL_MESSAGE,requestBody.getSku()));
            return record;
        } catch (AmazonClientException e){
            logger.error(String.format(RepositoryConstants.MERCHANDISE_CREATE_FAIL_MESSAGE,requestBody.toString()));
            throw new RuntimeException();
        }
    }

    public List<Merchandise> getAllMerchandise() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        try {
            List<Merchandise> merchandiseList = client.scan(Merchandise.class, scanExpression);
            logger.info(RepositoryConstants.MERCHANDISE_GET_ALL_SUCCESSFUL_MESSAGE);
            return merchandiseList;
        } catch (AmazonClientException e) {
            logger.error(RepositoryConstants.MERCHANDISE_GET_ALL_FAIL_MESSAGE);
            throw new RuntimeException();
        }
    }

    public void deleteMerchandiseBySku(String sku) {
        try {
            Merchandise itemToDelete = new Merchandise();
            itemToDelete.setSku(sku);
            client.delete(itemToDelete);
            logger.error(String.format(RepositoryConstants.MERCHANDISE_DELETE_SUCCESSFUL_MESSAGE, sku));
        } catch (AmazonClientException e){
            logger.error(String.format(RepositoryConstants.MERCHANDISE_DELETE_FAIL_MESSAGE, sku));
            throw new RuntimeException();
        }
    }

    public Merchandise updateMerchandiseBySku(String sku, UpdateMerchandiseRequestBody requestBody, Merchandise currentMerchandise) {
        Merchandise record = new Merchandise(sku, requestBody, currentMerchandise);
        try {
            client.save(record);
            logger.info(String.format(RepositoryConstants.MERCHANDISE_UPDATE_SUCCESSFUL_MESSAGE,sku));
            return record;
        } catch (AmazonClientException e) {
            logger.error(String.format(RepositoryConstants.MERCHANDISE_UPDATE_FAIL_MESSAGE, sku));
            throw new RuntimeException();
        }
    }
}
