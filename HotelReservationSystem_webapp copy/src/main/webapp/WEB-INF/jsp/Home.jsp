<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <style>
            body {
                margin: 0;
                font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
            }
            .home-image {
                background-image: url("hotel.jpeg");
                height: 100%;
                background-position: center;
                background-repeat: no-repeat;
                background-size: cover;
                position: relative;
            }
            .home-text {
                text-align: center;
                position: absolute;
                font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
                top: 40%;
                left: 50%;
                font-weight: bold;
                transform: translate(-50%, -50%);
                color: white;
            }

            .form-checkin-section{
                text-align: center;
                position: absolute;
                font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
                top: 60%;
                font-weight: bold;
                left:10%;
                color:aliceblue;
            }
            .form-checkout-section{
                text-align: center;
                font-weight: bold;
                position:absolute;
                font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
                top: 60%;
                left: 40%;
                color:aliceblue;
            }
            .form-button-section{
                text-align: center;
                position: absolute;
                font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
                top: 60.5%;
                left: 70%;
            }
            .error-msg{
                text-align: center;
                position: absolute;
                font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
                top: 70%;
                left: 10%;
                color:aliceblue;
            }
            input[type=date] {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }
            .check-availability-class {
                width: 100%;
                background-color:black;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .check-availability-class:hover {
                background-color:gray;
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

            .icon {
                position: absolute;
                left: 2%;
                top: 5%;
                background-color: transparent;
                border: none;
                font-weight: bold;
                color: white;
                text-align: center;
                cursor: pointer;
                margin: 2%;
            }

            .home-button {
                position: absolute;
                left: 60%;
                top: 5%;
                background-color: transparent;
                border: none;
                font-size: 21px;
                color: white;
                text-align: center;
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
        <title>Home Page</title>
    </head>

    <body>
        <div class="home-image">
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
            <div class="home-text">
                <h3> HOTEL & RESORT</h4>
                <h1> EXPERIENCE THE LUXURY </h1>
            </div>

            <form id="home" action="select" method="POST">
                <div class="form-checkin-section">
                    <label> CHECK IN</label>
                    <input type="date" id="checkin" name="CheckInDate">
                </div>
                <div class="form-checkout-section">
                    <label> CHECK OUT</label>
                    <input type="date" id="checkout" name="CheckOutDate">
                </div>
                <div class="form-button-section">
                    <p> <input type="submit" id="btn" value="CHECK AVAILABILITY" class="check-availability-class"> </p>
                </div>
                <script>
                    document.getElementById("home").addEventListener("submit", function(e){
                          var x = document.getElementById("checkin").valueAsDate;
                          var z = document.getElementById("checkout").valueAsDate;
                          var y = new Date();
                          if(x<y || z<y){
                                e.preventDefault();
                                alert("Invalid dates selected, check-in or check-out dates cannot be in the past");
                          }else if(x>z){
                                e.preventDefault();
                                alert("Invalid dates selected, check-in date cannot be after check-out date");
                          }
                    });
                </script>
                <div class="error-msg">
                    <p> ${errorMsg} </p>
                <div>
            </form>
        </div>
    </body>
</html>