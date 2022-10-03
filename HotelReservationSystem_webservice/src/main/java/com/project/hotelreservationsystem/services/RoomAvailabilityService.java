package com.project.hotelreservationsystem.services;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.project.hotelreservationsystem.cloudservices.DynamoDBService;
import com.project.hotelreservationsystem.entity.Availability;
import com.project.hotelreservationsystem.exceptions.InvalidDataException;
import com.project.hotelreservationsystem.servicehelper.RoomAvailabilityServiceHelper;
import com.project.hotelreservationsystem.util.Util;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoomAvailabilityService {

    Logger logger = Logger.getLogger(RoomAvailabilityService.class.getName());

    private static final String DYNAMO_DB_ROOM_AVAILABILITY_TABLE_NAME = "ROOM_AVAILABILITY_DETAILS";

    private static final String ROOM_AVAILABILITY_TABLE_PRIMARY_KEY = "AvailabilityDate";

    @Autowired
    private DynamoDBService dynamoDBService;

    @Autowired
    private RoomAvailabilityServiceHelper roomAvailabilityServiceHelper;


    public void initializeService() {
        logger.log(Level.INFO, "Creating " + DYNAMO_DB_ROOM_AVAILABILITY_TABLE_NAME + " table");
        dynamoDBService.createTable(DYNAMO_DB_ROOM_AVAILABILITY_TABLE_NAME, ROOM_AVAILABILITY_TABLE_PRIMARY_KEY);
        preLoadRoomAvailabilityTableWithData();
    }

    private void preLoadRoomAvailabilityTableWithData() {
        String date = Util.getCurrentDate();
        List<Item> items = roomAvailabilityServiceHelper.convertRoomAvailabilityDetailsToTableItem(date,20);
        dynamoDBService.insertMultipleItemsIntoTable(DYNAMO_DB_ROOM_AVAILABILITY_TABLE_NAME, items);
    }

    public void addRoomAvailabilityRecord(String date) throws InvalidDataException {
        logger.log(Level.INFO, "adding availability details to DB");
        String currentDate = Util.getCurrentDate();
        if (!Util.isAfter(date, currentDate)){
            throw new InvalidDataException("Invalid checkin/checkout dates");
        }
        Item item = roomAvailabilityServiceHelper.convertRoomAvailabilityDetailsToTableItem(date,0).get(0);
        dynamoDBService.insertSingleItemIntoTable(DYNAMO_DB_ROOM_AVAILABILITY_TABLE_NAME, item);
    }

    public void updateRoomAvailability(String check_in, String check_out, String roomTypeBooked, int roomsBookedCount) throws InvalidDataException {
        logger.log(Level.INFO, "updating availability details to DB");
        List<Availability> availabilities = new ArrayList<>();
        String current = check_in;
        while(!current.equals(check_out)){
            Availability availability = getAvailabilityRecord(current);
            current = Util.getNextDates(current, 1).get(0);
            availabilities.add(availability);
        }
        List<Item> items = roomAvailabilityServiceHelper.getItemsToBeUpdated(availabilities, roomTypeBooked, roomsBookedCount);
        dynamoDBService.insertMultipleItemsIntoTable(DYNAMO_DB_ROOM_AVAILABILITY_TABLE_NAME, items);
    }

    public Availability getAvailabilityRecord(String date){
        logger.log(Level.INFO, "fetching availability details from DB");
        Item item = dynamoDBService.queryItemFromTable(DYNAMO_DB_ROOM_AVAILABILITY_TABLE_NAME, ROOM_AVAILABILITY_TABLE_PRIMARY_KEY, date);
        Availability availability = roomAvailabilityServiceHelper.convertTableItemToAvailabilityObject(item);
        return availability;
    }

    public JSONObject checkRoomAvailability(String check_in_date, String check_out_date) throws InvalidDataException {
        logger.log(Level.INFO, "fetching availability details from DB");
        String currentDate = Util.getCurrentDate();
        if (Util.isAfter(check_in_date, check_out_date) || !Util.isAfter(check_in_date, currentDate) ||
                !Util.isAfter(check_out_date, currentDate)){
            throw new InvalidDataException("Invalid checkin/checkout dates");
        }
        String current = check_in_date;
        List<Item> items = new ArrayList<>();
        while (!current.equals(check_out_date)){
            Item item = dynamoDBService.queryItemFromTable(DYNAMO_DB_ROOM_AVAILABILITY_TABLE_NAME, ROOM_AVAILABILITY_TABLE_PRIMARY_KEY, current);
            if(item==null){
                throw new InvalidDataException("Bookings can be taken only for dates falling in next two months from current date.");
            }
            items.add(item);
            current = Util.getNextDates(current, 1).get(0);
        }
        JSONObject roomAvailability = roomAvailabilityServiceHelper.getRoomAvailability(items);
        return roomAvailability;
    }


}
