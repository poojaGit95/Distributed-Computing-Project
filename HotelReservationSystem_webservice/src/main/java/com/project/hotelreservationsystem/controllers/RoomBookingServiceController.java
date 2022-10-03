package com.project.hotelreservationsystem.controllers;

import com.google.gson.Gson;
import com.project.hotelreservationsystem.entity.Booking;
import com.project.hotelreservationsystem.entity.Room;
import com.project.hotelreservationsystem.exceptions.InvalidDataException;
import com.project.hotelreservationsystem.exceptions.NoItemFoundException;
import com.project.hotelreservationsystem.services.RoomBookingService;
import com.project.hotelreservationsystem.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomBookingServiceController {

    @Autowired
    RoomBookingService roomBookingService;

    @Autowired
    private Gson gson;

    @GetMapping("/Bookings")
    public Booking getRoomBooking(@RequestParam(name="bookingID") String bookingID) throws NoItemFoundException {
        Booking booking = roomBookingService.getBookingDetails(bookingID);
        return booking;
    }

    @PostMapping(path ="/Bookings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addRoomBooking(@RequestBody String bookingData) throws InvalidDataException {
        Booking booking = gson.fromJson(bookingData, Booking.class);
        roomBookingService.bookRoom(booking);
        return new ResponseEntity<>("Booking successfully added", HttpStatus.OK);
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
