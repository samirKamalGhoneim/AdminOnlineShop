<%@page import="java.sql.ResultSet"%>
<%@page import="Database.Database"%>
<%@include file="AdminHead.jsp" %>
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
            }
        }


    }

</script>
<%@include file="AdminUp.jsp" %>
<div class="row clearfix">
    <div class="col-md-12 column">
        <form role="form" enctype="multipart/form-data"  method="post" action="servletAddProduct">
            <%
int r_id=Integer.parseInt(request.getParameter("id"));
Database logic = new Database();
ResultSet rs = logic.GetProductById(r_id);
while (rs.next()) {
String id= rs.getString(1);
            %>
            <div class="form-group">
                <input type="hidden" id="id" value="<%= id %>">
                <label for="exampleInputEmail1">Product Name</label><input id="name" name="name" value="<%= rs.getString(2) %>" type="text" class="form-control" />
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Product Description </label>
                <textarea name="description" id="description" rows="10" class="form-control" ><%= rs.getString(3) %></textarea>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Product Price </label><input type="text" name="price" value="<%= rs.getString(7) %>" class="form-control" id="price" />
            </div>

            <% }%>
            <div class="form-group">

                <label for="exampleInputFile">Category</label>
                <select  class="form-control" id="cat">

                    <%

                       rs = logic.ShowAllCategory();
                        while (rs.next()) {
                    %>
                    <option value="<%= rs.getString(1)%>"><%= rs.getString(2)%></option>
                    <% }%>

                </select>

            </div>

            <button type="button" onclick="addProduct()"  class="btn btn-default">Submit</button>

        </form>
    </div>
</div>
<div class="form-group">
    <div class="alert alert-success alert-dismissable" id="done">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <h4>
            done!
        </h4>  your product has been updated . <a href="#" class="alert-link"></a>
    </div>


</div>
<%@include file="AdminDown.jsp" %>
