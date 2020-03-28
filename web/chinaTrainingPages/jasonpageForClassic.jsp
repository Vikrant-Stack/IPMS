<%-- 
    Document   : jasonpageForClassic
    Created on : Jan 17, 2019, 8:01:49 AM
    Author     : rituk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <script type="text/javascript" src="JS/jquery-1.4.2.min.js"></script>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <title data-i18n="resources.title_osmlayer"></title>
    </head>
    <body style=" margin: 0;overflow: hidden;background: #fff;width: 100%;height:100%;position: absolute;top: 0;" onload="init()">
        <div id="toolbar">
                <input type="button" class="btn" value="IDW" onclick="interpolation()" />
                <input type="button" class="btn" value="Clear" onclick="removeInterpolation()" >
        </div>
        <div id="map" style="margin:0 auto;width: 100%;height: 100%"></div>

        <!--<script type="text/javascript" include="bootstrap,widgets.alert" src="../js/include-web.js"></script>-->
        <script src="http://localhost:8084/supermap-libs/classic/libs/SuperMap.Include.js"></script>
        <script type="text/javascript">
                var map, osmLayer,points;
                var url_map = "http://localhost:8090/iserver/services/map-interpolation-excercise/rest/maps/interpolattionMap";
            var url_spatial = "http://localhost:8090/iserver/services/spatialAnalysis-interpolation-excercise/restjsr/spatialanalyst";
            function init() {
                map = new SuperMap.Map("map", {
                    controls: [
                        new SuperMap.Control.Navigation(),
                        new SuperMap.Control.Zoom(),
                        new SuperMap.Control.LayerSwitcher(),
                        new SuperMap.Control.MousePosition()
                    ]
                });
                map.allOverlays = true;

                layer = new SuperMap.Layer.TiledDynamicRESTLayer("Points", url_map, {transparent: true}, {maxResolution: "auto"});
                //监听图层信息加载完成事件
                layer.events.on({"layerInitialized": addLayer});
            }
            function addLayer() {
                osmLayer = new SuperMap.Layer.OSM("OSM");
                map.addLayers([osmLayer, layer]);
                map.setCenter(new SuperMap.LonLat(8117383.7784246, 2574751.9467887), 16);
            }
            function transform() {
                var point = new SuperMap.Geometry.Point(10, 20);
                console.log(point);
                point.transform(new SuperMap.Projection("EPSG:4326"), new SuperMap.Projection("EPSG:3857"));
                console.log(point);
            }
            function interpolation() {
                removeInterpolation();
                alert("in inter");

                 $.ajax({url: "InterpolationCont.do?task=showMapWindow2",
                    //type: 'POST',
                    dataType: 'json',
                    //contentType: 'application/json',
                    //context: document.body,
                    success: function(response_data)
               {

                 points = [];
                var data=response_data.data;
                        for(var i=0;i<data.length;i++)
                        {
                    //var point = L.latLng([data[i].latitude, data[i].longitude, data[i].altitude]);
                    var point = new SuperMap.Geometry.Point([data[i].longitude], [data[i].latitude]);
                    var point_new = point.transform(new SuperMap.Projection("EPSG:4326"), new SuperMap.Projection("EPSG:3857"));
                                    //每个插值点在插值过程中的权重值
                                    //z = Math.random() * (zMax - zMin) + zMin;
                                    point_new.tag = data[i].altitude;
                                    points.push(point_new);
                                }
        //		var interpolationParams = new SuperMap.REST.InterpolationIDWAnalystParameters({
        //          //用于做插值分析的数据源中数据集的名称
        //          dataset: "test1@test",
        //          //插值分析结果数据集的名称
        //          outputDatasetName: "demo",
        //          //插值分析结果数据源的名称
        //          outputDatasourceName: "test",
        //          //结果栅格数据集存储的像素格式
        //          pixelFormat: SuperMap.REST.PixelFormat.double,
        //          // 属性过滤条件
        ////            filterQueryParameter: {
        ////                attributeFilter: ""
        ////            },
        //          //存储用于进行插值分析的字段名称
        //          zValueFieldName: "z",
        //          resolution: 1.3399076587595,
        //          //采取固定点数查找参与运算点的方式
        //          searchMode: "KDTREE_FIXED_COUNT",
        //          //固定点数查找方式下,参与差值运算的点数默认为12。
        //          expectedCount: 12,
        //          bounds: new SuperMap.Bounds(8116835.29 , 2574869.39, 8118346.79 , 2575539.34)
        //      });


                var interpolationParams = new SuperMap.REST.InterpolationIDWAnalystParameters({
                    //插值分析类型
                    InterpolationAnalystType: "geometry",
                    //插值分析结果数据集的名称
                    outputDatasetName: "IDWcretePoints_result",
                    //插值分析结果数据源的名称
                    outputDatasourceName: "vector",
                    //结果栅格数据集存储的像素格式
                    pixelFormat: SuperMap.REST.PixelFormat.double,
                    //用于做插值分析的离散点集合
                    inputPoints: points,
                    // 属性过滤条件
                    filterQueryParameter: {
                        attributeFilter: ""
                    },
                    //采取定长查找参与运算点的方式
                    searchMode: "KDTREE_FIXED_RADIUS",
                    // 查找半径,与点数据单位相同
                    searchRadius: 200,
                    resolution: 1.3,
                    bounds: new SuperMap.Bounds(8115706.2245124, 2574396.7534803, 8119370.4245554, 5921501.395578556)
                });

                var interpolationService = new SuperMap.REST.InterpolationAnalystService(url_spatial, {
                    eventListeners: {
                        "processCompleted": processCompleted,
                        "processFailed": processFailed
                    }
                });
                interpolationService.processAsync(interpolationParams);
            }
                 });

                 }

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
                            start: 20,
                            end: 22,
                            color: color1
                        }),
                        themeGridRangeIteme2 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 22,
                            end: 24,
                            color: color2
                        }),
                        themeGridRangeIteme3 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 24,
                            end: 26,
                            color: color3
                        }),
                        themeGridRangeIteme4 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 26,
                            end: 28,
                            color: color4
                        }),
                        themeGridRangeIteme5 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 28,
                            end: 30,
                            color: color5
                        }),
                        themeGridRangeIteme6 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 30,
                            end: 32,
                            color: color6
                        }),
                        themeGridRangeIteme7 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 32,
                            end: 34,
                            color: color7
                        }),
                        themeGridRangeIteme8 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 34,
                            end: 36,
                            color: color8
                        }),
                        themeGridRangeIteme9 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 36,
                            end: 38,
                            color: color9
                        }),
                        themeGridRangeIteme10 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 38,
                            end: 40,
                            color: color10
                        }),
                        themeGridRangeIteme11 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 40,
                            end: 42,
                            color: color11
                        }),
                        themeGridRangeIteme12 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 42,
                            end: 44,
                            color: color12
                        }),
                        themeGridRangeIteme13 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 44,
                            end: 46,
                            color: color13
                        }),
                        themeGridRangeIteme14 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 46,
                            end: 48,
                            color: color14
                        }),
                        themeGridRangeIteme15 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 48,
                            end: 50,
                            color: color15
                        }),
                        themeGridRangeIteme16 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 50,
                            end: 52,
                            color: color16
                        }),
                        themeGridRangeIteme17 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 52,
                            end: 54,
                            color: color17
                        }),
                        themeGridRangeIteme18 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 54,
                            end: 56,
                            color: color18
                        }),
                        themeGridRangeIteme19 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 56,
                            end: 58,
                            color: color19
                        }),
                        themeGridRangeIteme20 = new SuperMap.REST.ThemeGridRangeItem({
                            start: 58,
                            end: 60,
                            color: color20
                        }),
                        themeGridRange = new SuperMap.REST.ThemeGridRange({
                            reverseColor: false,
                            rangeMode: SuperMap.REST.RangeMode.EQUALINTERVAL,
                            //栅格分段专题图子项数组
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
                            //制作专题图的数据集数组
                            datasetNames: [InterpolationAnalystEventArgs.result.dataset.split('@')[0]],
                            // 制作专题图的数据集所在的数据源数组
                            dataSourceNames: [InterpolationAnalystEventArgs.result.dataset.split('@')[1]],
                            joinItems: null,
                            //专题图对象列表
                            themes: [themeGridRange],
                            types: ['REGION']
                        });
                //console.log(InterpolationAnalystEventArgs.result.dataset.split('@')[0]);
                var themeService = new SuperMap.REST.ThemeService(url_map, {
                    eventListeners: {
                        "processCompleted": themeCompleted,
                        "processFailed": themeFailed
                    }
                });
                themeService.processAsync(themeParameters);
            }

            function themeCompleted(themeEventArgs) {
                if (themeEventArgs.result.resourceInfo.id) {
                    themeLayer = new SuperMap.Layer.TiledDynamicRESTLayer("Result map of interpolation analysis", url_map, {
                        cacheEnabled: true,
                        transparent: true,
                        layersID: themeEventArgs.result.resourceInfo.id
                    }, {"maxResolution": "auto"});
                    themeLayer.events.on({"layerInitialized": addThemelayer});
                }
            }

            //添加专题图至map
            function addThemelayer() {
                map.addLayer(themeLayer);
            }

            //插值分析失败后调用
            function processFailed(ServiceFailedEventArgs) {
                alert(ServiceFailedEventArgs.error.errorMsg);
            }

            //服务端返回专题图结果失败时调用
            function themeFailed(serviceFailedEventArgs) {
                alert(serviceFailedEventArgs.error.errorMsg);
            }

            function removeInterpolation() {
                if (map.layers.length > 2) {
                    map.removeLayer(themeLayer, true);
                }
            }



        </script>
    </body>
</html>
