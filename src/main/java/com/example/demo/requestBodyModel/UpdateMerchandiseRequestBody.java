package com.example.demo.requestBodyModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

@Validated
public class UpdateMerchandiseRequestBody {
    @JsonProperty(namespace = "merchandiseName", required = false)
    private String merchandiseName;

    @JsonProperty(namespace = "availableMerchandiseCount", required = false)
    private Integer availableMerchandiseCount;

    @JsonProperty(namespace = "description", required = false)
    private String description;

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
