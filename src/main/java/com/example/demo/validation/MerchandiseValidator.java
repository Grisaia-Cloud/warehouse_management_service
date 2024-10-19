package com.example.demo.validation;

import com.example.demo.constants.ValidationConstants;
import com.example.demo.model.Merchandise;
import com.example.demo.repository.MerchandiseRepository;
import com.example.demo.requestBodyModel.NewMerchandiseRequestBody;
import com.example.demo.requestBodyModel.UpdateMerchandiseRequestBody;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MerchandiseValidator {

    @Autowired
    Logger logger;

    @Autowired
    MerchandiseRepository merchandiseRepository;

    public Pair<Boolean,String> validateNewMerchandiseRequestBody(NewMerchandiseRequestBody requestBody) {
        if (requestBody.getSku() == null || requestBody.getSku().isEmpty()) {
            logger.error(ValidationConstants.INVALID_SKU_ERROR_MESSAGE);
            return Pair.of(false, ValidationConstants.INVALID_SKU_ERROR_MESSAGE);
        } else if (merchandiseRepository.getMerchandiseInfoBySku(requestBody.getSku()).isPresent()) {
            logger.error(ValidationConstants.DUPLICATE_SKU_ERROR_MESSAGE);
            return Pair.of(false, ValidationConstants.DUPLICATE_SKU_ERROR_MESSAGE);
        } else if (requestBody.getName() == null || requestBody.getName().isEmpty()) {
            logger.error(ValidationConstants.INVALID_NAME_ERROR_MESSAGE);
            return Pair.of(false, ValidationConstants.INVALID_NAME_ERROR_MESSAGE);
        } else if (requestBody.getCount() == null || requestBody.getCount() < 0) {
            logger.error(ValidationConstants.INVALID_COUNT_ERROR_MESSAGE);
            return Pair.of(false, ValidationConstants.INVALID_COUNT_ERROR_MESSAGE);
        }
        return Pair.of(true, ValidationConstants.EMPTY_STRING);
    }

    public Pair<Boolean,String> validateUpdateMerchandiseRequestBody(UpdateMerchandiseRequestBody requestBody) {
        if (requestBody.getSku() == null || requestBody.getSku().isEmpty()) {
            logger.error(ValidationConstants.INVALID_SKU_ERROR_MESSAGE);
            return Pair.of(false, ValidationConstants.INVALID_SKU_ERROR_MESSAGE);
        } else if (merchandiseRepository.getMerchandiseInfoBySku(requestBody.getSku()).isEmpty()) {
            logger.error(String.format(ValidationConstants.MISSING_MERCHANDISE, requestBody.getSku()));
            return Pair.of(false, ValidationConstants.MISSING_MERCHANDISE);
        } else if (requestBody.getDescription() == null && requestBody.getName() == null && requestBody.getCount() == null) {
            logger.error(ValidationConstants.NOTHING_TO_UPDATE_ERROR_MESSAGE);
            return Pair.of(false, ValidationConstants.NOTHING_TO_UPDATE_ERROR_MESSAGE);
        } else if (requestBody.getCount() != null && requestBody.getCount() < 0) {
            logger.error(ValidationConstants.INVALID_COUNT_ERROR_MESSAGE);
            return Pair.of(false, ValidationConstants.INVALID_COUNT_ERROR_MESSAGE);
        }
        return Pair.of(true, ValidationConstants.EMPTY_STRING);
    }
}
