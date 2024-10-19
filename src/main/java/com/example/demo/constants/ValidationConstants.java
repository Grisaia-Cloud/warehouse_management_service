package com.example.demo.constants;

public class ValidationConstants {
    public static final String EMPTY_STRING = "";
    public static final String INVALID_SKU_ERROR_MESSAGE = "Request Body field name SKU cannot be null or empty";
    public static final String INVALID_NAME_ERROR_MESSAGE = "Request Body field name NAME cannot be null or empty";
    public static final String INVALID_COUNT_ERROR_MESSAGE = "Request Body field name COUNT cannot be less than 0 or null";
    public static final String DUPLICATE_SKU_ERROR_MESSAGE = "Duplicate SKU is not allowed: provided merchandise SKU has already been registered";
    public static final String NOTHING_TO_UPDATE_ERROR_MESSAGE = "Unable to update merchandise: missing all fields";
    public static final String MISSING_MERCHANDISE = "Merchandise with provided SKU %s does not exist";
}
