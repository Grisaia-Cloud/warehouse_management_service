package com.example.demo.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

import java.util.Optional;


public class MerchandiseRepositoryUnitTests {

    @Mock
    private DynamoDbEnhancedClient client;

    @Mock
    private MerchandiseRepository merchandiseRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void getMerchandiseInfoBySkuSuccess() {
//        Mockito.when(client.load(Merchandise.class,"123")).thenReturn(null);
//        Assertions.assertDoesNotThrow(() -> merchandiseRepository.getMerchandiseInfoBySku("123"));
//        Assertions.assertNull(merchandiseRepository.getMerchandiseInfoBySku("123"));
//    }

    @Test void getMerchandiseInfoBySkuFail() {
//        Mockito.when(client.load(Merchandise.class,"123")).thenThrow(new AmazonClientException("message"));
//        System.out.println(merchandiseRepository.getMerchandiseInfoBySku("123"));
//        Assertions.assertThrows(RuntimeException.class, () -> merchandiseRepository.getMerchandiseInfoBySku("123"));
    }
}
