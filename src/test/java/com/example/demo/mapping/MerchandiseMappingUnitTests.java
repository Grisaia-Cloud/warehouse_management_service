package com.example.demo.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MerchandiseMappingUnitTests {

    @Autowired
    MerchandiseMapping merchandiseMapping;

//    @Test
//    public void merchandiseMappingTest() {
//        Merchandise merchandise = new Merchandise();
//        merchandise.setId(1);
//        merchandise.setCount(2);
//        merchandise.setName("test");
//        merchandise.setSku("ABC123");
//        merchandise.setDescription("some desc");
//
//        MerchandiseInfoDto res = merchandiseMapping.MerchandiseToMerchandiseInfoDtoMap(merchandise);
//
//        Assertions.assertEquals(1, res.getMerchandiseId());
//        Assertions.assertEquals(2, res.getAvailableMerchandiseCount());
//        Assertions.assertEquals("test", res.getMerchandiseName());
//        Assertions.assertEquals("ABC123", res.getMerchandiseSku());
//        Assertions.assertEquals("some desc", res.getDescription());
//    }
}
