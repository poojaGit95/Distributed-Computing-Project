package com.project.hotelreservationsystem.cloudservices;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.BatchWriteItemSpec;
import com.amazonaws.services.dynamodbv2.model.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DynamoDBService {

    private final AmazonDynamoDB dynamoDBClient;

    private DynamoDB dynamoDB;

    public DynamoDBService(){
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("xxx", "xxxx");

        if(false){
            this.dynamoDBClient = AmazonDynamoDBClientBuilder.standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
                    .build();
        }else{
            this.dynamoDBClient = AmazonDynamoDBClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                    .withRegion(Regions.US_WEST_2)
                    .build();
        }
        dynamoDB = new DynamoDB(dynamoDBClient);
    }

    public void createTable(String tableName, String primaryKey){
        List<AttributeDefinition> attributes = Arrays.asList(
                new AttributeDefinition(primaryKey, ScalarAttributeType.S));
        List<KeySchemaElement> keySchema = Arrays.asList(
                new KeySchemaElement(primaryKey, KeyType.HASH));
        try{
            CreateTableRequest tableRequest = new CreateTableRequest()
                    .withTableName(tableName)
                    .withAttributeDefinitions(attributes)
                    .withKeySchema(keySchema)
                    .withProvisionedThroughput(new ProvisionedThroughput(10L, 10L));
            Table dbTable = dynamoDB.createTable(tableRequest);
            dbTable.waitForActive();
        } catch (Exception e) {
            System.err.println("Unable to create table: ");
            System.err.println(e.getMessage());
        }
    }

    public void insertSingleItemIntoTable(String tableName, Item tableItem){
        try{
            Table dbTable = dynamoDB.getTable(tableName);
            PutItemOutcome outcome = dbTable.putItem(tableItem);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void insertMultipleItemsIntoTable(String tableName,List<Item> tableItems){
        Table dbTable = dynamoDB.getTable(tableName);
        TableWriteItems tableWriteItems = new TableWriteItems(tableName);
        tableWriteItems.withItemsToPut(tableItems);
        BatchWriteItemSpec batchWriteItemSpec = new BatchWriteItemSpec();
        batchWriteItemSpec.withTableWriteItems(tableWriteItems);
        BatchWriteItemOutcome outcome = dynamoDB.batchWriteItem(batchWriteItemSpec);
        do {
            Map<String, List<WriteRequest>> unprocessedItems = outcome.getUnprocessedItems();
            if (outcome.getUnprocessedItems().size() == 0) {
                System.out.println("No unprocessed items found");
            }
            else {
                outcome = dynamoDB.batchWriteItemUnprocessed(unprocessedItems);
            }
        } while (outcome.getUnprocessedItems().size() > 0);
    }


    public List<Map<String, AttributeValue>> scanTable(String tableName) {
        ScanRequest scanRequest = new ScanRequest()
                .withTableName(tableName);
        ScanResult result = dynamoDBClient.scan(scanRequest);
        return result.getItems();
    }

    public Item queryItemFromTable(String tableName, String primaryKey, String primaryKeyValue){
        Table dbTable = dynamoDB.getTable(tableName);
        Item item = dbTable.getItem(primaryKey, primaryKeyValue);
        return item;
    }

    public void deleteItemsFromTable(String tableName, String primaryKey, String primaryKeyValue){
        Table dbTable = dynamoDB.getTable(tableName);
        dbTable.deleteItem(primaryKey, primaryKeyValue);
    }

    public void deleteTable(String tableName){
        Table dbTable = dynamoDB.getTable(tableName);
        DeleteTableResult result = dbTable.delete();
    }

}
