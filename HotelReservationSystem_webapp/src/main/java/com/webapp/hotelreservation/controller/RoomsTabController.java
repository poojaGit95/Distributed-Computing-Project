package com.webapp.hotelreservation.controller;

import com.webapp.hotelreservation.client.HotelReservationClient;
import com.webapp.hotelreservation.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

@Controller
public class RoomsTabController {

    @Autowired
    HotelReservationClient client;

    @RequestMapping(value="/rooms", method= RequestMethod.GET)
    public String roomsTab(ModelMap model){
        List<Room> rooms = client.getAllRoomsDetails();

        for (Room room : rooms) {
            if (room.getRoomType().equalsIgnoreCase("SINGLE") ||
                    room.getRoomType().equalsIgnoreCase("DELUXE") ||
                    room.getRoomType().equalsIgnoreCase("SUITE")){
                model.put(room.getRoomType()+"Price", room.getPrice());
                model.put(room.getRoomType()+"Type", room.getRoomType().toUpperCase(Locale.ROOT));
                model.put(room.getRoomType()+"Facility", room.getFacilities());
                model.put(room.getRoomType()+"Capacity", room.getCapacity());
            }
        }
        return "Rooms";
    }

}
