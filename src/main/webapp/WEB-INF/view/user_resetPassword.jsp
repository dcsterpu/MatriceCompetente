<%@ include file="header.jspf"%>

<%-- This file renders the reset-password form
     The methods are taken from the controller class through the Spring's form tag library
     The objects are defined within the modelAttribute field
     The JSTL core tag provides variable support and flow control
     --%>

<br>
<h3>Resetare parola</h3>
<hr>
<br>
<c:choose>
<c:when test="${loginUser.isExpired()}">
<h3>Salut, ${userExpleo.prenume}!</h3>

<div>
    <form:form action="${pageContext.request.contextPath}/forgotPassword/newPassword" method="POST" modelAttribute = "password">
     <table class="table table-striped">
        <thead class="thead-light">
        <tr>
            <th><label> Parola noua: </label>
            <form:input type="password" path="newPassword"/></th>
        </tr>
        <tr>
            <th><label> Confirma parola noua: </label>
            <form:input type="password" path="confirmPassword" /></th>
        </tr>
        <input type="hidden" name="userId" value="${loginUser.id}">
        </thead>
    </table>
<input type="hidden" name = "token" value="${token}">
   <button type="submit" class = "btn btn-outline-success" style="position: absolute; right: 10;">Change Password</button>
   <br>

            <c:forEach var="message"  items = "${errors}">
           <span class="error">${message}</span>
           <br>
            </c:forEach>
   </form:form>
</div>
</c:when>
<c:otherwise>
<br><br>
<h2> <p> Link-ul este invalid. </p> <h2>

<script>
  setTimeout(function() {
      window.location.href = "/";
  }, 3000); // <-- this is the delay in milliseconds
</script>
</c:otherwise>
</c:choose>
<%@ include file="footer.jspf"%>
