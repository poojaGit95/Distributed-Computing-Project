package com.project.hotelreservationsystem.entity;

public class Room {
    private String roomType;
    private String facilities;
    private int capacity;
    private double price;
    private int totalRooms;
    private int availableUnits;

    public Room(String roomType, String facilities, int capacity, double price, int totalRooms, int availableUnits) {
        this.roomType = roomType;
        this.facilities = facilities;
        this.capacity = capacity;
        this.price = price;
        this.totalRooms = totalRooms;
        this.availableUnits = availableUnits;
    }

    public Room(String roomType, String facilities, int capacity, double price, int totalRooms) {
        this.roomType = roomType;
        this.facilities = facilities;
        this.capacity = capacity;
        this.price = price;
        this.totalRooms = totalRooms;
        this.availableUnits = 0;
    }

    public Room() {

    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }

    public int getAvailableUnits() {
        return availableUnits;
    }

    public void setAvailableUnits(int availableUnits) {
        this.availableUnits = availableUnits;
    }

}
