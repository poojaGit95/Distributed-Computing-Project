package com.project.hotelreservationsystem.application;

import com.project.hotelreservationsystem.cloudservices.EmailService;
import com.project.hotelreservationsystem.services.RoomAvailabilityService;
import com.project.hotelreservationsystem.services.RoomBookingService;
import com.project.hotelreservationsystem.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

public class ApplicationServiceInitializer {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomBookingService roomBookingService;

    @Autowired
    private RoomAvailabilityService roomAvailabilityService;

    public void initializeHotelReservationApplication(){
        roomService.initializeService();
        roomAvailabilityService.initializeService();
        roomBookingService.initializeService();
    }


}
