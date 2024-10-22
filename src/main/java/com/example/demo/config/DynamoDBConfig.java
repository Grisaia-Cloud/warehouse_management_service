package com.example.demo.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {

    @Bean(name = "merchandiseDynamoDB")
    public DynamoDBMapper merchandiseClient() {
        return new DynamoDBMapper(merchandiseDynamoDBMapper());
    }

    @Bean(name = "inventoryDynamoDB")
    public DynamoDBMapper inventoryClient() {
        return new DynamoDBMapper(inventoryDynamoDBMapper());
    }

    private AmazonDynamoDB merchandiseDynamoDBMapper() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration((new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "claude"))).withCredentials((new AWSStaticCredentialsProvider(new BasicAWSCredentials("claude", "claude")))).build();
        return client;
    }

    private AmazonDynamoDB inventoryDynamoDBMapper() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration((new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "claude"))).withCredentials((new AWSStaticCredentialsProvider(new BasicAWSCredentials("claude", "claude")))).build();
        return client;
    }
}
