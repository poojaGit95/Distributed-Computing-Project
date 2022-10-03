package com.webapp.hotelreservation.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webapp.hotelreservation.Util.Util;
import com.webapp.hotelreservation.entity.Availability;
import com.webapp.hotelreservation.entity.Booking;
import com.webapp.hotelreservation.entity.Room;
import com.webapp.hotelreservation.exceptions.InvalidDataException;
import com.webapp.hotelreservation.restclient.RestClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HotelReservationClient {

    Logger logger = Logger.getLogger(HotelReservationClient.class.getName());

    private static final String baseUrl = "http://hotelreservationsystemwebservice-env.eba-yqwetpz5.us-west-2.elasticbeanstalk.com";

    //private static final String baseUrl ="http://localhost:8080";

    @Autowired
    private RestClient restClient;

    @Autowired
    Gson gson;

    public List<Room> getRoomsAvailability(String check_in, String check_out){
        String url = baseUrl+"/Availability?check_in="+check_in+"&check_out="+check_out;
        List<Room> roomList = null;
        try {
            logger.log(Level.INFO, "Requesting web service for availability details");
            String response = restClient.GetHttpResponse(url);
            roomList = gson.fromJson(response, new TypeToken<List<Room>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public Booking getBookingDetails(String bookingID){
        String url = baseUrl+"/Bookings?bookingID="+bookingID;
        Booking booking = null;
        try {
            logger.log(Level.INFO, "Requesting web service for booking details");
            String response = restClient.GetHttpResponse(url);
            booking = gson.fromJson(response, Booking.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
        return booking;
    }

    public List<Room> getAllRoomsDetails(){
        String url = baseUrl+"/Rooms";
        List<Room> roomList = null;
        try {
            logger.log(Level.INFO, "Requesting web service for room details");
            String response = restClient.GetHttpResponse(url);
            roomList = gson.fromJson(response, new TypeToken<List<Room>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public boolean addRoomBooking(Booking booking){
        String url = baseUrl+"/Bookings";
        String bookingData = gson.toJson(booking);
        logger.log(Level.INFO, "Requesting web service to create new booking");
        return restClient.PostHttpRequest(url, bookingData);
    }
}
