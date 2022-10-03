package com.project.hotelreservationsystem.servicehelper;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.project.hotelreservationsystem.entity.Availability;
import com.project.hotelreservationsystem.entity.Room;
import com.project.hotelreservationsystem.exceptions.InvalidDataException;
import com.project.hotelreservationsystem.services.RoomService;
import com.project.hotelreservationsystem.util.Util;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RoomAvailabilityServiceHelper {

    @Autowired
    private RoomService roomService;

    public List<Item> convertRoomAvailabilityDetailsToTableItem(String dateToAdd, int daysCount) {
        List<Item> items = new ArrayList<>();
        List<String> dates = Util.getNextDates(dateToAdd, daysCount);
        dates.add(dateToAdd);
        List<Room> rooms = roomService.getAllRoomRecords();
        JSONObject availabilityDetails = new JSONObject();
        for (Room room: rooms) {
            availabilityDetails.put(room.getRoomType(), room.getTotalRooms());
        }
        for (String date: dates) {
            Item item = new Item().withPrimaryKey("AvailabilityDate", date)
                            .withJSON("AvailabilityDetails", availabilityDetails.toString())
                                    .withInt("Version", 0);
            items.add(item);
        }
        return items;
    }

    public List<Item> getItemsToBeUpdated(List<Availability> availabilities, String roomTypeBooked, int roomsBookedCount) throws InvalidDataException {
        List<Item> items = new ArrayList<>();
        for (Availability availability: availabilities) {
            JSONObject details = availability.getAvailabilityDetails();
            int value = details.getInt(roomTypeBooked);
            if(value<roomsBookedCount){
                throw new InvalidDataException("No rooms available");
            }
            value = value-roomsBookedCount;
            details.put(roomTypeBooked, value);
            int version = availability.getVersion()+1;
            Item item = new Item().withPrimaryKey("AvailabilityDate", availability.getDate())
                    .withJSON("AvailabilityDetails", details.toString())
                    .withInt("Version", version);
            items.add(item);
        }
        return items;
    }

    public Availability convertUserInputToAvailabilityObject(String date, JSONObject availabilityDetails){
        Availability availability = new Availability();
        availability.setDate(date);
        availability.setAvailabilityDetails(availabilityDetails);
        return availability;
    }

    public Availability convertTableItemToAvailabilityObject(Item item){
        Availability availability = new Availability();
        availability.setDate(item.getString("AvailabilityDate"));
        availability.setAvailabilityDetails(new JSONObject(item.getJSON("AvailabilityDetails")));
        availability.setVersion(item.getInt("Version"));
        return availability;
    }

    public JSONObject getRoomAvailability(List<Item> items){
        Map<String, Integer> roomCount = new HashMap<>();
        for (Item item: items) {
            JSONObject availabilityDetails = new JSONObject(item.getJSON("AvailabilityDetails"));
            for (String key: availabilityDetails.keySet()) {
                if(roomCount.containsKey(key)){
                    int value = roomCount.get(key);
                    if(value>availabilityDetails.getInt(key)){
                        roomCount.put(key, availabilityDetails.getInt(key));
                    }
                }else{
                    roomCount.put(key, availabilityDetails.getInt(key));
                }
            }
        }
        JSONObject roomAvailability = new JSONObject();
        for (Map.Entry<String, Integer> roomMap :roomCount.entrySet()) {
            roomAvailability.put(roomMap.getKey(), roomMap.getValue());
        }
        return roomAvailability;
    }

}
