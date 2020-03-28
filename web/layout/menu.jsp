

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>

<html>
    <head>
        <title>Menu</title>
<!--        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>-->




        <script type="text/javascript">
            $(function () {
                if ($.browser.msie && $.browser.version.substr(0, 1) < 7)
                {
                    $('li').has('ul').mouseover(function () {
                        $(this).children('ul').show();
                    }).mouseout(function () {
                        $(this).children('ul').hide();
                    })
                }
            });

            $(document).ready(function () {
                var mouseX;
                var mouseY;
                $(document).mousemove(function (e) {
                    mouseX = e.pageX;
                    mouseY = e.pageY;
                });

                $("#close_div").click(function () {
                    //this.title = "fffff</div>";
                    $("#popup").hide();
                    $('#ride_no_from').val("");
                    $('#ride_no_to').val("");

                });

                $("#logout").click(function () {
                    $('#popup').css({
                        'position': 'absolute',
                        'left': mouseX,
                        'top': mouseY
                    });
                    $("#make_work_order_btn").val("quickWorkOrder");
                    $("#popup").show();

                });

            });





//        function logout(){debugger;
//         var ride_no_from = $("#ride_no_from").val();
//         var ride_no_to = $("#ride_no_to").val();
//
//         $.ajax({url: "shiftLoginCont.do?task=ride_detail",
//             data: "ride_no_from="+ ride_no_from +"&ride_no_to="+ ride_no_to +"",
//             success: function(response_data) {
//                 var affacted = response_data.trim().split("#&");
//
//                 if(parseInt(affacted[0]) > 0){
//                     doc.getElementById("message").innerHTML = "<td colspan='5' bgcolor='yellow'><b>Ride Detail is Saved...</b></td>";
//                     $("#ride_no_from").val(ride_no_from);
//                     $("#ride_no_to").val(affacted[1]);
//                     $("#driver").val(quick_driver_name);
//                 }else
//                     doc.getElementById("message").innerHTML = "<td colspan='5' bgcolor='coral'><b>Ride Detail is not saved some error!</b></td>";
//                 $("#order_detail_div").hide();
//                //$("#message").html("<td colspan='5' bgcolor='coral'><b>Location Name is required...</b></td>");
//             }
//         });
//        }
        </script>

        <!--        <link type="text/css" href="style/menu.css" rel="stylesheet"/>-->

        <style>

            .navbar-inverse a{
                /*        background-color: #3292a8;*/
                color: white;
            }
        </style>

    </head>
    <body>
    <nav class="navbar navbar-light" style="background-color: #e3f2fd;">
        <div class="container-fluid">
            <ul id="menu" class="nav navbar-nav " >

                <!--    <li><a id="mpeb" href="#">Data Entry</a>
                       <ul>
                           <li><a id="" href="destinationCont.do">Destination</a></li>
                           <li><a id="" href="sourceDestinationCont.do">Source Destination</a></li>
                            <li><a id="" href="keypersonCont.do">Key Person</a></li>
                            <li><a id="" href="vehicleCont.do">Vehicle Detail</a></li>
                        </ul>
                      </li> -->
                <li class="dropdown "><a id="" href="#" class="dropdown-toggle" data-toggle="dropdown">Organization<span class="caret"></span></a>
                    <ul class="dropdown-menu ">
<!--                        <li><a href="orgDetailEntryCont.do">Org. All in One</a></li>-->
                        <li><a href="orgNameCont.do">Org. Name</a></li>
                        <li><a href="orgTypeCont.do">Org. Type</a></li>
                        <li><a href="organisationSubTypeCont.do">Org. Sub Type</a></li>
<!--                        <li><a href="mapOrgCont.do">Org. Map Relation</a></li>-->
                        <li><a href="orgOfficeTypeCont.do">Org. Office Type</a></li>
                        <li><a href="organisationCont.do">Org. Office</a></li>
                        <li><a href="designationCont.do">Designation</a></li>
                        <li><a href="personCount.do">Org Person's Name</a></li>
