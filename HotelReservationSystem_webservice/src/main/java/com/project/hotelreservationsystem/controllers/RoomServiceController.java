package com.project.hotelreservationsystem.controllers;

import com.google.gson.Gson;
import com.project.hotelreservationsystem.entity.Room;
import com.project.hotelreservationsystem.exceptions.InvalidDataException;
import com.project.hotelreservationsystem.exceptions.NoItemFoundException;
import com.project.hotelreservationsystem.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomServiceController {

    @Autowired
    RoomService roomService;

    @Autowired
    private Gson gson;

    @PostMapping(path="/Room", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addRoom(@RequestBody String roomData) throws InvalidDataException {
        try{
            Room room = gson.fromJson(roomData, Room.class);
            roomService.addRoomDetails(room);
            return  new ResponseEntity<>("New Room Type added successfully", HttpStatus.OK);
        }catch(Exception e){
            throw new InvalidDataException("Invalid data passed");
        }
    }

    @GetMapping("/Room")
    public Room getRoom(@RequestParam(name="roomType") String roomType) throws NoItemFoundException {
        Room room = null;
        room = roomService.fetchRoomDetails(roomType);
        return room;
    }

    @GetMapping("/Rooms")
    public List<Room> getAllRooms(){
        List<Room> rooms = roomService.getAllRoomRecords();
        return rooms;
    }

    @ExceptionHandler(value = NoItemFoundException.class)
    public ResponseEntity handleBlogAlreadyExistsException(NoItemFoundException noItemFoundException) {
        return new ResponseEntity(noItemFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidDataException.class)
    public ResponseEntity handleBlogAlreadyExistsException(InvalidDataException invalidDataException) {
        return new ResponseEntity(invalidDataException.getMessage(), HttpStatus.BAD_REQUEST);
    }



}
