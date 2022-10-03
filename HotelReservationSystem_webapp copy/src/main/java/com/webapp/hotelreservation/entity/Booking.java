package com.webapp.hotelreservation.entity;

public class Booking {

    private String bookingID;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String phone;
    private String email;
    private int rooms_booked;
    private String roomType;
    private int adults_count;
    private String check_in;
    private String check_out;
    private double total_price;

    public Booking(String bookingID, String firstName, String lastName, String address, String city,
                   String state, String country, String pincode, String phone,
                   String email, int rooms_booked, String roomType, int adults_count,
                   String check_in, String check_out, double total_price) {
        this.bookingID = bookingID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.phone = phone;
        this.email = email;
        this.rooms_booked = rooms_booked;
        this.roomType = roomType;
        this.adults_count = adults_count;
        this.check_in = check_in;
        this.check_out = check_out;
        this.total_price = total_price;
    }

    public Booking() {

    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRooms_booked() {
        return rooms_booked;
    }

    public void setRooms_booked(int rooms_booked) {
        this.rooms_booked = rooms_booked;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getAdults_count() {
        return adults_count;
    }

    public void setAdults_count(int adults_count) {
        this.adults_count = adults_count;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID='" + bookingID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pincode='" + pincode + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", rooms_booked=" + rooms_booked +
                ", roomType='" + roomType + '\'' +
                ", adults_count=" + adults_count +
                ", check_in='" + check_in + '\'' +
                ", check_out='" + check_out + '\'' +
                ", total_price=" + total_price +
                '}';
    }

    public String getBookingDetailsView(){
        return "Booking Details: \n" +
                firstName + " " + lastName +
                "\n Booking Dates: " + check_in + " " + check_out +
                "\n Booked Room: " + rooms_booked + " " + roomType ;
    }

}
