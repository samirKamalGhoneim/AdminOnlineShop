<%@include file="AdminHead.jsp" %>
<%@include file="AdminUp.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<table class="table table-hover">
    <tr>
        <td>Product</td>
        <td>Quantity</td>
        <td>price</td>
        <td>Category</td>
        <td><a>update</a></td>

    </tr>
    <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"  
                       url="jdbc:mysql://localhost/onlineshopping"  
                       user="root"  password=""/>  
    <sql:query dataSource="${db}" var="rs">  
        SELECT product_id,productname,quantity,price,discount,categoryname from products where productname='<%= request.getParameter("q")%>';  
        <%--<sql:param value="${requestScope.q}" />--%>
    </sql:query>  
    <c:forEach var="product" items="${rs.rows}">  
        <tr id="${product.product_id}">
            <td>${product.productname}</td>
            <td>${product.price}</td>
            <td>${product.categoryname}</td>
            <td>${product.discount}</td>
            <td><a href="updateProduct.jsp?id=${product.product_id}">update</a></td>

        </tr>
    </c:forEach>  
</table>
<%@include file="AdminDown.jsp" %>
