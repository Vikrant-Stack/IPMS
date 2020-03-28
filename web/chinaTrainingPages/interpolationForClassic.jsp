<%-- 
    Document   : interpolationForClassic
    Created on : Jan 16, 2019, 2:31:25 PM
    Author     : rituk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type">
        <script type="text/javascript" src="JS/jquery-1.4.2.min.js"></script>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <title>Discrete Points Interpolation Analysis</title>
        <style type="text/css">
        body{
        margin: 0;
        overflow: hidden;
        background: #fff;
        }
        #map{
        position: relative;
        height: 500px;
        border:1px solid #3473b7;
        }

        #toolbar {
        position: relative;
        padding-top: 5px;
        padding-bottom: 10px;
        }
        </style>
        <script src = "http://localhost:8084/supermap-libs/classic/libs/SuperMap.Include.js"></script>
        <script type="text/javascript">
        //var host = document.location.toString().match(/file:\/\//)?"http://localhost:8090":'http://' + document.location.host,
        //url=host+"/iserver/services/map-temperature/rest/maps/ChinaTemperatureMap",
                    //url2 = host + "/iserver/services/spatialanalyst-sample/restjsr/spatialanalyst";
           var urlMap = "http://localhost:8090/iserver/services/map-interpolation-excercise/rest/maps/interpolattionMap";
           var urlSpatial= "http://localhost:8090/iserver/services/spatialAnalysis-interpolation-excercise/restjsr/spatialanalyst";
            var map, baseLayer, themeLayer, points = [];
            function init() {
                map = new SuperMap.Map("map", {controls: [
                        new SuperMap.Control.LayerSwitcher(),
                        new SuperMap.Control.ScaleLine(),
                        new SuperMap.Control.Zoom(),
                        new SuperMap.Control.Navigation({
                            dragPanOptions: {
                                enableKinetic: true
                            }
                        })], units: "m"
                });
                map.allOverlays = true;
                osmLayer = new SuperMap.Layer.OSM();
                map.addLayers([osmLayer]);
                map.setCenter(new SuperMap.LonLat(8117383.7784246, 2574751.9467887), 15);
            }
            
        //Discrete points interpolation analysis
            function cretePointsAnalyst() {
                removeInterpolation();
                sqlService_ProcessCompleted();
        //Query used for getting the geometry for the interpolation analysis
                
            }
            function sqlService_ProcessCompleted() {
                //var feature, recordsets = queryEventArgs.result.recordsets;

                $.ajax({url: "InterpolationCont.do?task=showMapWindow2&client_task="+client_task+"",
                    //type: 'POST',
                    dataType: 'json',
                    //contentType: 'application/json',
                    //context: document.body,
                    success: function(response_data)
        {

            //points = [];
                var data=response_data.data;
                        for(var i=0;i<20;i++)
                        {
                    //var point = L.latLng([data[i].latitude, data[i].longitude, data[i].altitude]);
                    var point = new SuperMap.Geometry.Point([data[i].longitude], [data[i].latitude]);
                    var point_new = point.transform(new SuperMap.Projection("EPSG:4326"), new SuperMap.Projection("EPSG:3857"));
                    //每个插值点在插值过程中的权重值
                    //z = Math.random() * (zMax - zMin) + zMin;
                    point_new.tag = data[i].altitude;
                     points.push(point_new);
                                }
        //IDW
                var interpolationParams = new SuperMap.REST.InterpolationIDWAnalystParameters({
        //Interpolation analysis type
                    InterpolationAnalystType: "geometry",
        //The name of the interpolation analysis result dataset.
                    outputDatasetName: "IDWcretePoints_result",
        //The name of the result datasource in the interpolation analysis.
                    outputDatasourceName: "vector",
        //The pixel format of the output grid dataset.
                    pixelFormat: SuperMap.REST.PixelFormat.double,
        //The discrete points collection used for the interpolation analysis.
                    inputPoints: points,
        //Attribute filter condition
                    filterQueryParameter: {
                        attributeFilter: ""
                    },
        //Adopt the fixed radius search mode
                    searchMode: "KDTREE_FIXED_COUNT",
        //Search radius which has the same unit with points
                    expectedCount: 12,
                    resolution: 1.33333,
                    bounds: new SuperMap.Bounds(8115706.2245124, 2574396.7534803, 8119370.4245554, 5921501.395578556)
                });
                //console.log(points);
                var interpolationService = new SuperMap.REST.InterpolationAnalystService(urlSpatial, {
                    eventListeners: {
                        "processCompleted": processCompleted,
                        "processFailed": processFailed
                    }});
                interpolationService.processAsync(interpolationParams);
            }

                });
            }
        ///After the interpolation is completed, the grid ranges map will be used for the display
            function processCompleted(InterpolationAnalystEventArgs) {
                console.log(InterpolationAnalystEventArgs);
                var color1 = new SuperMap.REST.ServerColor(170, 240, 233),
                        color2 = new SuperMap.REST.ServerColor(176, 244, 188),
                        color3 = new SuperMap.REST.ServerColor(218, 251, 178),
                        color4 = new SuperMap.REST.ServerColor(220, 236, 145),
                        color5 = new SuperMap.REST.ServerColor(96, 198, 66),
                        color6 = new SuperMap.REST.ServerColor(20, 142, 53),
                        color7 = new SuperMap.REST.ServerColor(85, 144, 55),
                        color8 = new SuperMap.REST.ServerColor(171, 168, 38),
                        color9 = new SuperMap.REST.ServerColor(235, 165, 9),
                        color10 = new SuperMap.REST.ServerColor(203, 89, 2),
                        color11 = new SuperMap.REST.ServerColor(157, 25, 1),
                        color12 = new SuperMap.REST.ServerColor(118, 15, 3),
                        color13 = new SuperMap.REST.ServerColor(112, 32, 7),
                        color14 = new SuperMap.REST.ServerColor(106, 45, 12),
                        color15 = new SuperMap.REST.ServerColor(129, 80, 50),
                        color16 = new SuperMap.REST.ServerColor(160, 154, 146),
                        color17 = new SuperMap.REST.ServerColor(107, 47, 14),
                        color18 = new SuperMap.REST.ServerColor(125, 75, 44),
                        color19 = new SuperMap.REST.ServerColor(146, 110, 88),
                        color20 = new SuperMap.REST.ServerColor(166, 153, 146),
                        themeGridRangeIteme1 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 22,
                            end: 24,
                            color: color1
                        }),
                        themeGridRangeIteme2 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 24,
                            end: 26,
                            color: color2
                        }),
                        themeGridRangeIteme3 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 26,
                            end: 28,
                            color: color3
                        }),
                        themeGridRangeIteme4 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 28,
                            end: 30,
                            color: color4
                        }),
                        themeGridRangeIteme5 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 30,
                            end: 32,
                            color: color5
                        }),
                        themeGridRangeIteme6 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 32,
                            end: 34,
                            color: color6
                        }),
                        themeGridRangeIteme7 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 34,
                            end: 36,
                            color: color7
                        }),
                        themeGridRangeIteme8 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 36,
                            end: 38,
                            color: color8
                        }),
                        themeGridRangeIteme9 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 38,
                            end: 40,
                            color: color9
                        }),
                        themeGridRangeIteme10 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 40,
                            end: 42,
                            color: color10
                        }),
                        themeGridRangeIteme11 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 42,
                            end: 44,
                            color: color11
                        }),
                        themeGridRangeIteme12 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 44,
                            end: 46,
                            color: color12
                        }),
                        themeGridRangeIteme13 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 46,
                            end: 48,
                            color: color13
                        }),
                        themeGridRangeIteme14 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 48,
                            end: 50,
                            color: color14
                        }),
                        themeGridRangeIteme15 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 50,
                            end: 52,
                            color: color15
                        }),
                        themeGridRangeIteme16 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 52,
                            end: 54,
                            color: color16
                        }),
                        themeGridRangeIteme17 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 54,
                            end: 56,
                            color: color17
                        }),
                        themeGridRangeIteme18 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 56,
                            end: 58,
                            color: color18
                        }),
                        themeGridRangeIteme19 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 58,
                            end: 60,
                            color: color19
                        }),
                        themeGridRangeIteme20 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 60,
                            end: 62,
                            color: color20
                        }),
                        themeGridRange = new SuperMap.REST.ThemeGridRange({
                            reverseColor: false,
                            rangeMode: SuperMap.REST.RangeMode.EQUALINTERVAL,
        //The grid ranges item array
                            items: [themeGridRangeIteme1,
                                themeGridRangeIteme2,
                                themeGridRangeIteme3,
                                themeGridRangeIteme4,
                                themeGridRangeIteme5,
                                themeGridRangeIteme6,
                                themeGridRangeIteme7,
                                themeGridRangeIteme8,
                                themeGridRangeIteme9,
                                themeGridRangeIteme10,
                                themeGridRangeIteme11,
                                themeGridRangeIteme12,
                                themeGridRangeIteme13,
                                themeGridRangeIteme14,
                                themeGridRangeIteme15,
                                themeGridRangeIteme16,
                                themeGridRangeIteme17,
                                themeGridRangeIteme18,
                                themeGridRangeIteme19,
                                themeGridRangeIteme20
                            ]
                        }),
                        themeParameters = new SuperMap.REST.ThemeParameters({
        //The dataset array for making the thematic map
                            datasetNames: [InterpolationAnalystEventArgs.result.dataset.split('@')[0]],
        //The array of datasources that contain the datasets for making the thematic map
                            dataSourceNames: [InterpolationAnalystEventArgs.result.dataset.split('@')[1]],
                            joinItems: null,
        //The thematic map list
                            themes: [themeGridRange]
                        });
                var themeService = new SuperMap.REST.ThemeService(urlMap, {eventListeners: {"processCompleted": themeCompleted, "processFailed": themeFailed}});
                themeService.processAsync(themeParameters);
            }
            function themeCompleted(themeEventArgs) {
                if (themeEventArgs.result.resourceInfo.id) {
                    themeLayer = new SuperMap.Layer.TiledDynamicRESTLayer("Result map of interpolation analysis", urlMap, {cacheEnabled: true, transparent: true, layersID: themeEventArgs.result.resourceInfo.id}, {"maxResolution": "auto"});
                    themeLayer.events.on({"layerInitialized": addThemelayer});

                }
            }
            function addThemelayer() {
                map.addLayer(themeLayer);

            }
            function processFailed(ServiceFailedEventArgs) {
                alert(ServiceFailedEventArgs.error.errorMsg);
            }
            function themeFailed(serviceFailedEventArgs) {
                alert(serviceFailedEventArgs.error.errorMsg);
            }
            function removeInterpolation() {
                if (map.layers.length > 1) {
                    map.removeLayer(themeLayer, true);
                }
            }

        </script>
    </head>
    <body onload="init()">
        <div id="toolbar">
            <span style='font-size: 1.2em;'>Z Value Min:</span>
            <input type='number' style='width:50px;height: 25px' id='zMin' value='-5'/>
            <span style='font-size: 1.2em;'>Max:</span>
            <input type='number' style='width:50px;height: 25px' id='zMax' value='28'/>
            <input type="hidden" id="equip_name" value="${equip_name}" >
            <input type="hidden" id="client_name" value="${client_name}" >
            <input type="hidden" id="client_task" value="${client_task}" >
            <input type="button" class="btn" value="Discrete Points Interpolation Analysis" onclick="cretePointsAnalyst()" />
            <input type="button" class="btn" value="Remove" onclick="removeInterpolation()" />
        </div>
        <div id="map"></div>
    </body>
</html>
