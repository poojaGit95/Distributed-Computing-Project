# Distributed-Computing-Project



Project: Hotel Reservation System


This system is a web-based software application which allows customers to book rooms for specified dates. The system provides the following services:
1.	User can enter check in and check out dates, to check availability of various rooms in the hotel.
2.	User can view the different types of rooms offered by the hotel.
3.	User can check details of a booking by using booking ID.
4.	User can view the contact information of the hotel.

Service details:
1.	Booking service: This service takes user input check-in / check-out dates and displays the available rooms. From these available rooms, user can select room type of his choice to proceed with booking. At the booking page user should enter his details and number of members to reserve the room. On successful booking an email is sent to the user.
2.	Listing rooms service: This service displays all the room types (e.g., single, double, suite etc.) offered by the hotel with its details like facilities offered, price etc. 
3.	Booking details service: This service intakes user’s booking ID and displays the corresponding booking details.
4.	Contact service: This service is static content displaying the hotel contact details with google map location.

Services not handled by this system:
1.	It does not provide hotel admin related services.
e.g.: adding a new room or removing a room etc.
2.	It does not handle any online payment services. 
e.g.: payments for room booking

AWS services used: 
1.	AWS Dynamo DB for database
2.	AWS SNS service for sending email notifications to users
3.	AWS Cloud watch to monitor the metrics
4.	AWS Elastic beanstalk to host the web application


Technologies, languages, and frameworks used:
1.	Java for backend
2.	HTML, CSS, JavaScript for user interface
3.	Spring boot framework

This system is hosted on AWS Elastic Beanstalk to make it available to all users.






