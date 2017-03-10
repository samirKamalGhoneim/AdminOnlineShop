<%-- 
    Document   : ShowAllUsers
    Created on : Mar 5, 2017, 4:16:01 PM
    Author     : Samir
--%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="AdminHead.jsp" %>
<%@include file="AdminUp.jsp" %>

<table class="table table-hover">
    <tr>
        <th>User Email</th>
        <th>Orders List</th>
    </tr>
    <c:forEach items="${requestScope.usersHasOrdersList}" var="email">
        <tr>
            <td><c:out value="${email}"/></td>
            <td><a href='/AdminJsp/AllUserOrders?id=<c:out value="${email}"/>'/>show Orders</td>
        </tr>
    </c:forEach>

</table>

<%@include  file="AdminDown.jsp" %>