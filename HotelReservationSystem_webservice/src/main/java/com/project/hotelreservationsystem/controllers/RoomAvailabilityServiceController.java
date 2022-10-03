package com.project.hotelreservationsystem.controllers;

import com.google.gson.Gson;
import com.project.hotelreservationsystem.exceptions.InvalidDataException;
import com.project.hotelreservationsystem.services.RoomAvailabilityService;
import com.project.hotelreservationsystem.services.RoomService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.hotelreservationsystem.entity.Room;

import java.util.List;

@RestController
public class RoomAvailabilityServiceController {

    @Autowired
    RoomAvailabilityService roomAvailabilityService;

    @Autowired
    RoomService roomService;

    @Autowired
    Gson gson;

    @PostMapping(path="/Availability", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addRoomAvailability(@RequestBody String date) throws InvalidDataException {
        JSONObject object = new JSONObject(date);
        String dateToAdd = object.get("date").toString();
        roomAvailabilityService.addRoomAvailabilityRecord(dateToAdd);
        return new ResponseEntity<>("New availability added successfully", HttpStatus.OK);
    }

    @PutMapping(path="/Availability", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateRoomAvailability(@RequestBody String details) throws InvalidDataException {
        JSONObject object = new JSONObject(details);
        String date_in = object.get("check_in").toString();
        String date_out = object.get("check_out").toString();
        String roomType = object.get("roomType").toString();
        int roomCount = object.getInt("roomCount");
        roomAvailabilityService.updateRoomAvailability(date_in, date_out, roomType, roomCount);
        return new ResponseEntity<>("Availability updated successfully", HttpStatus.OK);
    }

    @GetMapping("/Availability")
    public List<Room> getRoomAvailability(@RequestParam(name="check_in") String check_in,
                                          @RequestParam(name="check_out") String check_out) throws InvalidDataException {
        JSONObject availabilityDetails = roomAvailabilityService.checkRoomAvailability(check_in, check_out);
        List<Room> roomList = roomService.getAllRoomRecords();
        addAvailabilityDetailsToRoomList(roomList, availabilityDetails);
        return roomList;
    }

    @ExceptionHandler(value = InvalidDataException.class)
    public ResponseEntity handleBlogAlreadyExistsException(InvalidDataException invalidDataException) {
        return new ResponseEntity(invalidDataException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private void addAvailabilityDetailsToRoomList(List<Room> roomList, JSONObject availabilityDetails) {
        for (Room room : roomList) {
            if(availabilityDetails.has(room.getRoomType()))
                room.setAvailableUnits(availabilityDetails.getInt(room.getRoomType()));
        }
    }
}
