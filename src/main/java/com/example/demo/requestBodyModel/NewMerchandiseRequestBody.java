package com.example.demo.requestBodyModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

@Validated
public class NewMerchandiseRequestBody {
    @JsonProperty(namespace = "sku", required = true)
    private String sku;

    @JsonProperty(namespace = "name", required = true)
    private String name;

    @JsonProperty(namespace = "count", required = true)
    private Integer count;

    @JsonProperty(namespace = "description", required = true)
    private String description;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
