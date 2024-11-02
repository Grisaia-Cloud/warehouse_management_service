package com.example.demo.controller;

import com.example.demo.constants.ControllerConstants;
import com.example.demo.requestBodyModel.NewInventoryRequestBody;
import com.example.demo.service.IWarehouseService;
import com.example.demo.validation.InventoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.dynamodb.model.TransactionCanceledException;

import java.util.List;

// Partition key: Giftcard
// combination sort key: china:Unused:Apple:100:

// Type | Region | Brand | Value | Status | Code | Order#
@RestController
@RequestMapping("/api/warehouse")
@Validated
public class InventoryControllers {

    @Autowired
    IWarehouseService warehouseService;

    @Autowired
    InventoryValidator inventoryValidator;

    @GetMapping(value = "/inventory")
    public ResponseEntity<Object> getFromInventory(
            @RequestParam(value = "type", required = false) String type, @RequestParam(value = "region", required = false) String region,
            @RequestParam(value = "brand", required = false) String brand, @RequestParam(value = "value", required = false) Integer value,
            @RequestParam(value = "status", required = false) String status, @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "order_number", required = false) String order_number) {
        Pair<Boolean, String> validation_result = inventoryValidator.validateGetRequestParams(type, region, brand, value, status, code, order_number);
        if (!validation_result.getFirst()) {
            return ResponseEntity.badRequest().body(validation_result.getSecond());
        }
        if (type == null) {
            try {
                return ResponseEntity.ok(warehouseService.getAllFromInventory());
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body(e.getMessage());
            }
        } else {
            try {
                return ResponseEntity.ok(warehouseService.getFromInventory(type, region, brand, value, status, code, order_number));
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body(e.getMessage());
            }
        }
    }

    @PostMapping("/inventory")
    public ResponseEntity<Object> addToInventory(@RequestBody(required = true) List<NewInventoryRequestBody> requestBodyList) {
        Pair<Boolean, String> validation_result = inventoryValidator.validatePostRequestParams(requestBodyList);
        if (!validation_result.getFirst()) {
            return ResponseEntity.badRequest().body(validation_result.getSecond());
        }
        try {
            warehouseService.addToInventory(requestBodyList);
            return ResponseEntity.ok().build();
        } catch (TransactionCanceledException e) {
            return ResponseEntity.badRequest().body(ControllerConstants.DUPLICATE_INVENTORY_ITEM_ERROR_MESSAGE);
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }
}
