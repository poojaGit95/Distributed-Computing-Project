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
            .confirmation-panel{
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
        <form action="book" method="POST">
        <div class="confirmation-panel">
            <h1>Your booking is confirmed!</h1>
            <p> ${successMsg} </p>
            <p> ${errorMsg} </p>
        </div>
        </form>
    </body>
</html>