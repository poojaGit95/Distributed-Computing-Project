<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <style>
            body  {
                margin: 0;
                font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
                background-image: url("hotel.jpeg");
                background-size: cover;
            }
            a{
                text-decoration: none;
                margin-left: 35px;
                text-align: right;
                color:white;
            }
            a:hover{
                font-weight: bold;
            }
            .a-panel{
                position: absolute;
                text-align: right;
                top:5%;
                left: 60%;
            }

            .home-button:hover {
               font-weight: bold;
            }
             .room-button:hover {
               font-weight: bold;
            }
             .booking-button:hover {
               font-weight: bold;
            }
            .contact-button:hover {
                font-weight: bold;
            }

            .home-button {
                position: absolute;
                left: 60%;
                top: 5%;
                background-color: transparent;
                border: none;
                color: white;
                text-align: center;
                font-size: 21px;
                cursor: pointer;
                margin: 2%;
            }

            .room-button {
                position: absolute;
                left: 68%;
                top: 5%;
                background-color: transparent;
                border: none;
                color: white;
                text-align: center;
                font-size: 21px;
                cursor: pointer;
                margin: 2%;
            }

            .booking-button {
                position: absolute;
                left: 76%;
                top: 5%;
                background-color: transparent;
                border: none;
                color: white;
                text-align: center;
                font-size: 21px;
                cursor: pointer;
                margin: 2%;
            }
            .contact-button {
                position: absolute;
                left: 86%;
                top: 5%;
                background-color: transparent;
                border: none;
                color: white;
                text-align: center;
                font-size: 21px;
                cursor: pointer;
                margin: 2%;
            }

            .contact-panel{
                position:absolute;
                text-align: left;
                top:10%;
                font-weight: bold;
                left: 30%;
                width: 30%;
                height: 70%;
                margin: 50px;
                color: white;
                padding: 10px;
            }

        </style>
        <title> Contact Page</title>
    </head>
    <body>
        <form action="home" method="GET">
            <input type="submit" value="Home" class="home-button">
        </form>
        <form action="rooms" method="GET">
            <input type="submit" value="Rooms" class="room-button">
        </form>
        <form action="bookings" method="GET">
            <input type="submit" value="Bookings" class="booking-button">
        </form>
        <form action="contact" method="GET">
            <input type="submit" value="Contact" class="contact-button">
        </form>
        <div class="contact-panel">
            <h1>CONTACT US</h1>
            <p> If you have any questions, do not hesitate to ask them.</p>
            <p> Email: askyourquestion@hotel.info</p>
            <p> Contact: +1 425 777 1999 </p>
            <p> Seattle WA </p>
            <p>Locate us on google maps: </p>
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2689.6844152269227!2d-122.19525028464572!3d47.61282579554154!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x54906c629258d9fb%3A0xb6b25640371d5bcc!2sSeattle%20Marriott%20Bellevue!5e0!3m2!1sen!2sus!4v1649016234927!5m2!1sen!2sus" width="300" height="200" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
    </body>
</html>