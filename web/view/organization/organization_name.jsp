<%--
    Document   : workType
    Created on : Feb 16, 2012, 6:18:45 PM
    Author     : Tarun
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta charset="utf-8">
        <title>Data Entry: Organization Name Table</title>
       <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
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

     
      
                 
//              $("#org_name").autocomplete({
//                    
//            source: function (request, response) {
//                debugger;
//                $.ajax({
//                    url: "orgNameCont.do",
//                    dataType: "json",
//                    data: {action1: "searchOrganisationName"},
//                    success: function (data) {
//
//                        console.log(data);
//                        response(data.list);
//                    }, error: function (error) {
//                        console.log(error.responseText);
//                        response(error.responseText);
//                    }
//                });
//            },
//            select: function (events, ui) {
//                console.log(ui);
//                $('#org_name').val(ui.item.label); // display the selected text
//                return false;
//            }
//        });
        
    
          $(function () {  
                 
              $("#organisation_type").autocomplete({
                    
            source: function (request, response) {
               
                $.ajax({
                    url: "orgNameCont.do",
                    dataType: "json",
                    data: {action1: "getOrganisationTypeName"},
                    success: function (data) {

                        console.log(data);
                        response(data.list);
                    }, error: function (error) {
                        console.log(error.responseText);
                        response(error.responseText);
                    }
                });
            },
            select: function (events, ui) {
                console.log(ui);
                $('#organisation_type').val(ui.item.label); // display the selected text
                return false;
            }
        });

        });

       
              $(function () {  
                  
              $("#organisation_sub_type_name").autocomplete({
                    
            source: function (request, response) {
                var org_type=document.getElementById("organisation_type").value;
               
                $.ajax({
                    url: "orgNameCont.do",
                    dataType: "json",
                    data: {action1: "getOrganisationSubTypeName",
                   action2: org_type},
                    success: function (data) {

                        console.log(data);
                        response(data.list);
                    }, error: function (error) {
                        console.log(error.responseText);
                        response(error.responseText);
                    }
                });
            },
            select: function (events, ui) {
                console.log(ui);
                $('#organisation_sub_type_name').val(ui.item.label); // display the selected text
                return false;
            }
  

       });
   });
//       $(function () {  
//                 
//              $("#organisation_name").autocomplete({
//                    
//            source: function (request, response) {
//                debugger;
//                $.ajax({
//                    url: "orgNameCont.do",
//                    dataType: "json",
//                    data: {action1: "getOrganisationName"},
//                    success: function (data) {
//
//                        console.log(data);
//                        response(data.list);
//                    }, error: function (error) {
//                        console.log(error.responseText);
//                        response(error.responseText);
//                    }
//                });
//            },
//            select: function (events, ui) {
//                console.log(ui);
//                $('#organisation_name').val(ui.item.label); // display the selected text
//                return false;
//            }
//        });

