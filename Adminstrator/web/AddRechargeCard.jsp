<%@include file="AdminHead.jsp" %>
<%@include file="AdminUp.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    function handle() {
        document.getElementById('charge').style.display = 'block';
        document.getElementById('success').style.display = 'none';

    }
</script>


<div class="w3-container">


    <button onclick="handle()" class="w3-button">Add Cards</button>

    <div id="charge" class="w3-modal">
        <div class="w3-modal-content w3-card-3">
            <header class="w3-container w3-blue "> 
                <span onclick="document.getElementById('charge').style.display = 'none'" 
                      class="w3-closebtn">&times;</span>
                <h2>Add Cards Balances</h2>
            </header>
            <br>
            <div class="w3-container">
                <form action="AddCards" method="get" class="w3-container">
                    <input class="w3-input w3-hover-blue" type="number" name="number" min="1" placeholder="number of cards" required/>
                    <br>

                    <input class="w3-input w3-hover-blue" type="number" name="balance" min="50" placeholder="Amount of balance" required/>
                    <br>
                    <input class="w3-btn w3-blue" type="submit" value="Add Card(s)">
                    <br>
                </form>
            </div>
            <footer class="w3-container  w3-white">
                <p class="w3-center">&COPY; Online Shopping</p>

            </footer>

        </div>
    </div>
</div>
<div class="w3-panel w3-blue" style="display: none;" id="success">
    <span class="w3-closebtn" onclick="this.parentElement.style.display = 'none'">X</span>
    <p>Added successfully</p>
</div>
<c:if test="${!empty requestScope.success}">
    <script>
        document.getElementById("success").style.display = "block";
    </script>
    <% request.removeAttribute("success");%>

</c:if>


<%@include file="AdminDown.jsp" %>
