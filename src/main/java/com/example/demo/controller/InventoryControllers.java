package com.example.demo.controller;

import com.example.demo.constants.ControllerConstants;
import com.example.demo.dto.InventoryDto;
import com.example.demo.enumeration.InventoryEnums;
import com.example.demo.requestBodyModel.GetInventoryRequestBody;
import com.example.demo.requestBodyModel.NewInventoryRequestBody;
import com.example.demo.requestBodyModel.UpdateInventoryRequestBody;
import com.example.demo.service.IWarehouseService;
import com.example.demo.validation.InventoryValidator;
import jakarta.websocket.server.PathParam;
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
    public ResponseEntity<Object> getInventoryItems(
            @RequestParam(value = "type", required = false) String type, @RequestParam(value = "region", required = false) String region,
            @RequestParam(value = "brand", required = false) String brand, @RequestParam(value = "value", required = false) Integer value,
            @RequestParam(value = "status", required = false) String status, @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "order_number", required = false) String order_number, @RequestBody(required = false) GetInventoryRequestBody requestBody) {
        Pair<Boolean, String> validation_result = inventoryValidator.validateGetRequestParams(type, region, brand, value, status, code, order_number, requestBody);
        if (!validation_result.getFirst()) {
            return ResponseEntity.badRequest().body(validation_result.getSecond());
        }
        if (type == null) {
            try {
                return ResponseEntity.ok(warehouseService.getAllInventoryItems());
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body(e.getMessage());
            }
        } else {
            try {
                return ResponseEntity.ok(warehouseService.getInventoryItems(type, region, brand, value, status, code, order_number, requestBody != null ? requestBody.getCount() : null));
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body(e.getMessage());
            }
        }
    }

    @GetMapping("/inventory/types")
    public ResponseEntity<Object> getInventoryTypes() {
        return ResponseEntity.ok().body(InventoryEnums.Type.values());
    }

    @GetMapping(value = {"/inventory/type/{type}/regions", "/inventory/regions"})
    public ResponseEntity<Object> getInventoryRegions() {
        return ResponseEntity.ok().body(InventoryEnums.Region.values());
    }

    @GetMapping(value = {"/inventory/type/{type}/region/{region}/brands", "/inventory/brands"})
    public ResponseEntity<Object> getInventoryBrands() {
        return ResponseEntity.ok().body(InventoryEnums.Brand.values());
    }

    @GetMapping(value = {"/inventory/type/{type}/region/{region}/brand/{brand}/statuses", "/inventory/statuses"})
    public ResponseEntity<Object> getInventoryStatuses() {
        return ResponseEntity.ok().body(InventoryEnums.Status.values());
    }


    @PostMapping("/inventory")
    public ResponseEntity<Object> addInventoryItems(@RequestBody(required = true) List<NewInventoryRequestBody> requestBodyList) {
        Pair<Boolean, String> validation_result = inventoryValidator.validatePostRequestParams(requestBodyList);
        if (!validation_result.getFirst()) {
            return ResponseEntity.badRequest().body(validation_result.getSecond());
        }
        try {
            warehouseService.addInventoryItems(requestBodyList);
            return ResponseEntity.ok().build();
        } catch (TransactionCanceledException e) {
            return ResponseEntity.badRequest().body(ControllerConstants.DUPLICATE_INVENTORY_ITEM_ERROR_MESSAGE);
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }

    @PutMapping("/inventory/code/{code}")
    public ResponseEntity<Object> updateInventoryItems(@RequestBody(required = true) UpdateInventoryRequestBody requestBody, @PathVariable(required = true) String code) {
        Pair<Boolean, String> validation_result = inventoryValidator.validateUpdateRequestParams(requestBody);
        if (!validation_result.getFirst()) {
            return ResponseEntity.badRequest().body(validation_result.getSecond());
        }
        try {
            InventoryDto res = warehouseService.updateInventoryItems(requestBody, code);
            return ResponseEntity.ok().body(res);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }
//    @DeleteMapping("/inventory/type/{type}/{code}/")
}