//        $('#btnDialog').click(function ()
//{
//    $(this).speedoPopup(
//    {
//        width:550,
//        height:265,
//        useFrame: TRUE,
//        href: '#divContentToPopup'
//    });
//});






            function makeEditable(id) {
                   debugger;
                   document.getElementById("organisation_type_name").disabled = false;
                   document.getElementById("organisation_sub_type_name").disabled = false;
                 document.getElementById("organisation_name").disabled = false;
                document.getElementById("description").disabled = false;
                if(id === 'new') {
                    // document.getElementById("message").innerHTML = "";      // Remove message
                    $("#message").html("");
                    document.getElementById("organisation_id").value = "";
                    document.getElementById("organisation_sub_type_id").value = "";
                    document.getElementById("organisation_type_id").value = "";
                    document.getElementById("edit").disabled = true;
                    document.getElementById("delete").disabled = true;
                    document.getElementById("save_As").disabled = true;
                    setDefaultColor(document.getElementById("noOfRowsTraversed").value, 4);
                    document.getElementById("organisation_name").focus();
                     document.getElementById("organisation_type_name").focus();
                   document.getElementById("organisation_sub_type_name").focus();
                }
                if(id == 'edit') {
                    document.getElementById("save_As").disabled = false;
                    document.getElementById("delete").disabled = false;
                }
                document.getElementById("save").disabled = false;
            }
            function setDefaultColor(noOfRowsTraversed, noOfColumns) {
                for(var i = 0; i < noOfRowsTraversed; i++) {
                    for(var j = 1; j <= noOfColumns; j++) {
                        document.getElementById("t1c" + (i * noOfColumns + j)).bgColor = "";     // set the default color.
                    }
                }
            }
            function fillColumns(id) {
                debugger;
                var noOfRowsTraversed = document.getElementById("noOfRowsTraversed").value;
        var noOfColumns = 4;
        var columnId = id;                              <%-- holds the id of the column being clicked, excluding the prefix t1c e.g. t1c3 (column 3 of table 1). --%>
        columnId = columnId.substring(3, id.length);    <%-- for e.g. suppose id is t1c3 we want characters after t1c i.e beginIndex = 3. --%>
        var lowerLimit, higherLimit;
        for(var i = 0; i < noOfRowsTraversed; i++)
        {
            lowerLimit = i * noOfColumns + 1;       // e.g. 11 = (1 * 10 + 1)
            higherLimit = (i + 1) * noOfColumns;    // e.g. 20 = ((1 + 1) * 10)

            if((columnId>= lowerLimit) && (columnId <= higherLimit)) break;
        }

              // set default color of rows (i.e. of multiple coloumns).
        var t1id = "t1c";       // particular column id of table 1 e.g. t1c3.

     document.getElementById("organisation_type_id").value=document.getElementById(t1id + (lowerLimit + 0)).innerHTML;
     
     document.getElementById("organisation_type").value=document.getElementById(t1id + (lowerLimit + 2)).innerHTML;
     document.getElementById("organisation_sub_type_name").value=document.getElementById(t1id + (lowerLimit + 3)).innerHTML;
     document.getElementById("organisation_name").value=document.getElementById(t1id + (lowerLimit + 4)).innerHTML;
     document.getElementById("description").value=document.getElementById(t1id + (lowerLimit + 5)).innerHTML;
    


        $("#message").html("");
            }
            
            function setStatus(id) {
                if(id === 'save') {
                    document.getElementById("clickedButton").value = "Save";
                }
                else if(id === 'save_As'){
                    document.getElementById("clickedButton").value = "Save AS New";
                } else if(id === 'search_org'){
                    var org_name=document.getElementById("org_name").value;
                    document.getElementById("org_name1").value =  org_name;
                    document.getElementById("org_name2").value =  org_name;
                    document.getElementById("clickedButton").value = "SEARCH";
                }
                else if(id === 'clear_org'){
                    document.getElementById("clickedButton").value = " ";
                    $("#org_msg").html("");
                    document.getElementById("org_name").value =" ";
                    document.getElementById("org_name1").value =  " ";
                    document.getElementById("org_name2").value =  " ";
                }
                else {
                    document.getElementById("clickedButton").value = "Delete";;
                }
            }
            function myLeftTrim(str) {                                                                                             
                var beginIndex = 0;
                for(var i = 0; i < str.length; i++) {
                    if(str.charAt(i) === ' ')
                        beginIndex++;
                    else break;
                }
                return str.substring(beginIndex, str.length);
            }
            function verify() {
                var result;
                if(document.getElementById("clickedButton").value === 'Save' || document.getElementById("clickedButton").value === 'Save AS New') {
                    var organisation_name = document.getElementById("organisation_name").value;
                    if(myLeftTrim(organisation_name).length === 0) {
                        // document.getElementById("message").innerHTML = "<td colspan='5' bgcolor='coral'><b>Organisation Name is required...</b></td>";
                        $("#message").html("<td colspan='2' bgcolor='coral'><b>Organisation Name is required...</b></td>");
                        document.getElementById("organisation_name").focus();
                        return false; // code to stop from submitting the form2.
                    }
                    if(result === false) {
                        // if result has value false do nothing, so result will remain contain value false.
                    } else {
                        result = true;
                    }
                    if(document.getElementById("clickedButton").value === 'Save AS New'){
                        result = confirm("Are you sure you want to save it as New record?")
                        return result;
                    }
                } else {
                    result = confirm("Are you sure you want to delete this record?");
                }
                return result;
            }
            function verifySearch(){
                var result;
                if(document.getElementById("clickedButton").value === 'SEARCH') {
                    var org_name = document.getElementById("org_name").value;
                    if(myLeftTrim(org_name).length === 0) {
                        document.getElementById("org_msg").innerHTML = "<b>Organization Name is required...</b>";
                        document.getElementById("org_name").focus();
                        return false; // code to stop from submitting the form2.
                    }
                }
            }

            function displayOrgnList(id){               
                var queryString;
                if(id==='viewPdf')
                queryString = "requester=PRINT";
            else
                queryString = "requester=PRINTXls";
                var url = "orgNameCont.do?"+queryString;
                popupwin = openPopUp(url, "Organisation", 600, 900);
            }

            function openPopUp(url, window_name, popup_height, popup_width) {
                var popup_top_pos = (screen.availHeight / 2) - (popup_height / 2);
                var popup_left_pos = (screen.availWidth / 2) - (popup_width / 2);
                var window_features = "left=" + popup_left_pos + ", top=" + popup_top_pos + ", width=" + popup_width + ", height=" + popup_height + ", resizable=no, scrollbars=yes, status=no, dialog=yes, dependent=yes";
                return window.open(url, window_name, window_features);
            }
            if (!document.all) {
                document.captureEvents (Event.CLICK);
            }
            document.onclick = function() {
                if (popupwin !== null && !popupwin.closed) {
                    popupwin.focus();
                }
            }

