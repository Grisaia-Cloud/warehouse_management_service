package com.example.demo.validation;

import com.example.demo.repository.InventoryRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryValidator {

    @Autowired
    Logger logger;

    @Autowired
    InventoryRepository inventoryRepository;


}
