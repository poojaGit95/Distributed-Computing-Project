package com.project.hotelreservationsystem.application;

import com.google.gson.Gson;
import com.project.hotelreservationsystem.cloudservices.DynamoDBService;
import com.project.hotelreservationsystem.cloudservices.DynamoDBTransactionService;
import com.project.hotelreservationsystem.cloudservices.EmailService;
import com.project.hotelreservationsystem.cloudservices.SimpleEmailService;
import com.project.hotelreservationsystem.servicehelper.RoomAvailabilityServiceHelper;
import com.project.hotelreservationsystem.servicehelper.RoomBookingServiceHelper;
import com.project.hotelreservationsystem.servicehelper.RoomServiceHelper;
import com.project.hotelreservationsystem.services.RoomAvailabilityService;
import com.project.hotelreservationsystem.services.RoomBookingService;
import com.project.hotelreservationsystem.services.RoomService;
import com.project.hotelreservationsystem.services.TransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class Config {

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("poojanadagouda95@gmail.com");
        javaMailSender.setPassword("vmbchamkzztqzsxp");
        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        return javaMailSender;
    }

    @Bean
    public EmailService emailService(){ return new EmailService(); }

    @Bean
    public ApplicationServiceInitializer applicationInitializer(){
        return new ApplicationServiceInitializer();
    }

    @Bean
    public DynamoDBService dynamoDBService(){
        return new DynamoDBService();
    }

    @Bean
    public DynamoDBTransactionService dynamoDBTransactionService(){ return new DynamoDBTransactionService(); }

    @Bean
    public SimpleEmailService simpleEmailService(){ return new SimpleEmailService(); }

    @Bean
    public RoomAvailabilityService roomAvailabilityService(){
        return new RoomAvailabilityService();
    }

    @Bean
    public RoomService roomService(){
        return new RoomService();
    }

    @Bean
    public RoomBookingService roomBookingService(){
        return new RoomBookingService();
    }

    @Bean
    public TransactionService transactionService(){ return new TransactionService(); }

    @Bean
    public RoomAvailabilityServiceHelper roomAvailabilityServiceHelper(){
        return new RoomAvailabilityServiceHelper();
    }

    @Bean
    public RoomServiceHelper roomServiceJelper(){
        return new RoomServiceHelper();
    }

    @Bean
    public RoomBookingServiceHelper roomBookingServiceHelper(){
        return new RoomBookingServiceHelper();
    }

    @Bean
    public Gson gson(){ return new Gson(); }
}