//             function changeClass(){
//                        var language=document.getElementById("language").value;
//                        if(language=='English'){
//                            $( "#organisation_name" ).addClass('input').removeClass('new_input');
//                            $("#description" ).addClass('input').removeClass('new_input');
//                             $("#org_name" ).addClass('input').removeClass('new_input');
//                              }
//                        else{
//                            $( "#organisation_name" ).addClass('new_input').removeClass('input');
//                            $("#description" ).addClass('new_input').removeClass('input');
//                                $("#org_name" ).addClass('new_input').removeClass('input');
//                            }
//                    }
//                     function changeSearchClass(){
//                        var language=document.getElementById("language_type").value;
//                        if(language=='English'){
//                            $( "#org_name" ).addClass('input').removeClass('new_input');
//                              }
//                        else{
//                            $( "#org_name" ).addClass('new_input').removeClass('input');
//                                            }
//                    }
        </script>
                
               
    </head>
    <body>
                <div class="container-fluid">
        <table align="center" cellpadding="0" cellspacing="0"  class="main">            <!--DWLayoutDefaultTable-->
            <%@include file="/layout/header.jsp" %>
            <tr>
                <%@include file="/layout/menu2.jsp" %> 
            </tr>
            <tr>
                <%--   <td width="50" height="600" valign="top"><%@include file="/view/layout/Leftmenu.jsp" %></td></tr> --%>
                <td>
                   
                       
