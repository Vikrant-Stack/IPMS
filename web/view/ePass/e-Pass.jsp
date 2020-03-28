<%-- 
    Document   : e-pass_type
    Created on : March 26, 2020, 7:46:00 PM
    Author     : Vikrant_Saini
--%>

<%@page import="com.healthDepartment.dbCon.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.apogee.general.QueryUtil"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Data Entry: E-Pass </title>
        <meta  name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' />
        <meta charset="utf-8">
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Expires" content="-1" />
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css"  rel="stylesheet" type="text/css">        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>


        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="time/bootstrap-datetimepicker.min.css">
        <script  type="text/javascript" src="time/moment.min.js"></script>
        <script  type="text/javascript" src="time/bootstrap-datetimepicker.min.js"></script>


        <script type="text/javascript" language="javascript">
            function setButton(id)
            {
                if (id == "delete")
                    document.getElementById('buttonClick').value = "delete";
                if (id == "saveAsNew")
                    document.getElementById('buttonClick').value = "saveAsNew";
                if (id == "save")
                    document.getElementById('buttonClick').value = "save";
            }


            function makeEditable(id)
            {//alert("idd -"+id);
                //debugger;

                if (id == "new")
                {
                    debugger;
                    $("#work_code").attr("disabled", false);
                    $("#person_id").attr("disabled", false);
                    $("#location_id").attr("disabled", false);
                    $("#valid_from").attr("disabled", false);
                    $("#valid_to").attr("disabled", false);
                    $("#remark").attr("disabled", false);
                    $("#saveAsNew").attr("disabled", false);
                    document.getElementById('message').innerHTML = "";

                }
                if (id == "edit")
                {
//                    document.getElementById('pin_code').disabled = false;
//                    document.getElementById('std_code').disabled = false;
                    document.getElementById('workName').disabled = false;
                    document.getElementById('org_code').disabled = false;
                    document.getElementById('remark').disabled = false;
                    document.getElementById('save').disabled = false;
                    document.getElementById('saveAsNew').disabled = false;
                    document.getElementById('delete').disabled = false;
                }

            }
            function varification()
            {
                var click = document.getElementById('buttonClick').value;
                if (click == "delete")
                {
                    var con = confirm("Do you want to delete this record")
                    return con;
                }
                if (click == "saveAsNew")
                {
                    var con = confirm("Do you want to save as this this record")
                    return con;
                }
                if ($.trim(document.getElementById('divisionName').value) == "")
                {
                    alert("Please enter division name")
                    document.getElementById('divisionName').value = "";
                    document.getElementById('divisionName').focus()
                    return false;
                }
                if ($.trim(document.getElementById('districtName').value) == "")
                {
                    alert("Please enter district name")
                    document.getElementById('districtName').value = "";
                    document.getElementById('districtName').focus()
                    return false;
                }
                if ($.trim(document.getElementById('cityName').value) == "")
                {
                    alert("Please enter city name")
                    document.getElementById('cityName').value = "";
                    document.getElementById('cityName').focus()
                    return false;
                }
//                if($.trim(document.getElementById('cityDescription').value)=="")
//                {
//                    alert("Please enter city description")
//                    document.getElementById('cityDescription').value="";
//                    document.getElementById('cityDescription').focus()
//                    return false;
//                }
                if (document.getElementById('cityId').value == "")
                {
                    addRow();
                    return false;
                }
                if (click == "save")
                {
                    var con = confirm("Do you want to update this record")
                    return con;
                }
                return true;
            }
            function addRow()
            {
                var table = document.getElementById('insetTable');
                var rowCount = table.rows.length;
                var row = table.insertRow(rowCount);

                var cell1 = row.insertCell(0);
                cell1.innerHTML = rowCount;
                var element1 = document.createElement("input");
                element1.name = "city_id";
                element1.id = "city_id" + rowCount;
                element1.type = "hidden";
                element1.value = 1;
                element1.size = 1;
                cell1.appendChild(element1);

                var element2 = document.createElement("input");
                element2.name = "cityCheckbox";
                element2.id = "cityCheckbox" + rowCount;
                element2.type = "checkbox";
                element2.checked = true;
                element2.setAttribute("onclick", 'singleCheck(' + rowCount + ')');
                cell1.appendChild(element2);
                ////////////////////////////////////
                var cell2 = row.insertCell(1);
                var element3 = document.createElement("input");
                element3.name = "divisionName";
                element3.id = "divisionName" + rowCount;
                element3.value = document.getElementById('divisionName').value;
                element3.size = 30;
                element3.className = "new_input";
                cell2.appendChild(element3);

                var cell3 = row.insertCell(2);
                var element4 = document.createElement("input");
                element4.name = "districtName";
                element4.id = "districtName" + rowCount;
                element4.value = document.getElementById('districtName').value;
                element4.size = 30;
                element4.className = "new_input";
                cell3.appendChild(element4);

                ///////////////////////////////////////////
                var cell4 = row.insertCell(3);
                var element5 = document.createElement("input");
                element5.name = "cityName";
                element5.id = "cityName" + rowCount;
                element5.value = document.getElementById('cityName').value;
                element5.size = 30;
                element5.className = "new_input";
                cell4.appendChild(element5);

                var cell5 = row.insertCell(4);
                var element6 = document.createElement("input");
                element6.name = "cityDescription";
                element6.id = "cityDescription" + rowCount;
                element6.value = document.getElementById('cityDescription').value;
                element6.size = 30;
                element6.className = "new_input";
                cell5.appendChild(element6);
                var height = (rowCount * 40) + 60;
                document.getElementById('autoCreatedTableDiv').style.visibility = "visible";
                document.getElementById("autoCreatedTableDiv").style.height = height + 'px';
            }
            function singleCheck(id)
            {
                if (document.getElementById('cityCheckbox' + id).checked == true)
                    document.getElementById('city_id' + id).value = 1;
                else
                    document.getElementById('city_id' + id).value = 0;
            }
            function deleteTable()
            {
                var table = document.getElementById('insetTable');
                var rowCount = table.rows.length;
                for (var i = 0; i < rowCount - 1; i++)
                    table.deleteRow(1);
                document.getElementById('autoCreatedTableDiv').style.visibility = "hidden";

            }
            function check()
            {
                var value = document.getElementById('uncheckAll').value;
                var length = document.getElementsByName('cityCheckbox').length;
                if (value == "UncheckAll")
                {
                    for (var checkbox = 0; checkbox < length; checkbox++)
                    {
                        document.getElementsByName('cityCheckbox')[checkbox].checked = false;
                        document.getElementsByName('city_id')[checkbox].value = 0;
                    }
                    document.getElementById('uncheckAll').value = "CheckAll";
                } else
                {
                    for (var checkbox = 0; checkbox < length; checkbox++)
                    {
                        document.getElementsByName('cityCheckbox')[checkbox].checked = true;
                        document.getElementsByName('city_id')[checkbox].value = 1;
                    }
                    document.getElementById('uncheckAll').value = "UncheckAll";
                }
            }
            function findfill(id)
            {
                debugger;
                // setDefault();
                //  document.getElementById(id).bgColor = "#d0dafd";
                document.getElementById('edit').disabled = false;
                document.getElementById('save').disabled = true;
                document.getElementById('cityId').value = document.getElementById(id + '1').innerHTML;
                document.getElementById('cityName').value = document.getElementById(id + '3').innerHTML;
                document.getElementById('pin_code').value = document.getElementById(id + '4').innerHTML;
                document.getElementById('std_code').value = document.getElementById(id + '5').innerHTML;
                document.getElementById('cityDescription').value = document.getElementById(id + '6').innerHTML;
                document.getElementById('tehsil').value = document.getElementById(id + '7').innerHTML;
                document.getElementById('message').innerHTML = "";
            }
            function setDefault()
            {
                for (var i = 1; i <= document.getElementById('noOfRowsTraversed').value; i++)
                    document.getElementById(i).bgColor = "";
            }
            function displayMapList(id)
            {
                var searchCity = document.getElementById('searchCity').value;
                var action;
                if (id == 'viewPdf')
                    action = "task=generateMapReport&searchCity=" + searchCity;
                else
                    action = "task=generateMapXlsReport&searchCity=" + searchCity;
                var url = "cityTypeCont?" + action;
                popup = popupWindow(url, "City_View_Report");
            }
            function popupWindow(url, windowName)
            {
                var windowfeatures = "left=50, top=50, width=1000, height=500, resizable=no, scrollbars=yes, location=0, menubar=no, status=no, dependent=yes";
                return window.open(url, windowName, windowfeatures)
            }

        </script>
        <style>
            .container-fluid
            {
                max-width: 100%;

            }

            .form {
                max-width: 100%;
            }

        </style>
    </head>
    <body>

        <%
            ServletContext ctx = getServletContext();
            QueryUtil qt = new QueryUtil();
            qt.setConnection((Connection) DBConnection.getConnectionForUtf(ctx));

            //String model_idDD="",tank_idDD="";               
