<%--
    Document   : classicpageForMarker
    Created on : Jan 16, 2019, 12:04:01 PM
    Author     : rituk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <script type="text/javascript" src="JS/jquery-1.4.2.min.js"></script>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <title>Marker Layer</title>
        <style type="text/css">
            body{
                margin: 0;
                overflow: hidden;
                background: #fff;
            }
            #map{
                position: relative;
                height: 553px;
                border:1px solid #3473b7;
            }
        </style>
        <script src = "http://192.168.1.7:8084/supermap-libs/classic/libs/SuperMap.Include.js"></script>
        <script type="text/javascript">
            var map, layer, markerlayer, marker, points,
                    //host = document.location.toString().match(/file:\/\//) ? "http://localhost:8090" : 'http://' + document.location.host;
                    //url = host + "/iserver/services/map-world/rest/maps/World";
                    url = "http://192.168.1.21:8090/iserver/services/map-world/rest/maps/World";

            function init()
            {
                map = new SuperMap.Map("map", {controls: [
                        new SuperMap.Control.Zoom(),
                        new SuperMap.Control.Navigation(),
                        new SuperMap.Control.LayerSwitcher()
                    ]});
                layer = new SuperMap.Layer.TiledDynamicRESTLayer("World", url, null, {maxResolution: "auto"});
                layer.events.on({"layerInitialized": addLayer});
                markerlayer = new SuperMap.Layer.Markers("markerLayer");
                addData();
            }

            var infowin = null;
        //Define the mouseClickHandler function. This function will be called when trigger this click event
        function mouseClickHandler(event){
            closeInfoWin();
            //Initialize the popup class         '<img src="supermap-libs/classic/theme/images/marker.png">'  http://114.110.17.6:8896/image.jpg?type=motion&amp;camera=1" style="width:300px;height:150px" alt="Loading CCTV Stream
            popup = new SuperMap.Popup(
                    "chicken",
                    marker.getLonLat(),
                    new SuperMap.Size(350,250),
                    '<video width ="480" height="320" controls="controls">'+
//                    ' <source src="supermap-libs/classic/theme/videos/v1.mp4" type="video/mp4">'+
                    ' <source src="https://www.radiantmediaplayer.com/media/bbb-360p.mp4" type="video/mp4">'+
                     '</video>',
                    true,
                    null
            );

            infowin = popup;
            //Add the popup to the map layer
            map.addPopup(popup);
        }

        function closeInfoWin(){
            if(infowin){
                        try {
                            infowin.hide();
                            infowin.destroy();
                        } catch (e) {
                        }
                    }
                }


            function addLayer() {

                map.addLayers([layer, markerlayer]);
                //Display the map extent
                map.setCenter(new SuperMap.LonLat(0, 0), 17);

            }
            //Add data
            function addData()
            {
                                                             
                            var size = new SuperMap.Size(44, 33);
                            var offset = new SuperMap.Pixel(-(size.w / 2), -size.h);
                            var icon = new SuperMap.Icon('http://192.168.1.7:8084/supermap-libs/classic/theme/images/marker.png', size, offset);
                            marker = new SuperMap.Marker(new SuperMap.LonLat([77.413881, 28.499977]), icon);
                            console.log(marker);
                            markerlayer.addMarker(marker);
                            marker.events.on({"click":mouseClickHandler});
                                                          
            }

        </script>
    </head>
    <body onload="init()" >
        <div id="map"></div>

    </body>
</html>
