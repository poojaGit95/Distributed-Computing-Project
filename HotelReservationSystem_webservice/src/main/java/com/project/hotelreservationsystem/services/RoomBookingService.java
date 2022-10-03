package com.project.hotelreservationsystem.services;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.project.hotelreservationsystem.cloudservices.DynamoDBService;
import com.project.hotelreservationsystem.cloudservices.EmailService;
import com.project.hotelreservationsystem.cloudservices.SimpleEmailService;
import com.project.hotelreservationsystem.entity.Booking;
import com.project.hotelreservationsystem.exceptions.InvalidDataException;
import com.project.hotelreservationsystem.exceptions.NoItemFoundException;
import com.project.hotelreservationsystem.servicehelper.RoomBookingServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Level;
import java.util.logging.Logger;


public class RoomBookingService {

    Logger logger = Logger.getLogger(RoomBookingService.class.getName());

    private static final String DYNAMO_DB_BOOKING_TABLE_NAME = "BOOKING_DETAILS";

    private static final String BOOKING_TABLE_PRIMARY_KEY = "BookingID";

    @Autowired
    private DynamoDBService dynamoDBService;

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private RoomBookingServiceHelper roomBookingServiceHelper;

    @Autowired
    private RoomAvailabilityService roomAvailabilityService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TransactionService transactionService;

    public void initializeService(){
        dynamoDBService.createTable(DYNAMO_DB_BOOKING_TABLE_NAME, BOOKING_TABLE_PRIMARY_KEY);
    }

    public void bookRoom(Booking booking) throws InvalidDataException {
        logger.log(Level.INFO, "adding new booking to DB");
        booking = roomBookingServiceHelper.convertUserInputToBookingObject(booking);
        //Item item = roomBookingServiceHelper.convertBookingDetailsToTableItem(booking);
        //roomAvailabilityService.updateRoomAvailability(booking.getCheck_in(), booking.getCheck_out(), booking.getRoomType(), booking.getRooms_booked());
        //dynamoDBService.insertSingleItemIntoTable(DYNAMO_DB_BOOKING_TABLE_NAME,item);
        transactionService.createBooking(booking);
        emailService.sendMail(booking.getEmail(), booking);
    }

    public Booking getBookingDetails(String bookingID) throws NoItemFoundException{
        Booking booking=null;
        try{
            logger.log(Level.INFO, "fetching booking details from DB");
            Item item = dynamoDBService.queryItemFromTable(DYNAMO_DB_BOOKING_TABLE_NAME, BOOKING_TABLE_PRIMARY_KEY, bookingID);
            booking = roomBookingServiceHelper.convertTableItemToBookingObject(item);
        }catch(NullPointerException e){
            logger.log(Level.WARNING, "Error occurred");
            throw new NoItemFoundException("No Booking under this booking ID");
        }
        return booking;
    }

}