%>
        <!--DWLayoutDefaultTable-->

        <div class="container-fluid">
            <table align="center" cellpadding="0" cellspacing="0"  class="main">            <!--DWLayoutDefaultTable-->
                <%@include file="/layout/header.jsp" %>
                <tr>
                    <%@include file="/layout/menu2.jsp" %> 
                </tr>
                <tr>
                    <%--   <td width="50" height="600" valign="top"><%@include file="/view/layout/Leftmenu.jsp" %></td></tr> --%>
                    <!--                    <td>
                    <%@include file="/layout/org_menu.jsp" %>
                </td>-->
                </tr>
            </table>

            <div class="container">
                <form action="ePassCont" method="post" class="form-group container-fluid">
                    <table class="table table-bordered table-hover  table-striped">
                        <tr>       <th>Enter E-Pass id:</th>
                            <td>   <input type="text" class="form-control"  name="searchEPass" size="20" id="searchEPass" value="${searchWork}"/></td>
                            <td>  <input class="btn btn-primary" type="submit" name="searchWorkModel" value="Search"/></td>
                            <td>  <input class="btn btn-success" type="submit" name="task" value="Search All Records"/></td>
                            <td>  <input class="btn btn-danger" type="button" id="viewPdf" name="viewPdf" value="PDF" onclick="displayMapList(id);"/></td>
                            <td>  <input class="btn btn-info" type="button" id="viewXls" name="viewXls" value="Excel" onclick="displayMapList(id);"/></td>
                        </tr>      
                    </table>
                </form>
            </div>

            <div class="container-fluid" >
                <form action="ePassCont" method="post" class="form-group container-fluid">
