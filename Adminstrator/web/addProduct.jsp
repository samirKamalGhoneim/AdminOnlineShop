<%@include file="AdminHead.jsp" %>
<%@include file="AdminUp.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--<div id="openModal" class="modalDialog">
    <div>
        <a title="Close" onclick="document.getElementById('openModal').style.display = 'none'" class="close">X</a>
        <h2>Online Shopping</h2>
        <p>Added successfully</p>
    </div>
</div>-->
<div class="w3-panel w3-blue" style="display: none;" id="openModal">
    <span class="w3-closebtn" onclick="this.parentElement.style.display = 'none'">X</span>
    <p>Added successfully</p>
</div>
<div class="row clearfix">
    <div class="col-md-12 column">
        <form role="form" enctype="multipart/form-data"  method="post" action="servletAddProduct">
            <div class="form-group">
                <label for="exampleInputEmail1">Product Name</label><input id="name" name="name" type="text" class="form-control" required/>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Product Description </label>
                <textarea style="height: 75px;" name="description" id="description" rows="10" class="form-control" required></textarea>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Quantity</label><input id="quantity" min="0" name="quantity" type="number" class="form-control" required/>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">discount</label><input id="discount" min="0" name="discount" type="number" class="form-control" required/>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Product Price </label><input type="number" min="0" name="price" class="form-control" id="price" required/>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Category Name </label><input type="text" name="category" class="form-control" id="category" required/>

            </div>


            <div class="form-group">
                <label for="exampleInputFile">image 1</label><input type="file" name="file"  id="file" required/>
                <label for="exampleInputFile">image 2</label><input type="file" name="file2"  id="file1" required/>
                <label for="exampleInputFile">image 3</label><input type="file" name="file3"  id="file2" required/>
                <label for="exampleInputFile">image 4</label><input type="file" name="file4"  id="file3" required/>
                <label for="exampleInputFile">image 5</label><input type="file" name="file5"  id="file4" required/>

            </div>
            <button type="submit"  class="btn btn-default">Submit</button>
        </form>
    </div>
    <div id="modal_popup">
    </div>


</div>


<c:if test="${!empty requestScope.valid}"> 
    <script>
        document.getElementById("openModal").style.display = "block";
    </script>
    <% request.removeAttribute("valid");%>
</c:if>


<%@include file="AdminDown.jsp" %>
