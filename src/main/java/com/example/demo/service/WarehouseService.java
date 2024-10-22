package com.example.demo.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.example.demo.dto.InventoryDto;
import com.example.demo.dto.MerchandiseDto;
import com.example.demo.model.Inventory;
import com.example.demo.model.Merchandise;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.MerchandiseRepository;
import com.example.demo.requestBodyModel.NewMerchandiseRequestBody;
import com.example.demo.requestBodyModel.UpdateMerchandiseRequestBody;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseService implements IWarehouseService {

    @Autowired
    MerchandiseRepository merchandiseRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    Logger logger;

    @Override
    public MerchandiseDto getMerchandiseBySku(String merchandiseSku) {
        Merchandise merchandiseModel = merchandiseRepository.getMerchandiseInfoBySku(merchandiseSku);
        MerchandiseDto merchandiseDto = new MerchandiseDto();
        if (merchandiseModel == null) {
            logger.warn("Invalid Merchandise SKU: {}", merchandiseSku);
            throw new NotFoundException("not found");
        }
        logger.info("Successfully fetched detail for merchandise with SKU: {}", merchandiseSku);
        merchandiseDto = new MerchandiseDto(merchandiseModel);
        return merchandiseDto;
    }

    @Override
    public MerchandiseDto insertMerchandise(NewMerchandiseRequestBody requestBody) {
        Merchandise merchandise = merchandiseRepository.createNewMerchandise(requestBody);
        MerchandiseDto res = new MerchandiseDto(merchandise);
        return res;
    }

    @Override
    public List<MerchandiseDto> getAllMerchandise() {
        List<MerchandiseDto> merchandiseDtoList = new ArrayList<>();
        List<Merchandise> merchandiseList = merchandiseRepository.getAllMerchandise();
        for (Merchandise merchandise : merchandiseList) {
            merchandiseDtoList.add(new MerchandiseDto(merchandise));
        }
        return merchandiseDtoList;
    }

    @Override
    public void deleteMerchandiseBySku(String merchandiseSku) {
        merchandiseRepository.deleteMerchandiseBySku(merchandiseSku);
    }

    @Override
    public MerchandiseDto updateMerchandiseBySku(String merchandiseSku, UpdateMerchandiseRequestBody requestBody, Merchandise currentMerchandise) {
        Merchandise merchandise = merchandiseRepository.updateMerchandiseBySku(merchandiseSku, requestBody, currentMerchandise);
        MerchandiseDto res =new MerchandiseDto(merchandise);
        return res;
    }

    @Override
    public List<InventoryDto> getFromInventory(String type, String region, String brand, Integer value, String status, String code, String order_number) {
        List<InventoryDto> inventoryDtoArrayList = new ArrayList<>();
        List<Inventory> merchandiseList = inventoryRepository.getFromInventory(type, region, brand, value, status, code, order_number);
        for (Inventory inventory : merchandiseList) {
            inventoryDtoArrayList.add(new InventoryDto(inventory));
        }
        return inventoryDtoArrayList;
    }
}