<!--                        <input type="hidden" name="searchCity" id="searchCity" value="${searchCity}"/>-->
                    <table class="table table-bordered table-hover  table-striped ">
                        <tr>
                            <th class="heading">S.No.</th>
                            <th class="heading">e-Pass ID</th>
                            <th class="heading">Location Id</th>
                            <th class="heading">Key Person Name</th>
                            <th class="heading">Valid From</th>
                            <th class="heading">Valid To</th>
                            <th class="heading">Work Type Id</th>
                            <th class="heading">QR code</th>
                            <th class="heading">Remark</th>
                            <!--                                <th class="heading">Tehsil Name</th>-->

                        </tr>
                        <c:forEach var="epassBean" items="${requestScope['ePassList']}" varStatus="loopcounter">
                            <tr id="${loopcounter.count}"onclick="findfill(id)">
                                <td id="${loopcounter.count}1" style="display: none">${epassBean.ePassId}</td>
                                <td id="${loopcounter.count}2" align="center">${lowerLimit - noOfRowsTraversed + loopcounter.count}</td>
                                <td class="new_input city_name" id="${loopcounter.count}3">${epassBean.ePassId}</td>
                                <td  id="${loopcounter.count}4">${epassBean.locationId}</td>
                                <td  id="${loopcounter.count}5">${epassBean.keyPersonId}</td>
                                <td  id="${loopcounter.count}5">${epassBean.validFrom}</td>
                                <td  id="${loopcounter.count}5">${epassBean.validTo}</td>
                                <td  id="${loopcounter.count}5">${epassBean.workCode}</td>
                                <td  id="${loopcounter.count}5">${epassBean.qrCode}</td>
                                <td  id="${loopcounter.count}5">${epassBean.remark}</td>

