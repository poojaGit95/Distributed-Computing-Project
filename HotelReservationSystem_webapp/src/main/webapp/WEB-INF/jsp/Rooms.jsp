<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <style>
            body{
                margin: 0;
                background-image: url(hotel.jpeg);
                background-size: cover;
                font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
            }
            .button {
                position: relative;
                background-color: transparent;
                border: none;
                color: white;
                text-align: center;
                font-size: 16px;
                cursor: pointer;
                margin: 2%;
            }
            .btn-group {
                left: 60%;
                top: 5%;
                position: absolute;
            }
            .button:hover {
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

            .room-single-panel{
                position: relative;
                background-color:white;
                top:20%;
                margin: 20px;
                height: 70%;
                width: 30%;
                float: left;
                text-align: center;
            }
            .room-delux-panel{
                position: relative;
                background-color:white;
                top:20%;
                margin: 20px;
                height: 70%;
                width: 30%;
                float: left;
                text-align: center;
            }
            .room-suite-panel{
                position: relative;
                background-color:white;
                top:20%;
                margin: 20px;
                height: 70%;
                width: 30%;
                float: left;
                text-align: center;
            }
        </style>
        <title>Rooms info page</title>
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

        <div class="room-single-panel">
            <form action="room" method="GET">
                <img src="single.png" height="55%" width="100%">
                <h2> ${SINGLEType} </h2>
                <p> Prices starting from ${SINGLEPrice}$ for ${SINGLECapacity} adult</p>
                <p> ${SINGLEFacility}  </p>
                <img src="s_facility.png" height="5%" width="15%">
            </form>
        </div>

        <div class="room-delux-panel">
            <form action="reserve" method="POST">
                <img src="delux.png" height="55%" width="100%">
                <h2> ${DELUXEType} </h2>
                <p> Prices starting from ${DELUXEPrice}$ for ${DELUXECapacity} adults </p>
                <p> ${DELUXEFacility} </p>
                <img src="d_facility.png" height="5%" width="20%">
            </form>
        </div>

        <div class="room-suite-panel">
            <form action="reserve" method="POST">
                <img src="suite.jpeg" height="55%" width="100%">
                <h2> ${SUITEType} </h2>
                <p> Prices starting from ${SUITEPrice}$ for ${SUITECapacity} adults </p>
                <p> ${SUITEFacility} </p>
                <img src="st_facility.png" height="5%" width="25%">
            </form>
        </div>
    </body>
</html>