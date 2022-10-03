package com.webapp.hotelreservation.controller;

import com.webapp.hotelreservation.client.HotelReservationClient;
import com.webapp.hotelreservation.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookingsTabController {

    @Autowired
    HotelReservationClient client;

    @RequestMapping(value="/bookings", method= RequestMethod.GET)
    public String bookingsTab(){
        return "Bookings";
    }

    @RequestMapping(value="/bookings", method=RequestMethod.POST)
    public String getBookings(@RequestParam("bookingID") String bookingID, ModelMap model){
        Booking booking = client.getBookingDetails(bookingID);
        if (booking==null){
            model.put("errorMsg", "Invalid Booking Number");

        }else{
            model.put("booking1", "Booking Details: "+ booking.getFirstName() + " " + booking.getLastName());
            model.put("booking2", "Booking Dates: " + booking.getCheck_in() + " to " + booking.getCheck_out());
            model.put("booking3", "Room Details: " + booking.getRooms_booked() + " " + booking.getRoomType() + " for " + booking.getAdults_count() + " adults ");
        }
        return "Bookings";
    }




}
