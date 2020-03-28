
function checkkk() {//alert(111); debugger;
   // debugger;
    var datetime;
    var ohlevel;
    var dateTime1 = [];
    OhLevel1 = [];
    dateTime2 = [];

    var dt = new Date();
    $.ajax({
        dataType: "json",
        async: false,
        url: "CanvasJSController?task=getAllDateTime",
        success: function (response_data) {//alert("response data -"+response_data.dateTime);
            datetime = response_data.dateTime;
            //alert("date time length-"+datetime.length);
            for (var i = 0; i < datetime.length; i++) {//alert("date time array -"+datetime[i]["date_time"]);
                var d = datetime[i]["date_time"];
                var a = d.split(",");
                dateTime1[i] = new Date(a[0], a[1], a[2], a[3], a[4]);
                //alert("date time -"+dateTime1[i]);
                //time.setTime(dateTime1[i].getTime());
                //alert("timeeee -"+dateTime1[i].getTime());
                var hour = dateTime1[i].getHours() - (dateTime1[i].getHours() >= 12 ? 12 : 0);
                if (hour == 0) {
                    hour = 12;
                }
                var period = dateTime1[i].getHours() >= 12 ? 'PM' : 'AM';
                //alert(hour + ':' + dateTime1[i].getMinutes() + ' ' + period);
                dateTime2[i] = hour + ':' + dateTime1[i].getMinutes() + ' ' + period;
                //alert("date time 222 -"+dateTime2[i]);

            }
        }
    });

    $.ajax({
        dataType: "json",
        async: false,
        url: "CanvasJSController?task=getAllOhLevel",
        //data:{data: vkp_id},
        success: function (response_data1) {
            ohlevel = response_data1.ohLevel;
            //alert("oh level length -"+ohlevel);
            for (var i = 0; i < ohlevel.length; i++) {
                OhLevel1[i] = ohlevel[i]["remark"];
                //alert("oh level remark -"+ohlevel[i]["remark"]);                           
            }
        }
    });

   
    var fuel_graph = new Morris.Bar({
        element: 'hero-graph',
        data: updateData(),
        xkey: 'time',
        ykeys: ['vol'],
        //labels: ['Licensed', 'Off the road'],
        labels: ['Level'],
        lineColors: ['#4ECDC4'],
        redraw: true,
    });

//    fuel_graph.setData(updateData());
//    alert("aa gaya ");

}

//function updateData(){
// var fuel_data = [];
//    for (var i = 0; i < OhLevel1.length; i++) {
//        fuel_data.push({"time": dateTime2[i], "vol": OhLevel1[i]});
//    }
//    return fuel_data;
//    alert("fuel data -"+fuel_data.length);
//}



// other testing

var goodComments = 0,
        badComments = 3,
        neutralComments = 1,
        G;

function updateData() {
    //debugger;
    var datetime;
    var ohlevel;
    var dateTime1 = [];
    var fueldata={
        dateTime : String,
        fuellvl:String,
        accuracy: String
    };
    OhLevel1 = [];
    dateTime2 = [];
 
    var dt = new Date();
    $.ajax({
        dataType: "json",
        async: false,
        url: "CanvasJSController?task=getAllDateTime",
        success: function (response_data) {//alert("response data -"+response_data.dateTime);
            datetime = response_data.dateTime;
            //alert("date time length-"+datetime.length);
            for (var i = 0; i < datetime.length; i++) {//alert("date time array -"+datetime[i]["date_time"]);
                var d = datetime[i]["date_time"];
                var a = d.split(",");
                dateTime1[i] = new Date(a[0], a[1], a[2], a[3], a[4]);
                //alert("date time -"+dateTime1[i]);
                //time.setTime(dateTime1[i].getTime());
                //alert("timeeee -"+dateTime1[i].getTime());
                var hour = dateTime1[i].getHours() - (dateTime1[i].getHours() >= 12 ? 12 : 0);
                if (hour == 0) {
                    hour = 12;
                }
                var period = dateTime1[i].getHours() >= 12 ? 'PM' : 'AM';
                //alert(hour + ':' + dateTime1[i].getMinutes() + ' ' + period);
                dateTime2[i] = hour + ':' + dateTime1[i].getMinutes() + ' ' + period;
                //alert("date time 222 -"+dateTime2[i]);

            }
        }
    });

    $.ajax({
        dataType: "json",
        async: false,
        url: "CanvasJSController?task=getAllOhLevel",
        //data:{data: vkp_id},
        success: function (response_data1) {
            ohlevel = response_data1.ohLevel;
            //alert("oh level length -"+ohlevel);
            for (var i = 0; i < ohlevel.length; i++) {
                OhLevel1[i] = ohlevel[i]["remark"];
                //alert("oh level remark -"+ohlevel[i]["remark"]);                           
            }
        }
    });


    var fuel_data = [];
    for (var i = 0; i < OhLevel1.length; i++) {
        fuel_data.push({"time": dateTime2[i], "vol": OhLevel1[i]});
    }
    return fuel_data;

}

