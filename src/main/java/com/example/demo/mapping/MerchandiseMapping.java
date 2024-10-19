package com.example.demo.mapping;

import com.example.demo.dto.MerchandiseDto;
import com.example.demo.model.Merchandise;
import org.springframework.stereotype.Component;

@Component
public class MerchandiseMapping {

    public MerchandiseDto merchandiseToMerchandiseDto(Merchandise merchandise) {
        return new MerchandiseDto(
                merchandise
        );
    }

}
