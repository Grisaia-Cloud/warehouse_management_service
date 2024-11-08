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
        if (requestBody.getMerchandiseSku() == null || requestBody.getMerchandiseSku().isEmpty()) {
            logger.error(ValidationConstants.INVALID_SKU_ERROR_MESSAGE);
            return Pair.of(false, ValidationConstants.INVALID_SKU_ERROR_MESSAGE);
        } else if (requestBody.getMerchandiseName() == null || requestBody.getMerchandiseName().isEmpty()) {
            logger.error(ValidationConstants.INVALID_NAME_ERROR_MESSAGE);
            return Pair.of(false, ValidationConstants.INVALID_NAME_ERROR_MESSAGE);
        } else if (requestBody.getAvailableMerchandiseCount() == null || requestBody.getAvailableMerchandiseCount() < 0) {
            logger.error(ValidationConstants.INVALID_COUNT_ERROR_MESSAGE);
            return Pair.of(false, ValidationConstants.INVALID_COUNT_ERROR_MESSAGE);
        }
        return Pair.of(true, ValidationConstants.EMPTY_STRING);
    }

    public Pair<Boolean,Object> validateUpdateMerchandiseRequestBody(String sku, UpdateMerchandiseRequestBody requestBody) {
        Merchandise merchandise = merchandiseRepository.getMerchandiseInfoBySku(sku);
        if (requestBody.getDescription() == null && requestBody.getMerchandiseName() == null && requestBody.getAvailableMerchandiseCount() == null) {
            logger.error(ValidationConstants.NOTHING_TO_UPDATE_ERROR_MESSAGE);
            return Pair.of(false, ValidationConstants.NOTHING_TO_UPDATE_ERROR_MESSAGE);
        } else if (requestBody.getAvailableMerchandiseCount() != null && requestBody.getAvailableMerchandiseCount() < 0) {
            logger.error(ValidationConstants.INVALID_COUNT_ERROR_MESSAGE);
            return Pair.of(false, ValidationConstants.INVALID_COUNT_ERROR_MESSAGE);
        }
        return Pair.of(true, merchandise);
    }
}
