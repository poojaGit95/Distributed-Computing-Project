package com.project.hotelreservationsystem.servicehelper;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.project.hotelreservationsystem.entity.Booking;
import com.project.hotelreservationsystem.exceptions.InvalidDataException;
import com.project.hotelreservationsystem.util.Util;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Random;

public class RoomBookingServiceHelper {

    public Item convertBookingDetailsToTableItem(Booking person){
        Item item = new Item().withPrimaryKey("BookingID", person.getBookingID())
                .withString("FirstName", person.getFirstName())
                .withString("LastName", person.getLastName())
                .withString("Address", person.getAddress())
                .withString("City", person.getCity())
                .withString("State", person.getState())
                .withString("Country", person.getCountry())
                .withString("Pincode", person.getPincode())
                .withString("Phone", person.getPhone())
                .withString("Email", person.getEmail())
                .withString("check_in", person.getCheck_in())
                .withString("check_out", person.getCheck_out())
                .withInt("rooms_booked", person.getRooms_booked())
                .withString("room_type", person.getRoomType())
                .withInt("adults_count", person.getAdults_count())
                .withDouble("Price", person.getTotal_price());
        return item;
    }

    public Booking convertUserInputToBookingObject(Booking booking) throws InvalidDataException {
        Random random = new Random();
        int randomNumber = random.nextInt(999999);
        String bookingID = "BKD" + String.format("%06d", randomNumber);
        booking.setBookingID(bookingID);
        String roomType = booking.getRoomType();
        booking.setRoomType(roomType.toUpperCase(Locale.ROOT));
        validateBookingDetails(booking);
        return booking;
    }

    private void validateBookingDetails(Booking booking) throws InvalidDataException {
        if(!StringUtils.isAlphaSpace(booking.getFirstName()) || !StringUtils.isAlphaSpace(booking.getLastName())
        || !StringUtils.isNumeric(booking.getPhone()) || !StringUtils.isAlphaSpace(booking.getCity())
                || !StringUtils.isAlphaSpace(booking.getState()) || !StringUtils.isAlphaSpace(booking.getCountry())
                || !StringUtils.isNumeric(booking.getPincode())) {
            throw new InvalidDataException("Invalid user data");
        }
        if (booking.getRooms_booked()<=0 || booking.getAdults_count()<=0){
            throw new InvalidDataException("Invalid booking data");
        }

        String currentDate = Util.getCurrentDate();
        if(Util.isAfter(booking.getCheck_in(), booking.getCheck_out()) || !Util.isAfter(booking.getCheck_in(), currentDate)
        || !Util.isAfter(booking.getCheck_out(), currentDate)){
            throw new InvalidDataException("Invalid checkin/checkout data");
        }

    }

    public Booking convertTableItemToBookingObject(Item item){
        Booking booking = new Booking();
        booking.setBookingID(item.getString("BookingID"));
        booking.setFirstName(item.getString("FirstName"));
        booking.setLastName(item.getString("LastName"));
        booking.setAddress(item.getString("Address"));
        booking.setCity(item.getString("City"));
        booking.setState(item.getString("State"));
        booking.setCountry(item.getString("Country"));
        booking.setPincode(item.getString("Pincode"));
        booking.setPhone(item.getString("Phone"));
        booking.setEmail(item.getString("Email"));
        booking.setCheck_in(item.getString("check_in"));
        booking.setCheck_out(item.getString("check_out"));
        booking.setRooms_booked(item.getInt("rooms_booked"));
        booking.setRoomType(item.getString("room_type"));
        booking.setAdults_count(item.getInt("adults_count"));
        booking.setTotal_price(item.getDouble("Price"));
        return booking;
    }
}
