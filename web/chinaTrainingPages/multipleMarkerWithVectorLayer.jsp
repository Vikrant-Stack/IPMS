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
        <script src = "http://localhost:8084/supermap-libs/classic/libs/SuperMap.Include.js"></script>
        <script type="text/javascript">
            var map, layer, markerlayer, marker, points,vector,pointVector,osmLayer,
                    //host = document.location.toString().match(/file:\/\//) ? "http://localhost:8090" : 'http://' + document.location.host;
                    //url = host + "/iserver/services/map-world/rest/maps/World";
                    url = "http://localhost:8090/iserver/services/map-world/rest/maps/World";

            function init()
            {
                map = new SuperMap.Map("map", {controls: [
                        new SuperMap.Control.Zoom(),
                        new SuperMap.Control.Navigation(),
                        new SuperMap.Control.LayerSwitcher()
                    ]});
                //layer = new SuperMap.Layer.TiledDynamicRESTLayer("World", url, null, {maxResolution: "auto"});
                //layer.events.on({"layerInitialized": addLayer});
                //markerlayer = new SuperMap.Layer.Markers("markerLayer");
                osmLayer = new SuperMap.Layer.OSM();              
                vector = new SuperMap.Layer.Vector("vector");
                map.addLayers([osmLayer, vector]);
                //Display the map extent
                map.setCenter(new SuperMap.LonLat(8117383.7784246, 2574751.9467887), 15);
                addData();
            }
//            function addLayer() {
//
//                map.addLayers([layer, vector]);
//                //Display the map extent
//                map.setCenter(new SuperMap.LonLat(0, 0), 17);
//
//            }
            //Add data
            function addData()
            {
                $.ajax({url: "InterpolationCont.do?task=showMapWindow2",
                    //type: 'POST',
                    dataType: 'json',
                    //contentType: 'application/json',
                    //context: document.body,
                    success: function (response_data)
                    {
                        
                        points = [];
                        var data = response_data.data;
                        for (var i = 0; i < data.length; i++)
                        {
                            var point = new SuperMap.Geometry.Point([data[i].longitude], [data[i].latitude]);
                            //console.log(point);
                            var point_new = point.transform(new SuperMap.Projection("EPSG:4326"), new SuperMap.Projection("EPSG:3857"));
                           // point_new.tag =
                             //point = SuperMap.Projection.transform(point,new SuperMap.Projection("ESPG:4326"), new SuperMap.Projection("ESPG:3857"));
                             console.log(point_new);
                              points.push(point_new);



                           // var size = new SuperMap.Size(44, 33);
                           // var offset = new SuperMap.Pixel(-(size.w / 2), -size.h);
                            //var icon = new SuperMap.Icon('http://localhost:8084/supermap-libs/classic/theme/images/marker.png', size, offset);
                           // marker = new SuperMap.Marker(new SuperMap.LonLat([data[i].longitude, data[i].latitude]), icon);
                           // console.log(marker);
                                                // markerlayer.addMarker(marker);
                        }
                  console.log(points);
                        var line1 = new SuperMap.Geometry.LineString(points);

                        var lineVector = new SuperMap.Feature.Vector(line1);
                        lineVector.style= {

                            strokeColor : "#7B68EE",
                            strokeWidth : 4
                        }
                        vector.addFeatures([lineVector]);

                    }
                });

            }

        </script>
    </head>
    <body onload="init()" >
        <div id="map"></div>

    </body>
</html>
