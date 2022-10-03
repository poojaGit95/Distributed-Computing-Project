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
            .btn-group {
                left: 60%;
                top: 5%;
                position: fixed;
            }
            .button:hover {
               font-weight: bold;
            }
            .room-single-panel{
                position: relative;
                background-color:white;
                top:18%;
                margin: 20px;
                height: 77%;
                width: 30%;
                float: left;
                text-align: center;
            }
            .room-delux-panel{
                position: relative;
                background-color:white;
                top:18%;
                margin: 20px;
                height: 77%;
                width: 30%;
                float: left;
                text-align: center;
            }
            .room-suite-panel{
                position: relative;
                background-color:white;
                top:18%;
                margin: 20px;
                height: 77%;
                width: 30%;
                float: left;
                text-align: center;
            }
            .book-btn {
                width: 80%;
                height: 7%;
                background-color:black;
                color: white;
                margin: 2px 0;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .book-btn:hover {
                background-color:gray;
            }
            .book-btn:disabled {
                background-color: gray;
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

        </style>
        <title>Rooms Selection page</title>
    </head>
    <body onload="btnFunction()">

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
            <form action="reserve" method="POST">
                <img src="single.png" height="47%" width="100%">
                <h2> ${SINGLEType} </h2>
                <p> Prices starting from ${SINGLEPrice}$ for ${SINGLECapacity} adult</p>
                <p> ${SINGLEFacility}  </p>
                <img src="s_facility.png" height="5%" width="15%">
                <p> <label id="count"> Rooms available: ${SINGLE}</label></p>
                <input type=hidden id="single_units" value=${SINGLE}>
                <input type=hidden id="checkInDate" name="CheckInDate" value=${CheckInDate}>
                <input type=hidden id="checkOutDate" name="CheckOutDate" value=${CheckOutDate}>
                <input type=hidden id="roomType" name="RoomType" value=${SINGLEType}>
                <input type=hidden id="price" name="Price" value=${SINGLEPrice}>
                <p> <input type="submit" id="single-book-btn" value="BOOK NOW" class="book-btn"> </p>
            </form>
        </div>
        <div class="room-delux-panel">
            <form action="reserve" method="POST">
                <img src="delux.png" height="47%" width="100%">
                <h2> ${DELUXEType} </h2>
                <p> Prices starting from ${DELUXEPrice}$ for ${DELUXECapacity} adults </p>
                <p> ${DELUXEFacility} </p>
                <img src="d_facility.png" height="5%" width="20%">
                <p> <label> Rooms available: ${DELUXE}</label></p>
                <input type=hidden id="deluxe_units" value=${DELUXE}>
                <input type=hidden id="checkInDate" name="CheckInDate" value=${CheckInDate}>
                <input type=hidden id="checkOutDate" name="CheckOutDate" value=${CheckOutDate}>
                <input type=hidden id="roomType" name="RoomType" value=${DELUXEType}>
                <input type=hidden id="price" name="Price" value=${DELUXEPrice}>
                <p> <input type="submit" id="deluxe-book-btn" value="BOOK NOW" class="book-btn"> </p>
            </form>
        </div>
        <div class="room-suite-panel">
            <form action="reserve" method="POST">
                <img src="suite.jpeg" height="47%" width="100%">
                <h2> ${SUITEType} </h2>
                <p> Prices starting from ${SUITEPrice}$ for ${SUITECapacity} adults </p>
                <p> ${SUITEFacility} </p>
                <img src="st_facility.png" height="5%" width="25%">
                <p> <label> Rooms available: ${SUITE}</label></p>
                <input type=hidden id="suite_units" value=${SUITE}>
                <input type=hidden id="checkInDate" name="CheckInDate" value=${CheckInDate}>
                <input type=hidden id="checkOutDate" name="CheckOutDate" value=${CheckOutDate}>
                <input type=hidden id="roomType" name="RoomType" value=${SUITEType}>
                <input type=hidden id="price" name="Price" value=${SUITEPrice}>
               <input type="submit" id="suite-book-btn" value="BOOK NOW" class="book-btn">
            </form>
            <script>
               function btnFunction() {
                   if (document.getElementById("deluxe_units").value==0) {
                       document.getElementById("deluxe-book-btn").disabled = true;
                   }
                   if (document.getElementById("single_units").value==0) {
                      document.getElementById("single-book-btn").disabled = true;
                   }
                   if (document.getElementById("suite_units").value==0) {
                      document.getElementById("suite-book-btn").disabled = true;
                   }
               }
            </script>
        </div>
    </body>
</html>