package com.project.hotelreservationsystem.services;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.project.hotelreservationsystem.cloudservices.DynamoDBService;
import com.project.hotelreservationsystem.entity.Room;
import com.project.hotelreservationsystem.exceptions.NoItemFoundException;
import com.project.hotelreservationsystem.servicehelper.RoomServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoomService {

    Logger logger = Logger.getLogger(RoomService.class.getName());

    private static final String DYNAMO_DB_ROOM_TABLE_NAME = "ROOM_DETAILS";

    private static final String ROOM_TABLE_PRIMARY_KEY = "RoomType";

    @Autowired
    private DynamoDBService dynamoDBService;

    @Autowired
    private RoomServiceHelper roomServiceHelper;

    public void initializeService(){
        logger.log(Level.INFO, "Creating " + DYNAMO_DB_ROOM_TABLE_NAME + " table");
        dynamoDBService.createTable(DYNAMO_DB_ROOM_TABLE_NAME, ROOM_TABLE_PRIMARY_KEY);
        preLoadRoomTableWithData();
    }

    private void preLoadRoomTableWithData(){
        addRoomDetails(new Room("SINGLE", "Single Bed", 1, 99.0, 30));
        addRoomDetails(new Room("DELUXE", "Queen-size Bed", 2, 149.0, 20));
        addRoomDetails(new Room("SUITE", "King-size Bed", 4, 199.0, 10));
    }

    public void addRoomDetails(Room room){
        logger.log(Level.INFO, "adding new room type to DB");
        Item item  = RoomServiceHelper.convertRoomDetailsToTableItem(room);
        dynamoDBService.insertSingleItemIntoTable(DYNAMO_DB_ROOM_TABLE_NAME, item);
    }

    public Room fetchRoomDetails(String roomType) throws NoItemFoundException {
        Room room = null;
        try{
            logger.log(Level.INFO, "fetching room details from DB");
            Item item = dynamoDBService.queryItemFromTable(DYNAMO_DB_ROOM_TABLE_NAME, ROOM_TABLE_PRIMARY_KEY, roomType);
            room = roomServiceHelper.convertTableItemToRoomObject(item, roomType);
        }catch(NullPointerException e){
            throw new NoItemFoundException("Such room type does not exist");
        }
        return room;
    }

    public List<Room> getAllRoomRecords(){
        logger.log(Level.INFO, "fetching all room types from DB");
        List<Map<String, AttributeValue>> items = dynamoDBService.scanTable(DYNAMO_DB_ROOM_TABLE_NAME);
        List<Room> rooms = roomServiceHelper.convertScanResultToRoomObjects(items);
        return rooms;
    }
}
