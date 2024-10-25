package com.example.demo.requestBodyModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

@Validated
public class NewMerchandiseRequestBody {
    @JsonProperty(namespace = "merchandiseSku", required = true)
    private String merchandiseSku;

    @JsonProperty(namespace = "merchandiseName", required = true)
    private String merchandiseName;

    @JsonProperty(namespace = "availableMerchandiseCount", required = true)
    private Integer availableMerchandiseCount;

    @JsonProperty(namespace = "description", required = true)
    private String description;

    public String getMerchandiseSku() {
        return merchandiseSku;
    }

    public void setMerchandiseSku(String merchandiseSku) {
        this.merchandiseSku = merchandiseSku;
    }

    public String getMerchandiseName() {
        return merchandiseName;
    }

    public void setMerchandiseName(String merchandiseName) {
        this.merchandiseName = merchandiseName;
    }

    public Integer getAvailableMerchandiseCount() {
        return availableMerchandiseCount;
    }

    public void setAvailableMerchandiseCount(Integer availableMerchandiseCount) {
        this.availableMerchandiseCount = availableMerchandiseCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
