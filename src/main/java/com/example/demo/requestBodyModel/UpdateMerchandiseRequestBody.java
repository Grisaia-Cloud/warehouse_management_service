package com.example.demo.requestBodyModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateMerchandiseRequestBody {
    @JsonProperty(namespace = "name", required = false)
    private String name;

    @JsonProperty(namespace = "count", required = false)
    private Integer count;

    @JsonProperty(namespace = "description", required = false)
    private String description;

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
