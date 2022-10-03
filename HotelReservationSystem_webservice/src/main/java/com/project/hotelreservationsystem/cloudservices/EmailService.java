package com.project.hotelreservationsystem.cloudservices;
import com.project.hotelreservationsystem.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

public class EmailService {

        @Autowired
        private JavaMailSender javaMailSender;

        public void sendMail(String toEmailAddress, Booking bookingDetails){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("poojanadagouda95@gmail.com");
            message.setTo(toEmailAddress);
            message.setText("Thank you for choosing us"
            +"\n Your Booking ID is: " + bookingDetails.getBookingID()
                    +"\n Room type booked: " + bookingDetails.getRoomType()
            +"\n Rooms booked: " + bookingDetails.getRooms_booked()
            +"\n Adults: " + bookingDetails.getAdults_count()
            + "\n Booking dates: " + bookingDetails.getCheck_in() + " to " + bookingDetails.getCheck_out()
            + "\n Enjoy your stay."
            +"\n Thank you");
            message.setSubject("Hotel Booking confirmation");
            javaMailSender.send(message);
        }

}
