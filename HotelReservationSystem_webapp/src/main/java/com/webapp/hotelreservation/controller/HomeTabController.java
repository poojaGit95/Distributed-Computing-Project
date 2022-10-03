package com.webapp.hotelreservation.controller;

import com.webapp.hotelreservation.client.HotelReservationClient;
import com.webapp.hotelreservation.entity.Booking;
import com.webapp.hotelreservation.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

@Controller
public class HomeTabController {

    @Autowired
    HotelReservationClient client;

    @RequestMapping(value="/home", method= RequestMethod.GET)
    public String homeTab(){
        return "Home";
    }


    @RequestMapping(value="/select", method=RequestMethod.POST)
    public String checkAvailability(@RequestParam("CheckInDate") String checkIn,
                                    @RequestParam("CheckOutDate") String checkOut,
                                    ModelMap model){
        List<Room> roomList = client.getRoomsAvailability(checkIn, checkOut);
        if(roomList == null || roomList.size()<=0){
            model.put("errorMsg", "Invalid dates selected. Note Bookings are taken for dates falling in next two months from current date.");
            return "Home";
        }
        for (Room room : roomList) {
            model.put(room.getRoomType(), room.getAvailableUnits());
            model.put(room.getRoomType()+"Price", room.getPrice());
            model.put(room.getRoomType()+"Type", room.getRoomType().toUpperCase(Locale.ROOT));
            model.put(room.getRoomType()+"Facility", room.getFacilities());
            model.put(room.getRoomType()+"Capacity", room.getCapacity());
        }
        model.put("CheckInDate", checkIn);
        model.put("CheckOutDate", checkOut);
        return "RoomSelection";
    }

    @RequestMapping(value="/reserve", method= RequestMethod.POST)
    public String bookNow(@RequestParam("CheckInDate") String checkIn,
                          @RequestParam("CheckOutDate") String checkOut,
                          @RequestParam("RoomType") String roomType,
                          @RequestParam("Price") String price,
                          ModelMap model){
        System.out.println(checkIn + "   " + checkOut + " " + roomType);
        model.put("CheckInDate", checkIn);
        model.put("CheckOutDate", checkOut);
        model.put("RoomType", roomType);
        model.put("Price", price);
        return "BookingDetails";
    }

    @RequestMapping(value="/book", method=RequestMethod.POST)
    public String reserveRoom(Booking booking, ModelMap model){
        boolean result = client.addRoomBooking(booking);
        if (result){
            model.put("successMsg", "An email has been sent to the email ID provided. Please check your inbox for details.");
        }else{
            model.put("errorMsg", "Booking could not be completed. Please try again later.");
        }
        return "BookingConfirmation";
    }

}
