package com.example.demo.controller;

import com.example.demo.constants.ControllerConstants;
import com.example.demo.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(value = "type") String type, @RequestParam(value = "region") String region,
            @RequestParam(value = "brand") String brand, @RequestParam(value = "value") Integer value,
            @RequestParam(value = "status") String status, @RequestParam(value = "code") String code,
            @RequestParam(value = "order_number") String order_number) {
        try {
            return ResponseEntity.ok(warehouseService.getFromInventory(type, region, brand, value, status, code, order_number));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ControllerConstants.INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }

    @PostMapping("/inventory")
    public ResponseEntity<Object> addToInventory(@RequestBody(required = true) List<newInventoryRequestBody> requestBodyList) {

    }

}
