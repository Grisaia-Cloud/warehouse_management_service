package com.example.demo.model;

import com.example.demo.enumeration.InventoryEnums;
import com.example.demo.requestBodyModel.NewInventoryRequestBody;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Type | Region | Brand | Value | Status | Code | Order#
// combination sort key: china:Apple:100:unused:code
@DynamoDbBean
public class Inventory {
    private String type;

    private String info;

    private String region;

    private String brand;

    private Integer value;

    private String status;

    private String code;

    private String order_number;

    public Inventory(){};

    public Inventory(NewInventoryRequestBody requestBody) {
        this.type = requestBody.getType().toString();
        this.region = requestBody.getRegion().toString();
        this.brand = requestBody.getRegion().toString();
        this.value = requestBody.getValue();
        this.status = requestBody.getStatus().toString();
        this.code = requestBody.getCode();
        this.order_number = requestBody.getOrder_number();
    }

    @DynamoDbPartitionKey
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @DynamoDbSortKey
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public static String createStartInfoValue(String region, String brand, Integer value, String status, String code) {
        List<String> res = new ArrayList<>();
        String defaultRegion = Arrays.stream(InventoryEnums.Region.values())
                .map(Enum::name)
                .min(String::compareTo)
                .orElse(null);
        String defaultBrand = Arrays.stream(InventoryEnums.Brand.values())
                .map(Enum::name)
                .min(String::compareTo)
                .orElse(null);
        String defaultValue = "0";
        String defaultStatus = Arrays.stream(InventoryEnums.Status.values())
                .map(Enum::name)
                .min(String::compareTo)
                .orElse(null);
        String defaultCode = "0-0";
        res.add(region != null ? region : defaultRegion);
        res.add(brand != null ? brand : defaultBrand);
        res.add(value != null ? value.toString() : defaultValue);
        res.add(status != null ? status : defaultStatus);
        res.add(code != null ? code : defaultCode);
        return String.join("#", res);
    }

    public static String createEndInfoValue(String region, String brand, Integer value, String status, String code) {
        List<String> res = new ArrayList<>();
        String defaultRegion = Arrays.stream(InventoryEnums.Region.values())
                .map(Enum::name)
                .max(String::compareTo)
                .orElse(null);
        String defaultBrand = Arrays.stream(InventoryEnums.Brand.values())
                .map(Enum::name)
                .max(String::compareTo)
                .orElse(null);
        String defaultValue = "0";
        String defaultStatus = Arrays.stream(InventoryEnums.Status.values())
                .map(Enum::name)
                .max(String::compareTo)
                .orElse(null);
        String defaultCode = "9999-9999";
        res.add(region != null ? region : defaultRegion);
        res.add(brand != null ? brand : defaultBrand);
        res.add(value != null ? value.toString() : defaultValue);
        res.add(status != null ? status : defaultStatus);
        res.add(code != null ? code : defaultCode);
        return String.join("#", res);
    }
//    aws dynamodb create-table \
//            --table-name inventory \
//            --attribute-definitions \
//    AttributeName=type,AttributeType=S \
//    AttributeName=info,AttributeType=S \
//            --key-schema \
//    AttributeName=type,KeyType=HASH \
//    AttributeName=info,KeyType=RANGE \
//            --provisioned-throughput \
//    ReadCapacityUnits=5,WriteCapacityUnits=5 \
//            --table-class STANDARD \
//            --endpoint-url http://localhost:8000
}