<!--                                    <td class="new_input" id="${loopcounter.count}7">${cityBean.tehsilName}</td>-->
                            </tr>
                        </c:forEach>
                        <tr>
                            <td align='center' colspan="9">
                                <c:choose>
                                    <c:when test="${showFirst eq 'false'}">
                                        <input class="btn btn-primary" type='submit' name='buttonAction' value='First' disabled>
                                    </c:when>
                                    <c:otherwise>
                                        <input class="btn btn-primary" type='submit' name='buttonAction' value='First'>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${showPrevious == 'false'}">
                                        <input class="btn btn-success" type='submit' name='buttonAction' value='Previous' disabled>
                                    </c:when>
                                    <c:otherwise>
                                        <input class="btn btn-success" type='submit' name='buttonAction' value='Previous'>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${showNext eq 'false'}">
                                        <input class="btn btn-warning" type='submit' name='buttonAction' value='Next' disabled>
                                    </c:when>
                                    <c:otherwise>
                                        <input class="btn btn-warning" type='submit' name='buttonAction' value='Next'>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${showLast == 'false'}">
                                        <input class="btn btn-info" type='submit' name='buttonAction' value='Last' disabled>
                                    </c:when>
                                    <c:otherwise>
                                        <input class="btn btn-info" type='submit' name='buttonAction' value='Last'>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <%-- These hidden fields "lowerLimit", and "noOfRowsTraversed" belong to form1 of table1. --%>
                        <input type="hidden"  name="lowerLimit" value="${lowerLimit}">
                        <input type="hidden"  name="noOfRowsTraversed" value="${noOfRowsTraversed}">
                        <input  type="hidden" name="searchtypeofoccupation" value="${searchtypeofoccupation}">
                    </table>
                </form>

            </div>

            <div class="col-12 col-sm pr-sm-0">
                <form action="ePassCont" method="post"  name="form2" onsubmit="return varification()" class="form-group container-fluid">
                    <table class="table table-bordered table-hover  table-striped">
                        <c:if test="${not empty message}">
                            <tr id="message">
                                <td colspan="2" bgcolor="${messageBGColor}"><b>Result: ${message}</b></td>
                            </tr>
                        </c:if>
                        <tr>
                            <td ><input style="display: none;" type="text" class="form-control" id="cityId" name="cityId" value="" readonly/><td>
                        </tr>
                        <!--                        <tr>
                                                    <th >Work Type</th>
                                                    <td><input class="form-control" type="text"  id="WorkName" name="WorkName" size="40" disabled/></td>
                                                </tr>-->



                        <tr>
                            <th>Work Type</th>
                            <td><select class="form-control" id="work_code" name="work_code" disabled>
                                    <option value="">Select Work Type</option>
                                    <%=QueryUtil.getComboOption("work_type", "work_type_id", "work_code", "", "1=1", "")%>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <th>Key Person Name</th>
                            <td><select class="form-control" id="person_id" name="person_id" disabled>
                                    <option value="">Select Key Person</option>
                                    <%=QueryUtil.getComboOption("key_person", "key_person_id", "key_person_name", "", "1=1", "")%>
                                </select>
                            </td>
                        </tr>


                        <tr>
                            <th class="">Location</th>
                            <td><input class="form-control" type="text"  id="location_id" name="location_id" size="40" disabled/></td>
                        </tr>

                        <tr>
                            <th class="">Valid From</th>
                            <td><input class="form-control" type="text"  id="valid_from" name="valid_from" size="40" disabled/></td>
                        </tr>

                        <tr>
                            <th class="">Valid To</th>
                            <td><input class="form-control" type="text"  id="valid_to" name="valid_to" size="40" disabled/></td>
                        </tr>



                        <tr>
                            <th class="">Remark</th>
                            <td><input class="form-control" type="text"  id="remark" name="remark" size="40" disabled/></td>
                        </tr>
                        <!--                            <tr>
                                                        <th class="heading1">City Description</th>
                                                        <td><input class="form-control" type="text"  name="cityDescription" id="cityDescription" value="" size="40" disabled></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="heading1">Tehsil Name</th>
                                                        <td><input class="form-control" type="text"  name="tehsil" id="tehsil" value="" size="40" disabled></td>
                                                    </tr>-->
                        <input type="hidden" name="buttonClick" id="buttonClick" value=""/>
                        <tr>
                            <td align='center' colspan="4">
                                <input type="button" class="btn btn-primary" name="edit" id="edit" value="Edit" onclick="makeEditable(id)" disabled>
                                <input type="submit" class="btn btn-success" name="task" id="save" value="Save" onclick="setStatus(id)" disabled>
                                <input type="submit" class="btn btn-info" name="task" id="saveAsNew" value="Save AS New" onclick="setStatus(id)" disabled>
                                <input type="reset" class=" btn btn-warning" name="new" id="new" value="New" onclick="makeEditable(id)" >
                                <input type="submit" class="btn btn-success" name="task" id="delete" value="Delete" onclick="setStatus(id)" disabled>
                            </td>
                        </tr>
<!--                        
                         <input type='text' class="form-control" />
                        
                <div class='input-group date' id='datetimepicker1'>
                    <input type='text' class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div> 
                         <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script> 
                         <script type="text/javascript">
            $(function () {
                $('#datetimepicker1').datetimepicker();
            });
        </script>-->

                        <%-- These hidden fields "lowerLimit", "noOfRowsTraversed", and "clickedButton" belong to form2 of table2. --%>
                        <input type="hidden" name="lowerLimit" value="${lowerLimit}">
                        <input type="hidden" name="noOfRowsTraversed" value="${noOfRowsTraversed}">
                        <input type="hidden" id="clickedButton" value="">

                    </table>
                </form>
            </div>


            <%@include file="/layout/footer.jsp" %>
        </table>


</body>
</html>