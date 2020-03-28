<%-- 
    Document   : simpleBrowserShow
    Created on : Jan 16, 2019, 9:25:26 AM
    Author     : rituk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title data-i18n="resources.title_imageMapLayer3857"></title>
    
</head>
<body style=" margin: 0;overflow: hidden;background: #fff;width: 100%;height:100%;position: absolute;top: 0;">
<div id="map" style="margin:0 auto;width: 100%;height: 100%"></div>
<script type="text/javascript" src="http://localhost:8084/supermap-libs/dist/leaflet/include-leaflet.js"></script>
<script type="text/javascript">

        var osmUrl = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
        var osmAttrib = 'Map data © <a href="https://openstreetmap.org">OpenStreetMap</a> contributors';
        var osm = new L.TileLayer(osmUrl, { attribution: osmAttrib });

        var map = L.map('map', {
            crs: L.CRS.EPSG3857,
            center: [28.613939, 77.209023],
            zoom: 9
        });
        map.addLayer(osm);

</script>
</body>
</html>
