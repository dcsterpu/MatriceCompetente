<%@ include file="header.jspf"%>
<%@ include file="navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<h2>Profil Personal</h2>

<%--
<!--            Display username and role-->
--%>
<p>
<hr>
    <b>Nume:</b> ${user.nume} ${user.prenume}
    <br>
    <b>Numar matricol</b>: ${user.numarMatricol}
    <br>
    <b>Adresa email:</b>
    <security:authentication property="principal.username"/>
    <br>
    <b>Data angajare:</b> ${user.dataAngajare}
    <br>
    <b>Functie:</b> ${user.functie}
    <br>
    <hr>
    <b>Skilluri personale:</b>
</p>

<table>


    <thead>
    <tr>
        <th>Nume Skill</th>
        <th>Categorie</th>
        <th>Auto-Evaluare</th>
        <th>Valoare noua</th>
        <th>Elimina</th>
    </tr>
    </thead>
    <tbody>

<c:forEach var="userSkill" items="${userSkills}">

   <c:url var="deleteLink" value="/retex/deleteSkill">
       <c:param name="skillId" value="${userSkill.skill.idSkill}"/>
   </c:url>

<!--    <c:url var="modify" value="/retex/modify">-->
<!--        <c:param name="skillId" value="${userSkill.skill.idSkill}"/>-->
<!--    </c:url>-->
<!--    <form action="${modify}">-->
<!--        <input type="int" name="evaluation" value="1-4"/>-->
<!--        <input type='submit' value='Edit' />-->
<!--    </form>-->
    <tr>
        <td>${userSkill.skill.numeSkill}</td>
        <td>${userSkill.skill.categorie}</td>
        <td>${userSkill.evaluation}</td>
        <td>
                <form method = "get" action="${modify}">
                    <select name="evaluation" default = ${userSkill.evaluation}>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                    </select>
<!--                    <input type="number" name="evaluation" value=""/>-->
                    <input type=hidden name="idskill" value="${userSkill.skill.idSkill}"/>
                    <input type='submit' value='Submit' />
                </form>
        </td>
        <td>
            <a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete this skill?'))) return false">
                <button type="button" class="btn-info">X</button>
            </a><!--Java script code-->
        </td>

    </tr>
</c:forEach>
    </tbody>
</table>
<br>
<div>
<button type="button" class="btn btn-info" onclick="window.location.href='/retex/'">Back</button>
<button type="button" class="btn btn-info" onclick="window.location.href='/retex/leaders'">Manager</button>
<button type="button" class="btn btn-info" onclick="window.location.href='/retex/personalProfile/showFormForAddSkill'">Adauga Skill</button>
</div>
</html>