package com.example.demo.requestBodyModel;

import com.example.demo.enumeration.InventoryEnums;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

@Validated
public class UpdateInventoryRequestBody {
    @JsonProperty(namespace = "status", required = false)
    InventoryEnums.Status status;

    @JsonProperty(namespace = "order_number", required = false)
    String order_number;

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
