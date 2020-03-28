<%-- 
    Document   : firstPageForclassic
    Created on : Jan 16, 2019, 11:32:19 AM
    Author     : rituk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="http://localhost:8084/supermap-libs/classic/libs/SuperMap.Include.js"></script>
        
        <script>
            var map,layer;
            var url="http://localhost:8090/iserver/services/map-world/rest/maps/World";
            
            function onPageLoad(){
                map = new SuperMap.Map("map");               
            
            
            // Create layer
     layer = new SuperMap.Layer.TiledDynamicRESTLayer("World", url, {transparent: true, cacheEnabled: true}, {maxResolution:"auto"}); 
     layer.events.on({"layerInitialized": addLayer}); 
            }

        function addLayer() {
     // Add layer into the map
     map.addLayer(layer);
     map.setCenter(new SuperMap.LonLat(0, 0), 0);
      } 				
            
            
           </script>
    </head>
    <body onload="onPageLoad();">
       <div id="map" style="position:relative;left:0px;right:0px;width:800px;height:500px;">             
     </div>
       
    </body>
</html>
