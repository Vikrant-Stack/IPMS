

<style>
    
    .dropdown{
   margin-left: 20px;
        
    }
    
    </style>

<body>
       
        <nav class="navbar navbar-expand-sm navbar-expand" style="background-color: #99C1E8;font-size: 20px;color: #000;font-weight: bold;padding:10px"  >
  <div class="container-fluid">
   
    <div class="collapse navbar-collapse" id="myNavbar">
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
                <li  class="dropdown"><a id="mpeb2" href="#" class="dropdown-toggle" data-toggle="dropdown">Location<span class="caret"></span></a>
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
                
                <li  class="dropdown"><a id="mpeb2" href="#" class="dropdown-toggle" data-toggle="dropdown">E-Pass<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <!-- <li><a href="zonalCont">Zonal Council</a></li>
                         <li><a href="stateutTypeCont">State & UT</a></li>-->
                        <li><a href="workTypeCont">Work Type</a></li>
                        <li><a href="ePassCont">e-pass</a></li>
<!--                        <li><a href="countryCont">Country</a></li>
                        <li><a href="stateutTypeCont">State</a></li>
                        <li><a href="districtTypeCont">District</a></li>
                        <li><a href="divisionTypeCont">Division</a></li>
                        <li><a href="zoneTypeCont">Zone</a></li>
                        <li><a href="wardTypeCont">Ward</a></li>                                                        
                        <li><a href="areaTypeCont">Area </a></li>
                        <li><a href="cityLocationCont">Location</a></li>-->

                    </ul>
                </li>
                
                <!--    <li><a id="mpeb1" href="#">Ride</a>
                       <ul>
                           <li><a href="vehicleOrderCont.do">Vehicle Order</a></li>
                           <li><a href="rideCont.do">Get Ride</a></li>
                       </ul>
                   </li>-->
              
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
