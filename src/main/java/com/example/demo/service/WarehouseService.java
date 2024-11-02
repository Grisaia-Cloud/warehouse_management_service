package com.example.demo.service;

import com.example.demo.dto.InventoryDto;
import com.example.demo.dto.MerchandiseDto;
import com.example.demo.model.Inventory;
import com.example.demo.model.Merchandise;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.MerchandiseRepository;
import com.example.demo.requestBodyModel.GetInventoryRequestBody;
import com.example.demo.requestBodyModel.NewInventoryRequestBody;
import com.example.demo.requestBodyModel.NewMerchandiseRequestBody;
import com.example.demo.requestBodyModel.UpdateMerchandiseRequestBody;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;

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
        if (merchandiseModel == null) {
            throw ResourceNotFoundException.builder().build();
        }
        logger.info("Successfully fetched detail for merchandise with SKU: {}", merchandiseSku);
        return new MerchandiseDto(merchandiseModel);
    }

    @Override
    public MerchandiseDto insertMerchandise(NewMerchandiseRequestBody requestBody) {
        Merchandise merchandise = merchandiseRepository.createNewMerchandise(requestBody);
        return new MerchandiseDto(merchandise);
    }

    @Override
    public List<MerchandiseDto> getAllMerchandise() {
        List<MerchandiseDto> merchandiseDtoList = new ArrayList<>();
        PageIterable<Merchandise> merchandiseList = merchandiseRepository.getAllMerchandise();
        for (Page<Merchandise> page : merchandiseList) {
            for (Merchandise merchandise: page.items()) {
                merchandiseDtoList.add(new MerchandiseDto(merchandise));
            }
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
        return new MerchandiseDto(merchandise);
    }

    @Override
    public List<InventoryDto> getFromInventory(String type, String region, String brand, Integer value, String status, String code, String order_number, Integer count) {
        List<InventoryDto> inventoryDtoArrayList = new ArrayList<>();
        PageIterable<Inventory> inventoryPageIterable = inventoryRepository.getFromInventory(type, region, brand, value, status, code, order_number, count);
        for (Page<Inventory> page : inventoryPageIterable) {
            for (Inventory inventory : page.items()) {
                inventoryDtoArrayList.add(new InventoryDto(inventory));
            }
            if (count != null) {
                break;
            }
        }
        return inventoryDtoArrayList;
    }

    @Override
    public List<InventoryDto> getAllFromInventory() {
        List<InventoryDto> inventoryDtoArrayList = new ArrayList<>();
        PageIterable<Inventory> inventoryList = inventoryRepository.getAllFromInventory();
        for (Page<Inventory> page : inventoryList) {
            for (Inventory inventory: page.items()) {
                inventoryDtoArrayList.add(new InventoryDto(inventory));
            }
        }
        return inventoryDtoArrayList;
    }

    @Override
    public void addToInventory(List<NewInventoryRequestBody> newInventoryRequestBodyList) {
        List<Inventory> inventoryList = new ArrayList<>();
        for (NewInventoryRequestBody requestBody : newInventoryRequestBodyList) {
            inventoryList.add(new Inventory(requestBody));
        }
        inventoryRepository.addToInventory(inventoryList);
    }
}
