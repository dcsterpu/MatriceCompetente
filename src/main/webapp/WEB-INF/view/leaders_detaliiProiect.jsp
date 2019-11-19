<%@ include file="leaders_leadersHeader.jspf"%>

<br>

 <button type="button" class="btn btn-info"
 onclick="window.location.href='/webCM/leaders/${project.codProiect}/adaugaColaboratori'">Adauga colaboratori</button>

 <button type="button" class="btn btn-info"
 onclick="window.location.href='/webCM/leaders/${project.codProiect}/addSkills'">Adauga competente</button>

  <button type="button" class="btn btn-warning"
  onclick="window.location.href='/webCM/leaders/'">Proiecte</button>

<br>
<br>

<form:form action = "/webCM/leaders/${project.codProiect}/renuntaLaProiect" method = "POST">
  <input type="submit" class="btn btn-danger" value = "Renunta la proiect">
</form:form>


 <hr>


 <font size="5">Numele proiectului: ${project.numeProiect}</font>
 <br>
 <font size="5"> Cod: ${project.codProiect}</font>
 <br><hr>

     <table>
         <tbody>
          <font size="4">Colaboratori</font>
         <tr>
            <th>Nume</th>
            <th>Prenume</th>
            <th>Numar Matricol</th>
            <th><th>
         </tr>
         </thead>

         <tbody>
            <c:forEach var="user" items="${users}">
                <spring:url var="removeUser" value="/webCM/leaders/${varPath}/removeEmp">
                    <spring:param name="userId" value="${user.id}"/>
                </spring:url>



               <tr>
                 <td>${user.nume}</td>
                 <td>${user.prenume}</td>
                 <td>${user.numarMatricol}</td>
                 <td>
                    <form:form action="${removeUser}" method="POST">
                        <input type="submit" class="btn btn-danger" value="Elimina-l din proiect">
                    </form:form>
                 </td>
               </tr>
            </c:forEach>
         </tbody>
      </table>

       <br>
       <hr>

     <table>
         <tbody>
         <font size="4">Competente</font>
         <tr>
            <th>Competenta</th>
            <th>Categorie</th>
            <th></th>
         </tr>
         </thead>

         <tbody>
            <c:forEach var="skill" items="${skills}">

               <spring:url var="removeSkill" value="/webCM/leaders/${varPath}/removeSkill">
                   <spring:param name="skillId" value="${skill.idSkill}"/>
               </spring:url>

               <tr>
                 <td>${skill.numeSkill}</td>
                 <td>${skill.categorie}</td>
                 <td>
                    <form:form action="${removeSkill}" method="POST">
                        <input type="submit" class="btn btn-danger" value="Elimina competenta"
                    </form:form>
                 </td>
               </tr>
            </c:forEach>
         </tbody>
      </table>



<%@ include file="footer.jspf"%>