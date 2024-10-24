package com.example.demo.requestBodyModel;

import com.example.demo.enumeration.InventoryEnums;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

// Type | Region | Brand | Value | Status | Code | Order#
@Validated
public class NewInventoryRequestBody {
    @JsonProperty(namespace = "type", required = true)
    InventoryEnums.Type type;

    @JsonProperty(namespace = "region", required = true)
    InventoryEnums.Region region;

    @JsonProperty(namespace = "brand", required = true)
    InventoryEnums.Brand brand;

    @JsonProperty(namespace = "value", required = true)
    int value;

    @JsonProperty(namespace = "status", required = true)
    String code;

    @JsonProperty(namespace = "status", required = true)
    InventoryEnums.Status status;

    @JsonProperty(namespace = "order_number", required = true)
    String order_number;

    public InventoryEnums.Type getType() {
        return type;
    }

    public void setType(InventoryEnums.Type type) {
        this.type = type;
    }

    public InventoryEnums.Region getRegion() {
        return region;
    }

    public void setRegion(InventoryEnums.Region region) {
        this.region = region;
    }

    public InventoryEnums.Brand getBrand() {
        return brand;
    }

    public void setBrand(InventoryEnums.Brand brand) {
        this.brand = brand;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public InventoryEnums.Status getStatus() {
        return status;
    }

    public void setStatus(InventoryEnums.Status status) {
        this.status = status;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }
}
