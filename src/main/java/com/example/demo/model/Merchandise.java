package com.example.demo.model;

import com.example.demo.requestBodyModel.NewMerchandiseRequestBody;
import com.example.demo.requestBodyModel.UpdateMerchandiseRequestBody;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Merchandise {

    String sku;

    String name;

    Integer count;

    String description;

    public Merchandise() {}

    public Merchandise(NewMerchandiseRequestBody requestBody) {
        this.sku = requestBody.getSku();
        this.name = requestBody.getName();
        this.count = requestBody.getCount();
        this.description = requestBody.getDescription();
    }

    public Merchandise(String sku, UpdateMerchandiseRequestBody requestBody, Merchandise currentMerchandise) {
        this.sku = currentMerchandise.getSku();
        this.name = requestBody.getName() != null ? requestBody.getName() : currentMerchandise.getName();
        this.count = requestBody.getCount() != null ? requestBody.getCount() : currentMerchandise.getCount();
        this.description = requestBody.getDescription() != null ? requestBody.getDescription() : currentMerchandise.getDescription();
    }

    @DynamoDbPartitionKey
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @DynamoDbAttribute("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDbAttribute("count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @DynamoDbAttribute("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

//aws dynamodb create-table \
//        --table-name merchandise \
//        --attribute-definitions \
//AttributeName=sku,AttributeType=S \
//        --key-schema \
//AttributeName=sku,KeyType=HASH \
//        --provisioned-throughput \
//ReadCapacityUnits=5,WriteCapacityUnits=5 \
//        --table-class STANDARD \
//--endpoint-url http://localhost:8000