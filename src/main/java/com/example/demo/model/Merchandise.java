package com.example.demo.model;

import com.example.demo.requestBodyModel.NewMerchandiseRequestBody;
import com.example.demo.requestBodyModel.UpdateMerchandiseRequestBody;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Merchandise {

    String merchandiseSku;

    String merchandiseName;

    Integer availableMerchandiseCount;

    String description;

    public Merchandise() {}

    public Merchandise(NewMerchandiseRequestBody requestBody) {
        this.merchandiseSku = requestBody.getSku();
        this.merchandiseName = requestBody.getName();
        this.availableMerchandiseCount = requestBody.getCount();
        this.description = requestBody.getDescription();
    }

    public Merchandise(String merchandiseSku, UpdateMerchandiseRequestBody requestBody, Merchandise currentMerchandise) {
        this.merchandiseSku = currentMerchandise.getMerchandiseSku();
        this.merchandiseName = requestBody.getMerchandiseName() != null ? requestBody.getMerchandiseName() : currentMerchandise.getMerchandiseName();
        this.availableMerchandiseCount = requestBody.getAvailableMerchandiseCount() != null ? requestBody.getAvailableMerchandiseCount() : currentMerchandise.getAvailableMerchandiseCount();
        this.description = requestBody.getDescription() != null ? requestBody.getDescription() : currentMerchandise.getDescription();
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("sku")
    public String getMerchandiseSku() {
        return merchandiseSku;
    }

    public void setMerchandiseSku(String merchandiseSku) {
        this.merchandiseSku = merchandiseSku;
    }

    @DynamoDbAttribute("name")
    public String getMerchandiseName() {
        return merchandiseName;
    }

    public void setMerchandiseName(String merchandiseName) {
        this.merchandiseName = merchandiseName;
    }

    @DynamoDbAttribute("count")
    public Integer getAvailableMerchandiseCount() {
        return availableMerchandiseCount;
    }

    public void setAvailableMerchandiseCount(Integer availableMerchandiseCount) {
        this.availableMerchandiseCount = availableMerchandiseCount;
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