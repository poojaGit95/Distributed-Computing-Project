<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <style>
            .button {
                  position: relative;
                  background-size: cover;
                  background-color: transparent;
                  border: none;
                  color: white;
                  text-align: center;
                  font-size: 16px;
                  cursor: pointer;
                  float: left;
                  margin: 2%;
            }
            .btn-group {
                left: 60%;
                top: 5%;
                position: relative;
            }
            .button:hover {
               font-weight: bold;
            }
        </style>
    </head>

    <body>
        <div class="btn-group">
            <form action="home" method="GET">
                <input type="submit" value="Home" class="button">
            </form>
            <form action="rooms" method="GET">
                <input type="submit" value="Rooms" class="button">
            </form>
            <form action="bookings" method="GET">
                <input type="submit" value="Bookings" class="button">
            </form>
            <form action="contact" method="GET">
                <input type="submit" value="Contact" class="button">
            </form>
        </div>
    </body>
</html>