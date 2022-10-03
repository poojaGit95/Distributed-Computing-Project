package com.project.hotelreservationsystem;

import com.project.hotelreservationsystem.application.ApplicationServiceInitializer;
import com.project.hotelreservationsystem.application.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class HotelReservationSystemApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HotelReservationSystemApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(HotelReservationSystemApplication.class, args);
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		ApplicationServiceInitializer applicationInitializer = context.getBean(ApplicationServiceInitializer.class);
		applicationInitializer.initializeHotelReservationApplication();
	}


}
