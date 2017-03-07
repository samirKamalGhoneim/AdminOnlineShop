<%@page import="java.sql.ResultSet"%>
<%@page import="Database.Database"%>
<%@include file="AdminHead.jsp" %>
<script>
    function deletec(id)
    {

        var xmlhttp = new XMLHttpRequest();// to create new object from XMLHTTPReqest 
        xmlhttp.open("GET", "servletDeleteCategory?id=" + id, true);
        xmlhttp.send();
        xmlhttp.onreadystatechange = function ()
        {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
            {
                document.getElementById(id).remove();
                document.getElementById("done").style.visibility = "visible";

            }
        }


    }

</script>

<%@include file="AdminUp.jsp" %>
<table class="table table-hover">
    <tr>
        <td>Product</td>
        <td></td>
        <td></td>

    </tr>
    <%

                Database logic = new Database();
                ResultSet rs = logic.ShowAllCategory();
                while (rs.next()) {
                    String id= rs.getString(1);
    %>
    <tr id="<%= id %>">
        <td><%= rs.getString(2)%></td>
        <td><a href="updateCategory.jsp?id=<%= id %>">update</a></td>
        <td onclick="deletec(<%= id %>);">delete</td>

    </tr>
    <% }%>

</table>

<div class="form-group">
    <div class="alert alert-success alert-dismissable" id="done">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <h4>
            done!
        </h4>  your category has been deleted . <a href="#" class="alert-link"></a>
    </div>


</div>
<%@include  file="AdminDown.jsp" %>