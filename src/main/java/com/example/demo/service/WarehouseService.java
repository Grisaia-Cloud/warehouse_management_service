package com.example.demo.service;

import com.example.demo.dto.MerchandiseDto;
import com.example.demo.mapping.MerchandiseMapping;
import com.example.demo.model.Merchandise;
import com.example.demo.repository.MerchandiseRepository;
import com.example.demo.requestBodyModel.NewMerchandiseRequestBody;
import com.example.demo.requestBodyModel.UpdateMerchandiseRequestBody;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService implements IWarehouseService {

    @Autowired
    MerchandiseMapping merchandiseMapping;

    @Autowired
    MerchandiseRepository merchandiseRepository;

    @Autowired
    Logger logger;

    @Override
    public MerchandiseDto getMerchandiseBySku(String merchandiseSku) {
        Optional<Merchandise> merchandiseModel = merchandiseRepository.getMerchandiseInfoBySku(merchandiseSku);
        MerchandiseDto merchandiseDto = new MerchandiseDto();
        if (merchandiseModel.isEmpty()) {
            logger.warn("Invalid Merchandise SKU: {}", merchandiseSku);
            return merchandiseDto;
        }
        logger.info("Successfully fetched detail for mechandise with SKU: {}", merchandiseSku);
        merchandiseDto = merchandiseMapping.merchandiseToMerchandiseDto(merchandiseModel.get());
        return merchandiseDto;
    }

    @Override
    public MerchandiseDto insertMerchandise(NewMerchandiseRequestBody requestBody) {
        Merchandise merchandise = merchandiseRepository.createNewMerchandise(requestBody);
        MerchandiseDto res = merchandiseMapping.merchandiseToMerchandiseDto(merchandise);
        return res;
    }

    @Override
    public List<MerchandiseDto> getAllMerchandise() {
        List<MerchandiseDto> merchandiseDtoList = new ArrayList<>();
        List<Merchandise> merchandiseList = merchandiseRepository.getAllMerchandise();
        for (Merchandise merchandise : merchandiseList) {
            merchandiseDtoList.add(merchandiseMapping.merchandiseToMerchandiseDto(merchandise));
        }
        return merchandiseDtoList;
    }

    @Override
    public void deleteMerchandiseBySku(String merchandiseSku) {
        merchandiseRepository.deleteMerchandiseBySku(merchandiseSku);
    }

    @Override
    public MerchandiseDto updateMerchandiseBySku(String merchandiseSku, UpdateMerchandiseRequestBody requestBody) {
        Merchandise merchandise = merchandiseRepository.updateMerchandiseBySku(merchandiseSku, requestBody);
        MerchandiseDto res = merchandiseMapping.merchandiseToMerchandiseDto(merchandise);
        return res;
    }
}
