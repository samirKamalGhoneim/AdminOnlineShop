<%@page import="java.sql.ResultSet"%>
<%@include file="AdminHead.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="AdminUp.jsp" %>
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
        SELECT product_id,productname,quantity,price,discount,categoryname from products;  
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
<br><br>
<div class="form-group">
    <div class="alert alert-success alert-dismissable" id="done">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <h4>
            done!
        </h4>  your product has been deleted . <a href="#" class="alert-link"></a>
    </div>


</div>
<%@include  file="AdminDown.jsp" %>