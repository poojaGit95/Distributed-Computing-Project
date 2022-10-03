package com.project.hotelreservationsystem.services;

import com.project.hotelreservationsystem.cloudservices.DynamoDBService;
import com.project.hotelreservationsystem.cloudservices.DynamoDBTransactionService;
import com.project.hotelreservationsystem.entity.Availability;
import com.project.hotelreservationsystem.entity.Booking;
import com.project.hotelreservationsystem.util.Util;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TransactionService {

    @Autowired
    private RoomAvailabilityService roomAvailabilityService;

    @Autowired
    private DynamoDBService dynamoDBService;

    @Autowired
    private DynamoDBTransactionService dynamoDBTransactionService;

    public void createBooking(Booking booking){
        List<Availability> availabilities = new ArrayList<>();
        String current = booking.getCheck_in();
        while(!current.equals(booking.getCheck_out())){
            availabilities.add(roomAvailabilityService.getAvailabilityRecord(current));
            current = Util.getNextDates(current, 1).get(0);
        }
        dynamoDBTransactionService.completeBookingTransaction(availabilities, booking);
    }

}
