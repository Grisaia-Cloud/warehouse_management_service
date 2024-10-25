package com.example.demo.dto;


import com.example.demo.model.Merchandise;

public class MerchandiseDto {

    private String merchandiseSku;
    private String merchandiseName;
    private Integer availableMerchandiseCount;
    private String description;

    public MerchandiseDto(){}

    public MerchandiseDto(Merchandise merchandise) {
        this.merchandiseSku = merchandise.getMerchandiseSku();
        this.merchandiseName = merchandise.getMerchandiseName();
        this.availableMerchandiseCount = merchandise.getAvailableMerchandiseCount();
        this.description = merchandise.getDescription();
    }

    public MerchandiseDto(int id, String sku, String name, int count, String description) {
        this.merchandiseSku = sku;
        this.merchandiseName = name;
        this.availableMerchandiseCount = count;
        this.description = description;
    }
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
