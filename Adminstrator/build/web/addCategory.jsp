<%@include file="AdminHead.jsp" %>
<script>
    function addCategory()
    {
        var n = document.getElementById("name").value;
        var d = document.getElementById("description").value;

        var xmlhttp = new XMLHttpRequest();// to create new object from XMLHTTPReqest 
        xmlhttp.open("GET", "servletAddCategory?name=" + n + "&desc=" + d, true);
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
            <div class="form-group">
                <label for="exampleInputEmail1">Category Name</label><input id="name" name="name" type="text" class="form-control" />
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Category Description </label>
                <textarea name="description" id="description" rows="10" class="form-control" id="exampleInputPassword1"></textarea>
            </div>
            <button type="button" onclick="addCategory()"  class="btn btn-default">Submit</button>
        </form>
    </div>
</div>
<div class="form-group">
    <div class="alert alert-success alert-dismissable" id="done">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <h4>
            done!
        </h4>  your product has been added . <a href="#" class="alert-link"></a>
    </div>


</div>
<%@include file="AdminDown.jsp" %>
