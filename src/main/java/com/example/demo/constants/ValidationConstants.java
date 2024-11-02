package com.example.demo.constants;

public class ValidationConstants {
    public static final String EMPTY_STRING = "";
    public static final String INVALID_SKU_ERROR_MESSAGE = "Request Body field name SKU cannot be null or empty";
    public static final String INVALID_NAME_ERROR_MESSAGE = "Request Body field name NAME cannot be null or empty";
    public static final String INVALID_COUNT_ERROR_MESSAGE = "Request Body field name COUNT cannot be less than 0 or null";
    public static final String NOTHING_TO_UPDATE_ERROR_MESSAGE = "Unable to update merchandise: missing all fields";
    public static final String NULL_TYPE_ERROR_MESSAGE = "Required param: 'type' is not provided";
    public static final String ILLEGAL_TYPE_ERROR_MESSAGE = "Provided param: 'type' is not valid";
    public static final String ILLEGAL_REGION_ERROR_MESSAGE = "Provided param: 'region' is not valid";
    public static final String ILLEGAL_BRAND_ERROR_MESSAGE = "Provided param: 'brand' is not valid";
    public static final String ILLEGAL_VALUE_ERROR_MESSAGE = "Provided param: 'value' has to be greater than zero";
    public static final String ILLEGAL_STATUS_ERROR_MESSAGE = "Provided param: 'status' is not valid";
    public static final String ILLEGAL_COUNT_ERROR_MESSAGE = "Request Body field: 'count' has to be greater than zero";
}
