package com.example.demo.validation;

import com.example.demo.constants.ValidationConstants;
import com.example.demo.enumeration.InventoryEnums;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.requestBodyModel.GetInventoryRequestBody;
import com.example.demo.requestBodyModel.NewInventoryRequestBody;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryValidator {

    @Autowired
    Logger logger;

    @Autowired
    InventoryRepository inventoryRepository;

    public Pair<Boolean, String> validateGetRequestParams(String type, String region, String brand, Integer value, String status, String code, String order_number, GetInventoryRequestBody requestBody) {
        if (type == null && (region != null || brand != null || value != null || status != null || code != null || order_number != null)) {
            return Pair.of(false, ValidationConstants.NULL_TYPE_ERROR_MESSAGE);
        }
        if (type != null) {
            try {
                InventoryEnums.Type.valueOf(type);
            } catch (IllegalArgumentException e) {
                return Pair.of(false, ValidationConstants.ILLEGAL_TYPE_ERROR_MESSAGE);
            }
        }
        if (region != null) {
            try {
                InventoryEnums.Region.valueOf(region);
            } catch (IllegalArgumentException e) {
                return Pair.of(false, ValidationConstants.ILLEGAL_REGION_ERROR_MESSAGE);
            }
        }
        if (brand != null) {
            try {
                InventoryEnums.Brand.valueOf(brand);
            } catch (IllegalArgumentException e) {
                return Pair.of(false, ValidationConstants.ILLEGAL_BRAND_ERROR_MESSAGE);
            }
        }
        if (value != null && value <= 0) {
            return Pair.of(false, ValidationConstants.ILLEGAL_VALUE_ERROR_MESSAGE);
        }
        if (status != null) {
            try {
                InventoryEnums.Status.valueOf(status);
            } catch (IllegalArgumentException e) {
                return Pair.of(false, ValidationConstants.ILLEGAL_STATUS_ERROR_MESSAGE);
            }
        }
        if (requestBody != null && requestBody.getCount() <= 0) {
            return Pair.of(false, ValidationConstants.ILLEGAL_COUNT_ERROR_MESSAGE);
        }
        return Pair.of(true, ValidationConstants.EMPTY_STRING);
    }

    public Pair<Boolean, String> validatePostRequestParams(List<NewInventoryRequestBody> requestBodyList) {
        for (NewInventoryRequestBody requestBody: requestBodyList) {
            if (requestBody.getValue() <= 0) {
                return Pair.of(false, ValidationConstants.ILLEGAL_VALUE_ERROR_MESSAGE);
            }
        }
        return Pair.of(true, ValidationConstants.EMPTY_STRING);
    }
}
