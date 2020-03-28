/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



     jQuery(function () {

var dataPoints1 = [];
var dataPoints2 = [];
var l=1;

var chart = new CanvasJS.Chart
           ("chartContainer",
           {
               animationEnabled: true,
animationDuration: 2000,
title: {
text: "Average Fuel Per Hour"
},
axisX: {
// valueFormatString: "MMM YYYY"
},
axisY2: {
title: "Volume"

},
toolTip: {
shared: true
},
legend: {
cursor: "pointer",
               
verticalAlign: "top",
horizontalAlign: "center",
dockInsidePlotArea: true,
itemclick: toogleDataSeries
},
data: [
            {
type:"line",
               
axisYType: "secondary",
name: "Fuel Level",
showInLegend: true,
                markerType: "circle",

yValueFormatString: "####.### ltr",
dataPoints: dataPoints1
}

    ]
});

var chart2 = new CanvasJS.Chart
           ("chartContainer2",
           {
               animationEnabled: true,
animationDuration: 1000,
title: {
text: "Mileage per Hour"
},
axisX: {
// valueFormatString: "MMM YYYY"
},
axisY2: {
title: "KM/LTR"

},
toolTip: {
shared: true
},
legend: {
cursor: "pointer",
               
verticalAlign: "top",
horizontalAlign: "center",
dockInsidePlotArea: true,
itemclick: toogleDataSeries
},
data: [
            {
type:"line",
               
axisYType: "secondary",
name: "Mileage",
showInLegend: true,
                markerType: "circle",

yValueFormatString: "##.#### km/ltr",
dataPoints: dataPoints2
}

    ]
});
function toogleDataSeries(e){
if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
e.dataSeries.visible = false;
} else{
e.dataSeries.visible = true;
}
chart.render();
}

function updateData()
{        
      
    var datetime;
    var ohlevel;
    var dateTime1 = [];
    var newTime = [];
    OhLevel1 = [];
    dateTime2 = [];
//    dataPoints1 = null;
//    dataPoints2 = null;
   
    var fuellvl=[];

    var dt = new Date();
    $.ajax({
        dataType: "json",
        async: false,
        url: "CanvasJSController?task=getAllDateTime",
        success: function (response_data) {//alert("response data -"+response_data.dateTime);
           
//            var fuelobj={
//        x:String,
//        y:String
//    };
            datetime = response_data.dateTime;
            //alert("date time length-"+datetime.length);
            for (var i = 0; i < datetime.length; i++) {//alert("date time array -"+datetime[i]["date_time"]);
                 //debugger;
                 
               
                 
                 
               var d = datetime[i]["date_time"];
                var a = d.split(",");
                dateTime1[i] = new Date(a[0], a[1], a[2], a[3], "00");
//                dateTime1[i] = new Date(a[0], a[1], a[2], a[3]);
//                newTime[i]=dateTime1[i].getHours();
//                //alert("date time -"+dateTime1[i]);
//                //time.setTime(dateTime1[i].getTime());
//                //alert("timeeee -"+dateTime1[i].getTime());
//                var hour = dateTime1[i].getHours() - (dateTime1[i].getHours() >= 12 ? 12 : 0);
//                if (hour == 0) {
//                    hour = 12;
//                }
//                var period = dateTime1[i].getHours() >= 12 ? 'PM' : 'AM';
//                //alert(hour + ':' + dateTime1[i].getMinutes() + ' ' + period);
////                fuelobj.x = hour + ':' + dateTime1[i].getMinutes() + ' ' + period;
////                //alert("date time 222 -"+dateTime2[i]);
////                 fuelobj.x = dateTime1[i];
////                 fuelobj.y = datetime[i]["fuel"];
              if(l === 1)
              {
                  dataPoints1.push({
x:dateTime1[i],
y: Number(datetime[i]["averagefuel"])
});

                dataPoints2.push({
x: dateTime1[i],
y: Number(datetime[i]["milage"])
});
                  l++;
              }
              else
              {
                   var same=0;
               for(var k = 0; k < dataPoints1.length; k++)
               {
                   if ((dataPoints1[k].x == dateTime1[i]))

               {
               same=1;
    }

            }
             if (same  < 1)

               {
                 dataPoints1.push({
x:(dateTime1[i]),
y: Number(datetime[i]["averagefuel"])
});

                dataPoints2.push({
x: (dateTime1[i]),
y: Number(datetime[i]["milage"])
});
                 
               
    }
                 
              }
             
        }
        }
    });

 
 

    var fuel_data = [];
    for (var i = 0; i < OhLevel1.length; i++) {
        fuel_data.push({"time": dateTime2[i], "vol": OhLevel1[i]});
    }
    console.log(fuellvl);
    chart.render();
    chart2.render();
   
}


setInterval(function(){updateData();}, 5000);




});


