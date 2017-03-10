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
        <th>Order Date</th>
        <th>Product ID</th>
        <th>Product Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Discount</th>
        <th>Category</th>
    </tr>
    <c:forEach items="${requestScope.userOrdersList}" var="order">
        <tr>
            <td><c:out value="${order.date}"/></td>
            <c:forEach items="${order.orderedProducts}" var="product">
                <td><c:out value="${product.id}"/></td>
                <td><c:out value="${product.productName}"/></td>
                <td><c:out value="${product.price}"/></td>
                <td><c:out value="${product.quantity}"/></td>
                <td><c:out value="${product.discount}"/></td>
                <td><c:out value="${product.categoryName}"/></td>
            </c:forEach>
        </tr>
    </c:forEach>

</table>

<%@include  file="AdminDown.jsp" %>