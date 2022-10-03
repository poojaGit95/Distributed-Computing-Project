package com.webapp.hotelreservation;

import com.google.gson.Gson;
import com.webapp.hotelreservation.client.HotelReservationClient;
import com.webapp.hotelreservation.restclient.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public RestClient restClient(){
        return new RestClient();
    }

    @Bean
    public HotelReservationClient hotelReservationClient(){
        return new HotelReservationClient();
    }

    @Bean
    public Gson gson(){
        return new Gson();
    }

}
