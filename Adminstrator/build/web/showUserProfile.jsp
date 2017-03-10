<%@include  file="AdminDown.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="AdminHead.jsp" %>

<%@include file="AdminUp.jsp" %>
<table class="table table-hover table-bordered">

    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Address</th>
        <th>Credit Card Number</th>
    </tr>
    <c:set var="user" scope="request" value="${requestScope.userDetailsObject}"/>
    <tr>
    <tr>
        <td><c:out value="${user.firstName} "/><c:out value="${user.lastName}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td><c:out value="${user.phone}"/></td>
        <td><c:out value="${user.type}"/></td>
        <td><c:out value="${user.creditCard.creditCardNumber}"/></td>
    </tr>
</tr>
</table>
<form action="GetAllUsers" method="POST">
    <input class="btn btn-primary" type="submit" value="Back"/>
</form>
<%@include  file="AdminDown.jsp" %>