<!--                        <li><a href="TypeOfOccupationController">Type Of Occupation</a></li>
                        <li><a href="BeneficiaryController">Beneficiary</a></li>
                        <li><a href="beneficiaryShiftSearchCont.do">Beneficiary Shift Search</a></li>
                        <li><a href="RwaController">RWA</a></li>
                        <li><a href="RwaBeneficiaryController">RWA Beneficiary</a></li>
                        <li><a href="UploadExcelCont.do">Upload Excel</a></li>-->
                    </ul>
                </li>
                <li class="dropdown"><a id="mpeb2" href="#" class="dropdown-toggle" data-toggle="dropdown">Location<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <!-- <li><a href="zonalCont">Zonal Council</a></li>
                         <li><a href="stateutTypeCont">State & UT</a></li>-->
                        <li><a href="cityTypeCont">City</a></li>
                        <li><a href="tehsilTypeCont">Tehsil</a></li>
                        <li><a href="countryCont">Country</a></li>
                        <li><a href="stateutTypeCont">State</a></li>
                        <li><a href="districtTypeCont">District</a></li>
                        <li><a href="divisionTypeCont">Division</a></li>
                        <li><a href="zoneTypeCont">Zone</a></li>
                        <li><a href="wardTypeCont">Ward</a></li>                                                        
                        <li><a href="areaTypeCont">Area </a></li>
                        <li><a href="cityLocationCont">Location</a></li>


                    </ul>
                </li>
                <!--    <li><a id="mpeb1" href="#">Ride</a>
                       <ul>
                           <li><a href="vehicleOrderCont.do">Vehicle Order</a></li>
                           <li><a href="rideCont.do">Get Ride</a></li>
                       </ul>
                   </li>-->
                <li class="dropdown"><a id="mpeb1" href="#" class="dropdown-toggle" data-toggle="dropdown">Shift<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="ShiftController">Shift Type</a></li>
                        <!--  <li><a href="LocationTypeController">Location Type</a></li>
                           <li><a href="DesignationLocationTypeController">Designation Location Type</a></li>-->
                        <li><a href="ShiftDesinationLocationController">Shift Designation Map</a></li>
                        <li><a href="ShiftKeyPersonMapController">Shift Key Person Map</a></li>
                        <li><a href="ShiftTimeController">Shift Time</a></li>
                        <li><a href="ShiftShowController">Shift Beneficiary detail</a></li>
                        <li><a href="AttendenceController">Attendance</a></li>
                        <li><a href="AttendenceViewController">Shift Attendance detail</a></li>
                        <li><a href="shiftReasonCont.do">Shift Reason</a></li>

                    </ul>
                </li>
                <li class="dropdown"><a id="mpeb2" href="#" class="dropdown-toggle" data-toggle="dropdown">Vehicle<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="VehicleTypeController">Vehicle Type</a></li>
                        <li><a href="VehicleSubTypeController">Vehicle Sub Type</a></li>
                        <li><a href="VehicleController">Vehicle</a></li>
                        <li><a href="VehicleLocationController">Vehicle Location</a></li>
                        <li><a href="VehicleKeyPersonController">Vehicle Key Person</a></li>
                        <li><a href="PointController">Point</a></li>
                        <li><a href="VehicleKeyPersonPoint">VehicleKeyPerson Point</a></li>
                        <li><a href="ShiftVehicleDetail">Shift Vehicle Detail</a></li>
                        <li><a href="VehicleWeightCont.do">Vehicle Weight</a></li>
                    </ul>
                </li>
                <!--                       <li class="dropdown"><a id="mpeb3" href="#" class="dropdown-toggle" data-toggle="dropdown">Dustbin<span class="caret"></span></a>
                                      <ul class="dropdown-menu">
                                                 <li><a href="DustbinTypeController">Dustbin Type</a></li>
                                                <li><a href="DustbinController">Dustbin</a></li>
                                                 <li><a href="LevelController">Level</a></li>
                                      </ul>
                                </li>-->
                <!--                 <li class="dropdown"><a id="mpeb4" href="#" class="dropdown-toggle" data-toggle="dropdown">Complain<span class="caret"></span></a>
                                      <ul class="dropdown-menu">
                                                 <li><a href="ErrorLogController">Error Log</a></li>
                                                 <li><a href="TypeOfErrorController">Type of error</a></li>
                                      </ul>
                                </li>-->
                <!--                <li class="dropdown"><a id="mpeb1" href="#"  class="dropdown-toggle" data-toggle="dropdown">MIS<span class="caret"></span></a>
                                      <ul class="dropdown-menu">
                                            <li><a href="mis">Show Records</a></li>
                                             <li><a href="WeightMIS">Weight</a></li>
                
                                      </ul>
                                
                
                                </li>-->
                <!--                <li class="dropdown"><a href="#"  class="dropdown-toggle" data-toggle="dropdown">Add Item<span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="ItemNameController">Item name</a></li>
                                        <li><a href="PropertiesController">Properties</a></li>
                                        <li><a href="ItemNamePropertiesMapController">ItemName_Properties_map</a></li>
                                        <li><a href="PropertiesDetailsController">Properties_Details</a></li>
                                        <li><a href="ItemController">Item</a></li>
                                        <li><a href="PurposeHeaderController2">Purpose Header1</a></li>
                                        <li><a href="NodeDetailController">Node Detail</a></li>
                                    </ul>
                                </li>-->
                <li class="dropdown"><a href="#"  class="dropdown-toggle" data-toggle="dropdown">Vehicle Trip<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="RouteNameCont.do">Route Name</a></li>
                        <li><a href="RouteCont.do">Route Point Map</a></li>
                        <li><a href="TripCont.do">Trip</a></li>
                        <li><a href="TripPointCont.do">Trip Point Map</a></li>
                        <li><a href="TripVehicleCont.do">Trip Vehicle Map</a></li>
                        <li><a href="TripLogFileCont.do">Trip Log File</a></li>
                        <li><a href="TripPointLogFileCont.do">Trip Point Log File</a></li>


                    </ul>
                </li>
<!--                <li><a id="mpeb1" href="LoginController?task=logout" onclick=""  >LogOut</li>-->
            </ul>
            
            
        </div>
    </nav>
    <form name="log" action="shiftLoginCont.do" method="post">
        
        <input  type="hidden" name="task" id="logout" value="Logout" >
    </form>

    <!--
 <div id="popup" align="center" style="display: none; background-color: white; border: black; border-width: 2px;border-style: solid">
     <form action="shiftLoginCont.do" method="post">
            <a id="close_div">Close</a><br>
            <Span><b><u>Provide Ride Details</u></b></Span><br>
            <Span id="show_message" style="display: none; background: red"></Span><br>
            <b>Ride No From</b><br>
            <input type="text" id="ride_no_from" name="ride_no_from" value=""><br>
            <b>Ride No To</b><br>
            <input class="new_input" type="text" id="ride_no_to" name="ride_no_to" value=""><br>
            <input class="button" type="submit" name="task" id="save" value="save" onclick="setStatus(id)">
            <input class="" type="hidden" id="make_work_order_btn" name="" value="">
     </form>
</div>
    -->


</body>
</html>