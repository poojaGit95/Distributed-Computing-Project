package com.project.hotelreservationsystem.entity;

import org.json.JSONObject;

public class Availability {

    private String date;
    private JSONObject availabilityDetails;
    private int version;

    public Availability(String date, JSONObject availabilityDetails) {
        this.date = date;
        this.availabilityDetails = availabilityDetails;

    }

    public Availability(String date, JSONObject availabilityDetails, int version) {
        this.date = date;
        this.availabilityDetails = availabilityDetails;
        this.version = 0;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }


}
