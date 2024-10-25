package com.example.demo.controller;

import com.example.demo.constants.ControllerConstants;
import com.example.demo.constants.RepositoryConstants;
import com.example.demo.model.Merchandise;
import com.example.demo.requestBodyModel.NewMerchandiseRequestBody;
import com.example.demo.requestBodyModel.UpdateMerchandiseRequestBody;
import com.example.demo.service.IWarehouseService;
import com.example.demo.validation.MerchandiseValidator;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.dynamodb.model.ConditionalCheckFailedException;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;

@RestController
@Validated
@RequestMapping("/api/warehouse")
public class MerchandiseControllers {

    @Autowired
    IWarehouseService warehouseService;

    @Autowired
    MerchandiseValidator merchandiseValidator;

    @GetMapping("/merchandise/{merchandiseSku}")
    public ResponseEntity<Object> getMerchandiseBySku(@PathVariable(required = true) String merchandiseSku) {
        try {
            return ResponseEntity.ok(warehouseService.getMerchandiseBySku(merchandiseSku));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(String.format(RepositoryConstants.MERCHANDISE_MISSING_MERCHANDISE, merchandiseSku), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ControllerConstants.INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }

    @PostMapping("/merchandise")
    public ResponseEntity<Object> addNewMerchandise(@RequestBody NewMerchandiseRequestBody requestBody) {
        Pair<Boolean, String> validationResult = merchandiseValidator.validateNewMerchandiseRequestBody(requestBody);
        if (!validationResult.getLeft()) {
            return ResponseEntity.badRequest().body(validationResult.getRight());
        }
        try {
            return ResponseEntity.ok(warehouseService.insertMerchandise(requestBody));
        } catch (ConditionalCheckFailedException e) {
            return ResponseEntity.internalServerError().body(ControllerConstants.DUPLICATE_SKU_ERROR_MESSAGE);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ControllerConstants.INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }

    @GetMapping("/merchandise")
    public ResponseEntity<Object> getAllMerchandise() {
        try {
            return ResponseEntity.ok(warehouseService.getAllMerchandise());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ControllerConstants.INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }

    @DeleteMapping("/merchandise/{merchandiseSku}")
    public ResponseEntity<Object> deleteMerchandiseBySku(@PathVariable(required = true) String merchandiseSku) {
        try {
            warehouseService.deleteMerchandiseBySku(merchandiseSku);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(ControllerConstants.INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }

    @PutMapping("/merchandise/{merchandiseSku}")
    public ResponseEntity<Object> updateMerchandiseBySku(@PathVariable(required = true) String merchandiseSku, @RequestBody UpdateMerchandiseRequestBody requestBody) {
        Pair<Boolean, Object> validationResult = merchandiseValidator.validateUpdateMerchandiseRequestBody(merchandiseSku, requestBody);
        if (!validationResult.getLeft()) {
            return ResponseEntity.badRequest().body(validationResult.getRight());
        }
        try {
            return ResponseEntity.ok(warehouseService.updateMerchandiseBySku(merchandiseSku, requestBody, (Merchandise) validationResult.getRight()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ControllerConstants.INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }
}
