<%@page import="java.sql.ResultSet"%>
<%@page import="Database.Database"%>
<%@include file="AdminHead.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    function addProduct()
    {
        var id = document.getElementById("id").value;
        var n = document.getElementById("name").value;
        var d = document.getElementById("description").value;
        var p = document.getElementById("price").value;
        var cus = document.getElementById('cat');
        var custid = cus.options[cus.selectedIndex].value;

        var xmlhttp = new XMLHttpRequest();// to create new object from XMLHTTPReqest 
        xmlhttp.open("GET", "servletUpdateProduct?id=" + id + "&cat=" + custid + "&price=" + p + "&name=" + n + "&desc=" + d, true);
        xmlhttp.send();
        xmlhttp.onreadystatechange = function ()
        {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
            {
                document.getElementById("done").style.visibility = "visible";
<<<<<<< HEAD
=======
                //document.getElementById("form").reset();
                $("#form")[0].reset();
>>>>>>> e020307e88d8acec8ad805ab46fc8a4c76d15110
            }
        }


    }

</script>
<%@include file="AdminUp.jsp" %>
<div class="row clearfix">
    <div class="col-md-12 column">
<<<<<<< HEAD
        <form role="form" enctype="multipart/form-data"  method="post" action="servletAddProduct">
=======
        <form role="form" enctype="multipart/form-data"  method="post" action="servletAddProduct" id="form">
>>>>>>> e020307e88d8acec8ad805ab46fc8a4c76d15110
            <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"  
                               url="jdbc:mysql://localhost/onlineshopping"  
                               user="root"  password=""/>  
            <sql:query dataSource="${db}" var="rs">  
                SELECT product_id,productname,description,quantity,price,discount,categoryname from products;  
            </sql:query>  
            <c:forEach var="product" items="${rs.rows}"> 
                <div class="form-group">
                    <input type="hidden" id="id" value="${product.product_id}">
<<<<<<< HEAD
                    <label for="exampleInputEmail1">Product Name</label><input id="name" name="name" value="${product.productname}" type="text" class="form-control" />
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Product Description </label>
                    <textarea style="height: 100px" name="description" id="description" rows="10" class="form-control" >${product.description}</textarea>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Product Price </label><input type="number" name="price" value="${product.price}" class="form-control" id="price" />
=======
                    <label for="exampleInputEmail1">Product Name</label><input id="name" name="name" value="${product.productname}" type="text" class="form-control" required/>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Product Description </label>
                    <textarea style="height: 100px" name="description" id="description" rows="10" class="form-control" required>${product.description}</textarea>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Product Price </label><input type="number" name="price" value="${product.price}" class="form-control" id="price" required/>
>>>>>>> e020307e88d8acec8ad805ab46fc8a4c76d15110
                </div>


                <div class="form-group">

                    <label for="exampleInputFile">Category</label>
                    <select  class="form-control" id="cat">


                        <option value="mobiles">Mobiles</option>
                        <option value="cameras">Cameras</option>
                        <option value="sound">sound</option>
                        <option value="computers">computers</option>


                    </select>

                </div>

                <button type="button" onclick="addProduct()"  class="btn btn-default">Submit</button>

            </form>



        </c:forEach>  


    </div>
</div>
<<<<<<< HEAD
<div class="form-group">
    <div class="alert alert-success alert-dismissable" id="done">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
=======
<br>
<div class="form-group">
    <div class="alert alert-success alert-dismissable" id="done">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">X</button>
>>>>>>> e020307e88d8acec8ad805ab46fc8a4c76d15110
        <h4>
            done!
        </h4>  your product has been updated . <a href="#" class="alert-link"></a>
    </div>


</div>
<%@include file="AdminDown.jsp" %>
