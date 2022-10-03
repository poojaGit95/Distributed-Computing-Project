package com.project.hotelreservationsystem.cloudservices;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;
import com.project.hotelreservationsystem.entity.Availability;
import com.project.hotelreservationsystem.entity.Booking;
import com.project.hotelreservationsystem.exceptions.InvalidDataException;
import org.json.JSONObject;

import java.util.*;

public class DynamoDBTransactionService {

    private final AmazonDynamoDB dynamoDBClient;

    public DynamoDBTransactionService(){
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("xxx", "xxxxxx");

        if(false){
            this.dynamoDBClient = AmazonDynamoDBClientBuilder.standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
                    .build();
        }else{
            this.dynamoDBClient = AmazonDynamoDBClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                    .withRegion(Regions.US_WEST_2)
                    .build();
        }
    }

    public  List<Update> availabilityUpdate(List<Availability> availabilities, Booking booking){
        List<Update> updates = new ArrayList<>();
        final String TABLE_NAME = "ROOM_AVAILABILITY_DETAILS";
        final String PARTITION_KEY = "AvailabilityDate";
        for (Availability avl: availabilities) {
            JSONObject curAvlDetails = avl.getAvailabilityDetails();
            int value = curAvlDetails.getInt(booking.getRoomType());
            if(value<booking.getRooms_booked()){
                System.out.println("less rooms avl...this shd never happen");
            }
            Map<String, AttributeValue> newAvlDetails = new HashMap<>();
            for (String roomtype: curAvlDetails.keySet()) {
                newAvlDetails.put(roomtype, new AttributeValue().withN(String.valueOf(curAvlDetails.getInt(roomtype))));
            }
            value = value-booking.getRooms_booked();
            newAvlDetails.put(booking.getRoomType(), new AttributeValue().withN(String.valueOf(value)));
            HashMap<String, AttributeValue> availabilityItemKey = new HashMap<>();
            availabilityItemKey.put(PARTITION_KEY, new AttributeValue(avl.getDate()));
            Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
            expressionAttributeValues.put(":new_version", new AttributeValue().withN(String.valueOf(avl.getVersion()+1)));
            expressionAttributeValues.put(":expected_version", new AttributeValue().withN(String.valueOf(avl.getVersion())));
            expressionAttributeValues.put(":new_availability", new AttributeValue().withM(newAvlDetails));
            Update updateAvailability = new Update()
                    .withTableName(TABLE_NAME)
                    .withKey(availabilityItemKey)
                    .withUpdateExpression("SET Version = :new_version , AvailabilityDetails = :new_availability")
                    .withConditionExpression("Version = :expected_version")
                    .withExpressionAttributeValues(expressionAttributeValues)
                    .withReturnValuesOnConditionCheckFailure(ReturnValuesOnConditionCheckFailure.ALL_OLD);
            updates.add(updateAvailability);
        }
        return updates;

    }

    public Put bookingCreation(Booking booking){
        final String PARTITION_KEY = "BookingID";
        final String TABLE_NAME = "BOOKING_DETAILS";

        HashMap<String, AttributeValue> bookingItem = new HashMap<>();
        bookingItem.put(PARTITION_KEY, new AttributeValue(booking.getBookingID()));
        bookingItem.put("FirstName", new AttributeValue(booking.getFirstName()));
        bookingItem.put("LastName", new AttributeValue(booking.getLastName()));
        bookingItem.put("Address", new AttributeValue(booking.getAddress()));
        bookingItem.put("City", new AttributeValue(booking.getCity()));
        bookingItem.put("State", new AttributeValue(booking.getState()));
        bookingItem.put("Country", new AttributeValue(booking.getCountry()));
        bookingItem.put("Pincode", new AttributeValue(booking.getPincode()));
        bookingItem.put("Phone", new AttributeValue(booking.getPhone()));
        bookingItem.put("Email", new AttributeValue(booking.getEmail()));
        bookingItem.put("check_in", new AttributeValue(booking.getCheck_in()));
        bookingItem.put("check_out", new AttributeValue(booking.getCheck_out()));
        bookingItem.put("rooms_booked", new AttributeValue().withN(String.valueOf(booking.getRooms_booked())));
        bookingItem.put("room_type", new AttributeValue(booking.getRoomType()));
        bookingItem.put("adults_count", new AttributeValue().withN(String.valueOf(booking.getAdults_count())));
        bookingItem.put("Price", new AttributeValue().withN(String.valueOf(booking.getTotal_price())));

        Put createOrder = new Put()
                .withTableName(TABLE_NAME)
                .withItem(bookingItem)
                ;
        return createOrder;
    }

    public void completeBookingTransaction(List<Availability> availabilities, Booking booking){
        List<Update> updateAvailabilities = availabilityUpdate(availabilities, booking);
        Put addBooking = bookingCreation(booking);
        runTransactions(updateAvailabilities, addBooking);
    }

    public void runTransactions(List<Update> updateAvailability, Put addBooking){
        Collection<TransactWriteItem> actions = new ArrayList<>();
        for (Update u: updateAvailability) {
            actions.add(new TransactWriteItem().withUpdate(u));
        }
        actions.add(new TransactWriteItem().withPut(addBooking));
        TransactWriteItemsRequest placeOrderTransaction = new TransactWriteItemsRequest()
                .withTransactItems(actions)
                .withReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL);
        try {
            dynamoDBClient.transactWriteItems(placeOrderTransaction);
            System.out.println("Transaction Successful");

        } catch (ResourceNotFoundException rnf) {
            System.err.println("One of the table involved in the transaction is not found" + rnf.getMessage());
        } catch (InternalServerErrorException ise) {
            System.err.println("Internal Server Error" + ise.getMessage());
        } catch (TransactionCanceledException tce) {
            System.out.println("Transaction Canceled " + tce.getMessage());
        }
    }



}
