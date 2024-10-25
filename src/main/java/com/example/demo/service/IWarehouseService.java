package com.example.demo.service;

import com.example.demo.dto.InventoryDto;
import com.example.demo.dto.MerchandiseDto;
import com.example.demo.model.Merchandise;
import com.example.demo.requestBodyModel.NewInventoryRequestBody;
import com.example.demo.requestBodyModel.NewMerchandiseRequestBody;
import com.example.demo.requestBodyModel.UpdateMerchandiseRequestBody;

import java.util.List;

public interface IWarehouseService {
    MerchandiseDto getMerchandiseBySku(String merchandiseSku);
    MerchandiseDto insertMerchandise(NewMerchandiseRequestBody requestBody);
    List<MerchandiseDto> getAllMerchandise();
    void deleteMerchandiseBySku(String merchandiseSku);
    MerchandiseDto updateMerchandiseBySku(String merchandiseSku, UpdateMerchandiseRequestBody requestBody, Merchandise current);
    List<InventoryDto> getFromInventory(String type, String region, String brand, Integer value, String status, String code, String order_number);
    List<InventoryDto> getAllFromInventory();
    void addToInventory(List<NewInventoryRequestBody> newInventoryRequestBodyList);
}
