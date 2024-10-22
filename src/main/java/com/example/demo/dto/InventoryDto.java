package com.example.demo.dto;

import com.example.demo.model.Inventory;

// Type | Region | Brand | Value | Status | Code | Order#
public class InventoryDto {
    private String type;
    private String region;
    private String brand;
    private Integer value;
    private String status;
    private String code;
    private String order_number;

    public InventoryDto() {};

    public InventoryDto(Inventory inventory) {
        this.type = inventory.getType();
        this.region = inventory.getRegion();
        this.brand = inventory.getBrand();
        this.value = inventory.getValue();
        this.status = inventory.getStatus();
        this.code = inventory.getCode();
        this.order_number = inventory.getOrder_number();
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }
}
