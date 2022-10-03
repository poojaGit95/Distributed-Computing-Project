package com.project.hotelreservationsystem.cloudservices;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;

public class SimpleEmailService {

    private AmazonSimpleEmailService amazonSimpleEmailServiceClient;

    final String sourceEmailAddress = "poojanadagouda95@gmail.com";

    final String emailSubject = "Hotel Booking Confirmation";

    final String emailBodyHtml = "<h5>Your booking is confirmed</h5>"
            + "<p>Thank you for the booking. Following are your booking details:"
            + "Thank you! And we are waiting to host you";


    final String emailBodyText = "Your booking is confirmed"
            + "Thank you for the booking.\n"
            + "We are waiting to host you \n"
            + "Following are your booking details: \n";


    public SimpleEmailService() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("xxx", "xxxx");
        amazonSimpleEmailServiceClient =
                AmazonSimpleEmailServiceClientBuilder.standard()
                        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                        .withRegion(Regions.US_WEST_2)
                        .build();
    }

    public void sendEmail(String destinationEmailAddress) {
        try {
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(new Destination().withToAddresses(destinationEmailAddress))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(emailBodyHtml))
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(emailBodyText)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(emailSubject)))
                    .withSource(sourceEmailAddress);
            amazonSimpleEmailServiceClient.sendEmail(request);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
