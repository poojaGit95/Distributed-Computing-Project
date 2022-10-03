<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
    <head>
        <style>
            body{
                margin: 0;
                background-image: url(hotel.jpeg);
                background-size: cover;
                background-attachment: fixed;
                background-repeat: no-repeat;
                font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
            }
            .details-background{
                background-image: url("hotel.jpeg");
                background-position: center;
                background-repeat: no-repeat;
                background-size: cover;
                position: absolute;
                z-index: 1;
            }
            .details-panel{
                position:relative;
                background-color: white;
                margin: 20px;
                top: 10%;
                left:35%;
                width: 30%;
                z-index: 2;
                border-radius: 5px;
                padding: 20px;
            }
            input[type=text], [type=number] {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
                border-radius: 5px;
                padding: 20px;
            }
            .btn-submit {
                width: 100%;
                background-color:black;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                border-radius: 5px;
                padding: 20px;
            }
            .btn-submit:hover {
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
             .booking-button :hover {
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
            <div class="details-panel">
                <form id="booking-details" action="/book" method="POST">
                    <p><label> Check-In-Date: ${CheckInDate}</label></p>
                    <input type=hidden id="checkout" name="check_in" value=${CheckInDate}>

                    <p><label> Check-Out-Date: ${CheckOutDate}</label></p>
                    <input type=hidden id="checkin" name="check_out" value=${CheckOutDate}>

                    <p><label> Type of Room: ${RoomType} - ${Price}$</label></p>
                    <input type=hidden id="roomType" name="roomType" value=${RoomType}>

                    <p><label> Enter you details: </label></p>

                    <label for="fname">First Name</label>
                    <input type="text" id="fname" name="firstName" placeholder="First Name">

                    <label for="lname">Last Name</label>
                    <input type="text" id="lname" name="lastName" placeholder="Last Name">

                    <label for="address">Address</label>
                    <input type="text" id="address" name="address" placeholder="Address">

                    <label for="city">City</label>
                    <input type="text" id="city" name="city" placeholder="City">

                    <label for="state">State</label>
                    <input type="text" id="state" name="state" placeholder="State">

                    <label for="country">Country</label>
                    <input type="text" id="country" name="country" placeholder="Country">

                    <label for="pincode">pincode</label>
                    <input type="text" id="pincode" name="pincode" placeholder="Pincode">

                    <label for="phone">Phone</label>
                    <input type="text" id="phone" name="phone" placeholder="Your mobile number">

                    <label for="email">Email ID</label>
                    <input type="text" id="email" name="email" placeholder="Your email">

                    <label for="adults">Number of Adults</label>
                    <input type="number" id="adults" name="adults_count" placeholder="0" oninput="calculate()">

                    <label for="rooms">Number of Rooms</label>
                    <input type="number" id="rooms" name="rooms_booked" placeholder="0" oninput="cost_calculate()">

                    <script>
                        function cost_calculate(){
                            var y = document.getElementById("roomType").value;
                            if(y=="SINGLE"){
                                var r = document.getElementById("rooms").value;
                                document.getElementById("cost").value = ${Price}*r;
                            }else if(y=="DELUXE"){
                                var r = document.getElementById("rooms").value;
                                document.getElementById("cost").value = ${Price}*r;
                            }else if(y=="SUITE"){
                                var r = document.getElementById("rooms").value;
                                document.getElementById("cost").value = ${Price}*r;
                            }
                        }

                    </script>

                    <script>
                        function calculate(){
                            var x = document.getElementById("adults").value;
                            var y = document.getElementById("roomType").value;
                            var p = document.getElementById("price").value;
                            if(y=="SINGLE"){
                                document.getElementById("rooms").value = x;
                                var r = document.getElementById("rooms").value;
                                document.getElementById("cost").value = ${Price}*r;
                            }else if(y=="DELUXE"){
                                var z = Math.floor(x/2) + (x%2)
                                document.getElementById("rooms").value = z;
                                var r = document.getElementById("rooms").value;
                                document.getElementById("cost").value = ${Price}*r;
                            }else if(y=="SUITE"){
                                var z = x%4;
                                if(z>0){
                                    z= 1 + Math.floor(x/4)
                                }else{
                                    z = Math.floor(x/4)
                                }
                                document.getElementById("rooms").value = z ;
                                var r = document.getElementById("rooms").value;
                                document.getElementById("cost").value = ${Price}*r;
                            }

                        }
                    </script>

                    <p><label id="price" name="total_price">Total Price </label></p>
                    <input type="text" id="cost" name="total_price" readOnly>

                    <input type="submit" value="Reserve" class="btn-submit">
                    <script>
                        let input1 = document.getElementById('fname');
                        let input2 = document.getElementById('lname');
                        let input3 = document.getElementById('address');
                        let input4 = document.getElementById('city');
                        let input5 = document.getElementById('state');
                        let input6 = document.getElementById('country');
                        let input7 = document.getElementById('pincode');
                        let input8 = document.getElementById('phone');
                        let input9 = document.getElementById('email');
                        let input10 = document.getElementById('adults');
                        let input11 = document.getElementById('rooms').value;
                        let input12 = document.getElementById('price');
                        let input13 = document.getElementById('roomType').value;
                        document.getElementById("booking-details").addEventListener("submit", function(e){
                        	if ( !input1.value || !input2.value || !input3.value || !input4.value ||
                        	!input5.value || !input6.value || !input7.value || !input8.value ||
                        	!input9.value || !input10.value) {
                        		e.preventDefault();
                        		alert("None of the fields can be empty, Please fill all the fields to complete booking");
                        		return false;
                        	}else if(input13=="SINGLE" && document.getElementById('adults').value > document.getElementById('rooms').value){
                                e.preventDefault();
                                alert("Rooms cannot be less than adults, Please increase the number of rooms to book.");
                                return false;
                            }else if(input13=="DELUXE"){
                                var a = Math.floor(input10.value/2) + input10.value%2;
                                if (a>document.getElementById('rooms').value){
                                    e.preventDefault();
                                    alert("Rooms cannot be less than adults, Please increase the number of rooms to book.");
                                    return false;
                                }
                            }else if(input13=="SUITE"){
                                var a = Math.floor(input10.value/4);
                                var b = input10.value%4;
                                if(b>0){
                                    a = a+1;
                                }
                                if (a>document.getElementById('rooms').value){
                                    e.preventDefault();
                                    alert("Rooms cannot be less than adults, Please increase the number of rooms to book.");
                                    return false;
                                }
                            }
                        });

                    </script>
                </form>
            </div>

    </body>
</html>