package com.example.demo.controller;

import com.example.demo.constants.ControllerConstants;
import com.example.demo.requestBodyModel.NewInventoryRequestBody;
import com.example.demo.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
// Partition key: Giftcard
// combination sort key: china:Unused:Apple:100:

// Type | Region | Brand | Value | Status | Code | Order#
public class InventoryControllers {

    @Autowired
    IWarehouseService warehouseService;

    @GetMapping("/inventory")
    public ResponseEntity<Object> getFromInventory(
            @RequestParam(value = "type", required = true) String type, @RequestParam(value = "region", required = false) String region,
            @RequestParam(value = "brand", required = false) String brand, @RequestParam(value = "value", required = false) Integer value,
            @RequestParam(value = "status", required = false) String status, @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "order_number", required = false) String order_number) {
        // TODO: add validation
        try {
            return ResponseEntity.ok(warehouseService.getFromInventory(type, region, brand, value, status, code, order_number));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ControllerConstants.INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }

//    @PostMapping("/inventory")
//    public ResponseEntity<Object> addToInventory(@RequestBody(required = true) List<NewInventoryRequestBody> requestBodyList) {
//
//    }

}
