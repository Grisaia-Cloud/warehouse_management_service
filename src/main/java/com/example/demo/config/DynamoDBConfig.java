package com.example.demo.config;

import com.example.demo.model.Inventory;
import com.example.demo.model.Merchandise;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class DynamoDBConfig {

    public static final TableSchema<Merchandise> MERCHANDISE_TABLE_SCHEMA = TableSchema.fromClass(Merchandise.class);
    public static final TableSchema<Inventory> INVENTORY_TABLE_SCHEMA = TableSchema.fromClass(Inventory.class);

    @Bean(name = "merchandiseDynamoDB")
    public DynamoDbEnhancedClient merchandiseClient() {
        return DynamoDbEnhancedClient.builder().dynamoDbClient(
                        DynamoDbClient.builder()
                                .region(Region.US_WEST_2)
                                .endpointOverride(URI.create("http://localhost:8000"))
                                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("claude", "claude"))).build()
                )
                .build();
    }

    @Bean(name = "inventoryDynamoDB")
    public DynamoDbEnhancedClient inventoryClient() {
        return DynamoDbEnhancedClient.builder().dynamoDbClient(
                        DynamoDbClient.builder()
                                .region(Region.US_WEST_2)
                                .endpointOverride(URI.create("http://localhost:8000"))
                                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("claude", "claude"))).build()
                )
                .build();
    }
}
