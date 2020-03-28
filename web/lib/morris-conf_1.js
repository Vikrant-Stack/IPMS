
function checkkk() {//alert(111); debugger;
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
            }
        }
    });

alert("yahan aa gaya");
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
function updateData(){
 var fuel_data = [];
    for (var i = 0; i < OhLevel1.length; i++) {
        fuel_data.push({"time": dateTime2[i], "vol": OhLevel1[i]});
    }
    return fuel_data;
    alert("fuel data -"+fuel_data.length);
}



// other testing

var goodComments = 0,
    badComments = 3,
    neutralComments = 1,
    G;

  function updateData() {//debugger;
      
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
    
    
      var fuel_data = [];
    for (var i = 0; i < OhLevel1.length; i++) {
        fuel_data.push({"time": dateTime2[i], "vol": OhLevel1[i]});
    }
    return fuel_data;

  }

  function check(){//alert(121);  debugger;
    G.setData(updateData());
    M.setData(updateData());
  }

  var G = Morris.Bar({
    element: 'bar-example',
    data: updateData(),
    xkey: 'time',
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
  
  //#006700

Morris.Donut({
        element: 'hero-donut',
        data: [
          {label: 'Speed', value: 25 },
          {label: 'Accuracy', value: 40 },
          {label: 'Voltage', value: 25 },
          {label: 'Fuel Status', value: 10 }
        ],
          colors: ['#3498db', '#2980b9', '#34495e'],
        formatter: function (y) { return y + "%" }
      });