<!--                            <tr><td>
                                    <table>
                                        <tr>
                                            <td align="center" class="header_table" width="800"></td>
                                            <td>
                                                <%@include file="/layout/org_menu.jsp" %>
                                            </td>
                                        </tr>
                                    </table>
                                </td></tr>-->
                                 <div class="container">
                          
                                    <form name="form1" method="POST" action="orgNameCont.do" onsubmit="return verifySearch();">
                                        <table  class="table table-bordered table-striped" width="700">
                                            <tr>
                                              
                                                <th>Organization</th>
                                                <td><input type="text" class="form-control" id="org_name" name="org_name" value="${org_name}" size="35"></td>
                                                <td><input type="submit" class="btn btn-primary" id="search_org" name="search_org" value="SEARCH" onclick="setStatus(id)"></td>
                                                <td><input type="submit" class="btn btn-success" id="clear_org" name="clear_org" value="CLEAR" onclick="setStatus(id)"></td>
                                                <td><input type="button" class="btn btn-danger" id="viewPdf" name="viewPdf" value="PDF" onclick="displayOrgnList(id)"></td>
                                                <td><input type="button" class="btn btn-info" id="viewXls" name="viewXls" value="Excel" onclick="displayOrgnList(id)"></td>
                                                <td><label id="org_msg">  </label></td>
                                                
                                            </tr>
                                        </table>
                                    </form>
                                  </div>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <form name="form1" method="POST" action="orgNameCont.do">
                                        <DIV class="container">
                                            <table border="1" id="table1" align="center"  class="table table-bordered table-striped" width="650">
                                                <tr>
                                                    <th class="heading">S.No.</th>
                                                    <th class="heading">Organisation Type</th>
                                                    <th class="heading">Organisation Sub Type</th>
                                                    <th class="heading">Organisation Name</th>
                                                    <th class="heading">Description</th>
                                                </tr>
                                                <c:forEach var="orgName" items="${requestScope['orgNameList']}" varStatus="loopCounter">
                                                    <tr  class="${loopCounter.index % 2 == 0 ? 'even': 'odd'}" >
                                                        <td id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)" align="center">
                                                            <input type="hidden" id="organisation_id${loopCounter.count}" value="${orgName.organisation_id}">${lowerLimit - noOfRowsTraversed + loopCounter.count}
                                                        </td>
                                                        <td id="t1c${IDGenerator.uniqueID}" class="new_input" onclick="fillColumns(id)" >${orgName.organisation_type}</td>
                                                         <td id="t1c${IDGenerator.uniqueID}" class="new_input" onclick="fillColumns(id)" >${orgName.organisation_sub_type_name}</td>
                                                        <td id="t1c${IDGenerator.uniqueID}" class="new_input" onclick="fillColumns(id)" >${orgName.organisation_name}</td>
                                                        <td id="t1c${IDGenerator.uniqueID}" class="new_input" onclick="fillColumns(id)" >${orgName.description}</td>
                                                        </tr>
                                                </c:forEach>
                                                <tr>
                                                    <td align="center" colspan="5">
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
                                                                <input class="btn btn-info" type='submit' name='buttonAction' value='Next' disabled>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input class="btn btn-info" type='submit' name='buttonAction' value='Next'>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when test="${showLast == 'false'}">
                                                                <input class="btn btn-primary" type='submit' name='buttonAction' value='Last' disabled>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input class="btn btn-primary" type='submit' name='buttonAction' value='Last'>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                                <%-- These hidden fields "lowerLimit", and "noOfRowsTraversed" belong to form1 of table1. --%>
                                                <input type="hidden" name="lowerLimit" value="${lowerLimit}">
                                                <input type="hidden" id="noOfRowsTraversed" name="noOfRowsTraversed" value="${noOfRowsTraversed}">
                                                <input type="hidden" id="org_name2" name="org_name" value="${org_name}">
                                            </table></DIV>
                                    </form>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <form name="form2" method="POST" action="orgNameCont.do" onsubmit="return verify()">
                                        <table class="table table-bordered table-striped" border="0" id="table2" align="center" width="650">
                                            <tr id="message">
                                                <c:if test="${not empty message}">
                                                    <td colspan="2" bgcolor="${msgBgColor}"><b>Result: ${message}</b></td>
                                                </c:if>
                                            </tr>
                                            
                                            
                                                 <tr>

                                                <th class="heading1">Organisation Type<span class="required_field_class">*</span></th><td>
                                                    <input class="form-control" type="hidden" id="organisation_type_id" name="organisation_type_id" value="" >
                                                    <input class="form-control" type="text" id="organisation_type" name="organisation_type" size="60" value="" >
                                                </td>
                                            </tr>
                                            
                                            <tr>

                                                <th class="heading1">Organisation Sub Type<span class="required_field_class">*</span></th><td>
                                                    <input class="form-control" type="hidden" id="organisation_sub_type_id" name="organisation_sub_type_id" value="" >
                                                    <input class="form-control" type="text" id="organisation_sub_type_name" name="organisation_sub_type_name" size="60" value="" >
                                                </td>
                                            </tr>
                                            
                                            <tr>

                                                <th class="heading1">Organisation Name<span class="required_field_class">*</span></th><td>
                                                    <input class="form-control" type="hidden" id="organisation_id" name="organisation_id" value="" >
                                                    <input class="form-control" type="text" id="organisation_name" name="organisation_name" size="60" value="" >
                                                </td>
                                            </tr>
                                            
                                            
                                            <tr>

                                                <th class="heading1">Description</th><td><input class="form-control" type="text" id="description" name="description" size="60" value="" ></td>
                                            </tr>
                                            <tr>
                                                <td align='center' colspan="2" >
                                                    <input type="button" class="btn btn-primary" name="edit" id="edit" value="Edit" onclick="makeEditable(id)" >
                                                    <input type="submit" class="btn btn-success" name="task" id="save" value="Save" onclick="setStatus(id)" >
                                                    <input type="submit" class="btn btn-info" name="task" id="save_As" value="Save AS New" onclick="setStatus(id)" >
                                                    <input type="reset" class="btn btn-danger" name="new" id="new" value="New" onclick="makeEditable(id)" >
                                                    <input type="submit" class="btn btn-primary" name="task" id="delete" value="Delete" onclick="setStatus(id)" >
                                                </td>
                                            </tr>

                                            <%-- These hidden fields "lowerLimit", "noOfRowsTraversed", and "clickedButton" belong to form2 of table2. --%>
                                            <input type="hidden" name="lowerLimit" value="${lowerLimit}">
                                            <input type="hidden" name="noOfRowsTraversed" value="${noOfRowsTraversed}">
                                            <input type="hidden" id="clickedButton" value="">
                                            <input type="hidden" id="org_name1" name="org_name" value="${org_name}">
                                        </table>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </DIV>
                
           <%@include file="/layout/footer.jsp" %>
        
    </body>
</html>
