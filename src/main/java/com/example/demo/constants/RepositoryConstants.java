package com.example.demo.constants;

public class RepositoryConstants {
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

    public static final String INVENTORY_GET_FAIL_MESSAGE = "Exception occurred while attempting to get from dynamoDB inventory with filter: type %s, region %s, brand %s, value %s, status %s, code %s, order_number %s";
    public static final String INVENTORY_GET_ALL_SUCCESSFUL_MESSAGE = "Successfully get all inventories from dynamoDB: inventory";
    public static final String INVENTORY_GET_ALL_FAIL_MESSAGE = "Exception occurred while attempting to get all inventories from dynamoDB: inventory";
}
