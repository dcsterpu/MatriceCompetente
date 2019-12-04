<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>

<br>

 <button type="button" class="btn btn-info"
 onclick="return addCollaborators('${varPath}');">Adauga colaboratori</button>

 <button type="button" class="btn btn-warning"
 onclick="return modify('${codProiect}')">Inapoi</button>

 <br><hr>
        <form:form id="searchSkillsForm" method="POST">
            <table>
                <tr>
                    <th><label>Search</label>
                    <input type="text" pattern=".{3,}" name = "searchTerm" title="Campul trebuie sa contina cel putin 3 caractere." required/>
                    <input type="submit" value="Search"/></th>
                </tr>
            </table>
        </form:form>
 <br>
         <c:if test="${not empty result}">
         <table>
             <tr>
                 <th>Competenta</th>
                 <th>Categorie</th>
                 <th></th>

             </tr>
             <c:forEach var="tempResult" items="${result}">
                 <spring:url var="modifyUser" value="/webCM/leaders/project/${varPath}/addSkills/add">
                     <spring:param name="skillId" value="${tempResult.idSkill}"/>
                 </spring:url>
             <tr>
                 <td>${tempResult.numeSkill}</td>
                 <td>${tempResult.categorie}</td>

                 <td>

                              <form:form action="${modifyUser}" method="POST">
                                <input type="submit" class="btn btn-success" value="Adauga">
                              </form:form>

                 </td>
             </tr>
             </c:forEach>

         </table>
         </c:if>



 <script>
    $("#searchSkillsForm").submit(function(e) {
            e.preventDefault();

            $.ajax({
                type: "POST",
                headers: {"X-CSRF-TOKEN": $("meta[name='_csrf']").attr("content")},
                data: $("#searchSkillsForm").serialize(),
                url: "/project/" + '${codProiect}' + "/addSkills",
                contentType : "application/json",
                success: function(data){
                    $("#div3").html(data);
                },
                error: function(res){
                        console.log("ERROR");
                        console.log(res);
                }
            });
        });

 </script>