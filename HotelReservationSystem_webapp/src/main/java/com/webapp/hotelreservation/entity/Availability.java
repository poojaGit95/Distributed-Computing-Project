package com.webapp.hotelreservation.entity;

import org.json.JSONObject;

public class Availability {

    private String date;
    private JSONObject availabilityDetails;


    public Availability(String date, JSONObject availabilityDetails) {
        this.date = date;
        this.availabilityDetails = availabilityDetails;
    }

    public Availability() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public JSONObject getAvailabilityDetails() {
        return availabilityDetails;
    }

    public void setAvailabilityDetails(JSONObject availabilityDetails) {
        this.availabilityDetails = availabilityDetails;
    }



}
