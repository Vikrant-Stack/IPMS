

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!--<link href="style/style1.css" type="text/css" rel="stylesheet" media="Screen"/>-->
<!--<link href="style/style.css" type="text/css" rel="stylesheet" media="Screen"/>-->
<!--<link href="style/Table_content.css" type="text/css" rel="stylesheet" media="Screen"/>-->
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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to IPMS</title>
        <script type="text/javascript">
                </script>
    </head>
    <body>





<!--        <table align="center" cellpadding="0" cellspacing="0" class="main">-->
            <tr><td><%@include file="/layout/header.jsp" %></td></tr>
            <tr>
                <c:choose>
                    <c:when test="${designation eq 'ड्राइवर'}">
                        <td><%@include file="/layout/menu2.jsp" %></td>
                    </c:when>
                        <c:when test="${designation eq 'RWA'}">
                        <td><%@include file="/layout/rwa_menu.jsp" %></td>
                    </c:when>
                        <c:when test="${designation eq 'पब्लिक'}">
                        <td><%@include file="/layout/benificiary_menu.jsp" %></td>
                    </c:when>
                        <c:when test="${designation eq 'Clerk'}">
                        <td><%@include file="/layout/clerk_menu.jsp" %></td>
                    </c:when>
                    <c:otherwise>
                        <td><%@include file="/view/login/before_login_home.jsp" %></td>
                    </c:otherwise>
                </c:choose>

            </tr>
<!--            <td>
                <DIV id="body" class="maindiv" align="center" >
                    <table width="100%" align="center">




                    </table>
                    <tr><td><%@include file="/layout/footer.jsp" %></td> </tr>
                </DIV>
            </td>-->

<!--        </table>-->
<div class="text-center">
<img src="images/m2.gif"  >
</div>

 
    </body>
</html>
