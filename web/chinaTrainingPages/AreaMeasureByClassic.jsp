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
            var map, layer, markerlayer, marker, points, vector, pointVector, osmLayer,
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
                osmLayer = new SuperMap.Layer.OSM();
                //layer = new SuperMap.Layer.TiledDynamicRESTLayer("World", url, null, {maxResolution: "auto"});
                //layer.events.on({"layerInitialized": addLayer});
                //markerlayer = new SuperMap.Layer.Markers("markerLayer");
                vector = new SuperMap.Layer.Vector("vector");
                map.addLayers([osmLayer, vector]);
                //Display the map extent
                map.setCenter(new SuperMap.LonLat(8117383.7784246, 2574751.9467887), 3);
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

                var point = [new SuperMap.Geometry.Point(-120, 54.142),
                    new SuperMap.Geometry.Point(-110, 40),
                    new SuperMap.Geometry.Point(-120, 25.857),
                    new SuperMap.Geometry.Point(-140, 25.857),
                    new SuperMap.Geometry.Point(-150, 40)
                   ];
                //console.log(point);
                //var point_new = point.transform(new SuperMap.Projection("EPSG:4326"), new SuperMap.Projection("EPSG:3857"));
                // point_new.tag =
                //point = SuperMap.Projection.transform(point,new SuperMap.Projection("ESPG:4326"), new SuperMap.Projection("ESPG:3857"));

                // var size = new SuperMap.Size(44, 33);
                // var offset = new SuperMap.Pixel(-(size.w / 2), -size.h);
                //var icon = new SuperMap.Icon('http://localhost:8084/supermap-libs/classic/theme/images/marker.png', size, offset);
                // marker = new SuperMap.Marker(new SuperMap.LonLat([data[i].longitude, data[i].latitude]), icon);
                // console.log(marker);
                // markerlayer.addMarker(marker);

                var linearRings = new SuperMap.Geometry.LinearRing(point);
                var region = new SuperMap.Geometry.Polygon([linearRings]);

               var  polyvector = new SuperMap.Feature.Vector(region);
                vector.addFeatures([polyvector]);

                var geometry = polyvector.geometry;

                measureParam = new SuperMap.REST.MeasureParameters(geometry), /* MeasureParameters: measurement parameter class.*/
                        myMeasuerService = new SuperMap.REST.MeasureService(url); //measurement service class
                myMeasuerService.events.on({"processCompleted": measureCompleted});

                //Determine the type of MeasureService. When it is LineString, it is set as MeasureMode.DISTANCE, or it will be MeasureMode.AREA

                myMeasuerService.measureMode = SuperMap.REST.MeasureMode.AREA;

                myMeasuerService.processAsync(measureParam);

                //  }
                //});

            }

            function measureCompleted(measureEventArgs) {
            var   area = measureEventArgs.result.area,
                    unit = measureEventArgs.result.unit;
            alert("Result is: "+ area + "Square Kilometers");
        }

        </script>
    </head>
    <body onload="init()" >
        <div id="map"></div>

    </body>
</html>
