package com.example.demo.requestBodyModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

@Validated
public class GetInventoryRequestBody {
    @JsonProperty(namespace = "count", required = true)
    int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
