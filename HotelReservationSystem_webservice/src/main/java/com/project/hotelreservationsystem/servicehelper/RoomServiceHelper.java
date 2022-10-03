package com.project.hotelreservationsystem.servicehelper;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.hotelreservationsystem.entity.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RoomServiceHelper {

    public static Item convertRoomDetailsToTableItem(Room room){
        Item item = new Item().withPrimaryKey("RoomType", room.getRoomType())
                .withString("Facilities", room.getFacilities())
                .withInt("Capacity", room.getCapacity())
                .withDouble("Price", room.getPrice())
                .withInt("Total_rooms", room.getTotalRooms());
        return item;
    }

    public Room convertUserInputToRoomObject(String roomType, String facilities, int capacity, double price, int totalRooms){
        Room room = new Room();
        room.setRoomType(roomType.toUpperCase(Locale.ROOT));
        room.setCapacity(capacity);
        room.setFacilities(facilities);
        room.setTotalRooms(totalRooms);
        room.setPrice(price);
        return room;
    }

    public Room convertTableItemToRoomObject(Item item, String roomType){
        Room room = new Room();
        room.setRoomType(roomType.toUpperCase(Locale.ROOT));
        room.setFacilities(item.getString("Facilities"));
        room.setCapacity(item.getInt("Capacity"));
        room.setPrice(item.getDouble("Price"));
        room.setTotalRooms(item.getInt("Total_rooms"));
        return room;
    }

    public List<Room> convertScanResultToRoomObjects(List<Map<String, AttributeValue>> items){
        List<Room> rooms = new ArrayList<>();
        for (Map<String, AttributeValue> item : items) {
            Item roomItem = new Item();
            for (Map.Entry<String, AttributeValue> i:item.entrySet()) {
                if(i.getValue().getS() != null && !i.getValue().getS().isEmpty()){
                    roomItem.withString(i.getKey(), i.getValue().getS());
                }else if(i.getValue().getN() != null && !i.getValue().getN().isEmpty()){
                    roomItem.withString(i.getKey(), i.getValue().getN());
                }
            }
            Room room = convertTableItemToRoomObject(roomItem, roomItem.getString("RoomType"));
            rooms.add(room);
        }
        return rooms;
    }
}
