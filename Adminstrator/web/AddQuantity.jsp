<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dto.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Database.DataBaseHandler"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="AdminHead.jsp" %>

<script>
    function addQuantity()
    {
        //    var n = document.getElementById("name").value;
        var q = document.getElementById("quantity").value;
        var cus = document.getElementById('name');
        var custid = cus.options[cus.selectedIndex].value;
        var o = document.getElementById("old" + custid).getAttribute("class");
        q = parseInt(q) + parseInt(o);

        var xmlhttp = new XMLHttpRequest();// to create new object from XMLHTTPReqest 
        xmlhttp.open("GET", "servletAddQunatity?id=" + custid + "&qunt=" + q, true);
        xmlhttp.send();
        xmlhttp.onreadystatechange = function ()
        {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200)
            {
                document.getElementById("done").style.visibility = "visible";
            }
        }


    }

</script>

<%@include file="AdminUp.jsp" %>
<form role="form"   method="post" >
    <div class="form-group">

        <label for="exampleInputFile">Product</label>
        <select  class="form-control" id="name">
            <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"  
                               url="jdbc:mysql://localhost/onlineshopping"  
                               user="root"  password=""/>  
            <sql:query dataSource="${db}" var="rs">  
                SELECT * from products;  
            </sql:query>  
            <c:forEach var="product" items="${rs.rows}">  
                <option id="old${product.product_id}" class="${product.quantity}" value="${product.product_id}">
                    ${product.productname}
                </option>

            </c:forEach>  

        </select>

    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Product Quantity </label>
        <input type="text" id="quantity" class="form-control"  />
    </div>
    <button type="button" onclick="addQuantity()"  class="btn btn-default">Submit</button>
</form> 
<br><br>
<div class="form-group">
    <div class="alert alert-success alert-dismissable" id="done">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <h4>
            done!
        </h4>  your product has been updated . <a href="#" class="alert-link"></a>
    </div>


</div>
<%@include file="AdminDown.jsp" %>                    