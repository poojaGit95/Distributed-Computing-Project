<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <style>
            body{
                margin: 0;
                background-image: url("hotel.jpeg");
                background-size: cover;
                font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
            }
            .bookings-panel{
                position:absolute;
                color: white;
                text-align: center;
                top:30%;
                left:30%;
                margin: 20px;
                height: 30%;
                width: 30%;
                /* float: left; */
                border-radius: 5px;
                padding: 20px;
            }
            input[type=text]{
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }
            .search-btn {
                width: 100%;
                background-color:black;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .search-btn:hover {
                background-color:gray;
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

        </style>
        <title> Bookings Page</title>
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
        <div class="bookings-panel">
            <h2> Check your booking here </h2>
            <form id="book-ID" action="bookings" method="POST">
                <input type="text" id="bookingID" name="bookingID" placeholder="Enter your Booking ID">
                <input type="submit" value="Search" class="search-btn">
                <h3>${errorMsg}</h3>
                <h3>${booking1}</h3>
                <h3>${booking2}</h3>
                <h3>${booking3}</h3>
                <script>
                    let input1 = document.getElementById('bookingID');
                    document.getElementById("book-ID").addEventListener("submit", function(e){
                        if (!input1.value) {
                            e.preventDefault();
                            alert("Booking ID cannot be empty");
                            return false;
                        }
                    });
                </script>
            </form>
        </div>
    </body>
</html>
