package com.example.demo.repository;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.demo.model.Merchandise;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;


public class MerchandiseRepositoryUnitTests {

    @Mock
    private DynamoDBMapper client;

    @Mock
    private MerchandiseRepository merchandiseRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getMerchandiseInfoBySkuSuccess() {
        Mockito.when(client.load(Merchandise.class,"123")).thenReturn(null);
        Assertions.assertDoesNotThrow(() -> merchandiseRepository.getMerchandiseInfoBySku("123"));
        Assertions.assertNull(merchandiseRepository.getMerchandiseInfoBySku("123"));
    }

    @Test void getMerchandiseInfoBySkuFail() {
//        Mockito.when(client.load(Merchandise.class,"123")).thenThrow(new AmazonClientException("message"));
//        System.out.println(merchandiseRepository.getMerchandiseInfoBySku("123"));
//        Assertions.assertThrows(RuntimeException.class, () -> merchandiseRepository.getMerchandiseInfoBySku("123"));
    }
}