function volumeData() {
    //debugger;
    var datetime;
    var ohlevel;
    var dateTime1 = [];
    OhLevel1 = [];
    dateTime2 = [];

    var dt = new Date();
    $.ajax({
        dataType: "json",
        async: false,
        url: "CanvasJSController?task=getAllDateTime",
        success: function (response_data) {//alert("response data -"+response_data.dateTime);
            datetime = response_data.dateTime;
            //alert("date time length-"+datetime.length);
            for (var i = 0; i < datetime.length; i++) {//alert("date time array -"+datetime[i]["date_time"]);
                var d = datetime[i]["date_time"];
                var a = d.split(",");
                dateTime1[i] = new Date(a[0], a[1], a[2], a[3], a[4]);
                //alert("date time -"+dateTime1[i]);
                //time.setTime(dateTime1[i].getTime());
                //alert("timeeee -"+dateTime1[i].getTime());
                var hour = dateTime1[i].getHours() - (dateTime1[i].getHours() >= 12 ? 12 : 0);
                if (hour == 0) {
                    hour = 12;
                }
                var period = dateTime1[i].getHours() >= 12 ? 'PM' : 'AM';
                //alert(hour + ':' + dateTime1[i].getMinutes() + ' ' + period);
                dateTime2[i] = hour + ':' + dateTime1[i].getMinutes() + ' ' + period;
                //alert("date time 222 -"+dateTime2[i]);

            }
        }
    });

    $.ajax({
        dataType: "json",
        async: false,
        url: "CanvasJSController?task=getAllOhLevel",
        //data:{data: vkp_id},
        success: function (response_data1) {
            ohlevel = response_data1.ohLevel;
            //alert("oh level length -"+ohlevel);
            for (var i = 0; i < ohlevel.length; i++) {
                OhLevel1[i] = ohlevel[i]["remark"];
                //alert("oh level remark -"+ohlevel[i]["remark"]); 
                analyse();
            }
        }
    });
    var fuel_data = [];
    for (var i = 0; i < OhLevel1.length; i++) {
        fuel_data.push({"time": dateTime2[i], "vol": OhLevel1[i]});
    }
    return fuel_data;

}

function vehicleData() {
    
    var accuracy = $("#XAccuracy").val();
    var voltage = $("#XVoltage").val();
    var fuelStatus = $("#XFuelStatus").val();
   // var speed = $("#speed").val();
   
    //alert("accu racy --" + accuracy + "---voltage ---" + voltage + "-- fuel status --" + fuelStatus);
    //alert("accu value -"+accu);
    var vehicle_data = [
//        {label: 'Speed', value: speed},
        {label: 'Accuracy', value: accuracy},
        {label: 'Voltage', value: voltage},
        {label: 'Fuel Status', value: fuelStatus}
    ];
     return vehicle_data;
}

function check() {
   // debugger;
    G.setData(updateData());
    M.setData(volumeData());
    S.setData(vehicleData());

}

var G = Morris.Line({
    element: 'bar-example',
    data: updateData(),
    xkey: 'time',
    parseTime: false,
    ykeys: ['vol'],
    labels: ['Level'],
    lineColors: ['#4ECDC4'],
    redraw: true,
});

var M = Morris.Bar({
    element: 'bar-mileage',
    data: updateData(),
    xkey: 'time',
    ykeys: ['vol'],
    labels: ['Mileage'],
    lineColors: ['#4ECDA4'],
    barColors: ['#006700'],
    redraw: true,
});

var S = Morris.Donut({
    element: 'hero-pie',
    data: vehicleData(),
    
    colors: ['#3498db', '#2980b9', '#34495e'],
    formatter: function (y) {
        return y
    }
});


//#006700

//var S = Morris.Donut({
//    element: 'hero-donut',
//    data: [
//        {label: 'Speed', value: 25},
//        {label: 'Accuracy', value: 40},
//        {label: 'Voltage', value: 25},
//        {label: 'Fuel Status', value: 10}
//    ],
//    colors: ['#3498db', '#2980b9', '#34495e'],
//    formatter: function (y) {
//        return y + "%"
//    }
//});

function analyse()
{
  
        $.ajax({
                    dataType: "json",
                    //                    async: false,
                    type: "POST",
                    url: "DashboardController?task=view&task1=check",
                    //data:{data: vkp_id},
                    
                    success: function (response_data) {
                        //debugger;
                        var fuel = response_data.data;
                        tank_height = 1000;
                        var empty_height;

                        for (var i = 0; i < fuel.length; i++) {
                            var level = fuel[i]["id"];
                          
                             
                            var refresh = fuel[i]["id5"];
                            var fuel_left_litre = fuel[i]["id6"];
                           
                            var date = fuel[i]["id2"];
                           
                            var accuracy = fuel[i]["id3"];
                           
                            var voltage = fuel[i]["id4"];
                             
                            var latitude = fuel[i]["id7"];
                           
                            var longitude = fuel[i]["id8"];
                              var speed = fuel[i]["id9"];
                           
                                
                            var a = level.split(",");
                            //                            document.getElementById("fuel_level").value = level;
                            //                            document.getElementById("datetime").value = date;
                            //                            document.getElementById("accuracy").value = accuracy;
                            //                            document.getElementById("voltage").value = voltage;
                            //$("#XDatetime").text(date);
                            //alert("date value -"+date);
                            //document.getElementById("XDatetime").value = date;
                            $("#XAccuracy").val(accuracy);
                            
                            $("#XVoltage").val(voltage);
                            $("#XFuelStatus").val(level);
                            //$("#XFuelLeft").text(level);
                            $("#Date_Time").text("Current Date : "+date);
                            
                            $("#fuel_level_litre").val(fuel_left_litre);
                            $("#NewDateTime").val(refresh);
                            $("#latitude").val(latitude);
                            $("#longitude").val(longitude);
                              $("#speed").val(speed);
                            // for fuel icon
                            empty_height = tank_height - level;
                            $("#XEmptyTank").val(empty_height);
                            $("#XFulltank").val(tank_height);

                        }
                        //                        if (response_data.success == true) {
                        //                            setTimeout(function () {
                        //                                location.reload();
                        //                            }, 3000);
                        //                        }
                    }

                });
                //             
    
}