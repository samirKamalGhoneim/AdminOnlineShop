
<%@include file="AdminHead.jsp" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="Database.Database"%>

<script>
    function addCategory()
    {
        var n = document.getElementById("name").value;
        var id = document.getElementById("id").value
        var d = document.getElementById("description").value;

        var xmlhttp = new XMLHttpRequest();// to create new object from XMLHTTPReqest 
        xmlhttp.open("GET", "servletUpdateCategory?name=" + n + "&desc=" + d + "&id=" + id, true);
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
        <form role="form"   method="post" action="servletAddProduct">
            <%
    int r_id=Integer.parseInt(request.getParameter("id"));
    Database logic = new Database();
    ResultSet rs = logic.getCategoryById(r_id);
    while (rs.next()) {
        String id= rs.getString(1);
            %>
            <div class="form-group">
                <input type="hidden" id="id" value="<%= id %>">
                <label for="exampleInputEmail1">Category Name</label><input id="name" name="name" value="<%= rs.getString(2) %>" type="text" class="form-control" />
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Category Description </label>
                <textarea name="description" id="description"  rows="10" class="form-control" ><%= rs.getString(3) %></textarea>
            </div>
            <button type="button" onclick="addCategory()"  class="btn btn-default">Submit</button>
            <% }%>             
        </form>
    </div>
</div>
<div class="form-group">
    <div class="alert alert-success alert-dismissable" id="done">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <h4>
            done!
        </h4>  your Category has been updated . <a href="#" class="alert-link"></a>
    </div>


</div>
<%@include file="AdminDown.jsp" %>
