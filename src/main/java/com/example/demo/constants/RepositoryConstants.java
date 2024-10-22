package com.example.demo.constants;

public class RepositoryConstants {
    public static final String TYPE_EXPRESSION_ATTRIBUTE_NAME = ":type";
    public static final String REGION_EXPRESSION_ATTRIBUTE_NAME = ":region";
    public static final String BRAND_EXPRESSION_ATTRIBUTE_NAME = ":brand";
    public static final String VALUE_EXPRESSION_ATTRIBUTE_NAME = ":value";
    public static final String STAUTS_EXPRESSION_ATTRIBUTE_NAME = ":status";
    public static final String CODE_EXPRESSION_ATTRIBUTE_NAME = ":code";
    public static final String ORDER_NUMBER_EXPRESSION_ATTRIBUTE_NAME = ":order_number";

    public static final String DELIMINATOR = " AND ";
    public static final String TYPE_KEY_EXPRESSION = "info.type = :type";
    public static final String REGION_KEY_EXPRESSION = "info.region = :region";
    public static final String BRAND_KEY_EXPRESSION = "info.brand = :brand";
    public static final String VALUE_KEY_EXPRESSION = "info.value = :value";
    public static final String STATUS_KEY_EXPRESSION = "info.status = :status";
    public static final String CODE_KEY_EXPRESSION = "info.code = :code";
    public static final String ORDER_NUMBER_ATTRIBUTE_EXPRESSION = "order_number = :order_number";

    public static final String EMPTY_STRING = "";
    public static final String MERCHANDISE_MISSING_MERCHANDISE = "Merchandise with provided SKU %s does not exist";
    public static final String MERCHANDISE_GET_SINGLE_SUCCESSFUL_MESSAGE = "Successfully get merchandise %s from dynamoDB: merchandise";
    public static final String MERCHANDISE_GET_SINGLE_FAIL_MESSAGE = "Exception occurred while attempting to get merchandise %s from dynamoDB: merchandise";
    public static final String MERCHANDISE_GET_ALL_SUCCESSFUL_MESSAGE = "Successfully get all merchandises from dynamoDB: merchandise";
    public static final String MERCHANDISE_GET_ALL_FAIL_MESSAGE = "Exception occurred while attempting to get all merchandises from dynamoDB: merchandise";
    public static final String MERCHANDISE_CREATE_SUCCESSFUL_MESSAGE = "Successfully created new merchandise with SKU %s to merchandise DynamoDB Table";
    public static final String MERCHANDISE_CREATE_FAIL_MESSAGE = "Exception occurred while attempting to save dynamoDB merchandise: %s";
    public static final String MERCHANDISE_UPDATE_SUCCESSFUL_MESSAGE = "Successfully updated new merchandise with SKU %s to merchandise DynamoDB Table";
    public static final String MERCHANDISE_UPDATE_FAIL_MESSAGE = "Exception occurred while attempting to updated new merchandise with SKU %s to merchandise DynamoDB Table";
    public static final String MERCHANDISE_DELETE_SUCCESSFUL_MESSAGE = "Successfully deleted merchandise with sku %s from DynamoDB table: 'merchandise'";
    public static final String MERCHANDISE_DELETE_FAIL_MESSAGE = "Exception occurred while attempting to delete merchandise with sku %s from DynamoDB table: 'merchandise'";

    public static final String INVENTORY_GET_ALL_SUCCESSFUL_MESSAGE = "Successfully get all inventory from dynamoDB: inventory";
    public static final String INVENTORY_GET_ALL_FAIL_MESSAGE = "Exception occurred while attempting to get all inventory from dynamoDB: inventory";
}
