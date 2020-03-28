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
            var map, layer, markerlayer, marker,
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
                layer = new SuperMap.Layer.TiledDynamicRESTLayer("World", url, null, {maxResolution: "auto"});
                layer.events.on({"layerInitialized": addLayer});
                markerlayer = new SuperMap.Layer.Markers("markerLayer");
                addData();
            }
            function addLayer() {

                map.addLayers([layer, markerlayer]);
        //Display the map extent
                map.setCenter(new SuperMap.LonLat(0, 0), 0);

            }
        //Add data
            function addData()
            {
                markerlayer.removeMarker(marker);
                var size = new SuperMap.Size(44, 33);
                var offset = new SuperMap.Pixel(-(size.w / 2), -size.h);
                var icon = new SuperMap.Icon('http://localhost:8084/supermap-libs/classic/theme/images/marker.png', size, offset);
                marker = new SuperMap.Marker(new SuperMap.LonLat(116.3, 39.9), icon);
                markerlayer.addMarker(marker);
            }
        </script>
    </head>
    <body onload="init()" >
        <div id="map"></div>

    </body>
</html>
