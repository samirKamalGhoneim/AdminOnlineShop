<%-- 
    Document   : ShowAllUsers
    Created on : Mar 5, 2017, 4:16:01 PM
    Author     : Samir
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Database.DataBaseHandler"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.User"%>
<%@page import="dto.CreditCard"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="AdminHead.jsp" %>
<%@include file="AdminUp.jsp" %>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
        <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'>
        <link rel='stylesheet prefetch' href='css/userProfileCss.css'>
    </head>
    <body>
        <div class="container">

            <div class="row">
                <c:forEach items="${requestScope.usersList}" var="user">
                    <div class="col-mid-3 col-sm-3">
                        <div class="card">
                            <div class="avatar">
                                <img src='http://www.henvertech.com/baru/wp-content/uploads/2016/10/User.png' alt="" />
                            </div>
                            <div class="content">
                                <p><c:out value="${user.firstName.toUpperCase()} "/><c:out value="${user.lastName.toUpperCase()}"/></p>
                                <p><a href='/AdminJsp/GetUserDetails?id=<c:out value="${user.email}"/>' class="btn btn-default">View Profile</a></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
    </body>
</html>
<%@include  file="AdminDown.jsp" %>
