<%@ include file="header.jspf"%>

<!--<h2>Home Page</h2>-->
<!--<hr>-->
<!--<p>-->
<!--    Welcome!-->
<!--</p>-->
<!--<hr>-->
<!--<p>-->
<!--    User:-->
<!--    <security:authentication property="principal.username"/>-->
<!--    <br><br>-->
<!--    Role(s):-->
<!--    <security:authentication property="principal.authorities"/>-->
<!--    <br><br>-->

<!--&lt;!&ndash;    <button type="button" class="btn btn-info" onclick="window.location.href='/webCM/personalProfile'">Personal Profile</button>&ndash;&gt;-->


<!--</p>-->

<!--<hr>-->

<!--<hr>-->

<div class="page">
    <!-- tabs -->
    <div class="pcss3t pcss3t-effect-scale pcss3t-theme-1">
        <input type="radio" name="pcss3t" checked  id="tab1"class="tab-content-first" onclick="skillMatrix()">
        <label for="tab1"><i class="icon-bolt"></i>Skill Matrix</label>

        <input type="radio" name="pcss3t" id="tab2" class="tab-content-2" onclick="showSkills()">
        <label for="tab2"><i class="icon-picture"></i>Personal Skills</label>

        <input type="radio" name="pcss3t" id="tab3" class="tab-content-3" onclick="leaders()">
        <label for="tab3"><i class="icon-cogs"></i>Manage Team</label>

        <input type="radio" name="pcss3t" id="tab5" class="tab-content-last">
        <label for="tab5"><i class="icon-globe"></i>Search People</label>

        <ul>
            <li class="tab-content tab-content-first typography" id="div1">
                <%@include file="currentProj.jsp" %>
                <script>
                    function skillMatrix() {
                        $("#div1").load("/webCM/currentProj");
                    }
                </script>
            </li>

            <li class="tab-content tab-content-2 typography" id="div2">
                <script>
                    function showSkills() {
                        $("#div2").load("/webCM/personalProfile");
                    }
                </script>
            </li>

            <li class="tab-content tab-content-3 typography" id="div3">
                <script>
                    function leaders() {
                        $("#div3").load("/webCM/leaders");
                    }
                </script>
            </li>

            <li class="tab-content tab-content-last typography">
                <div class="typography">

                </div>
            </li>
        </ul>
    </div>
    <!--/ tabs -->
</div>

<!--<button type="button" class="btn btn-success" onclick="window.location.href='/'">HomePage</button>-->
<!--<button type="button" class="btn btn-success" onclick="window.location.href='/webCM/leaders'">Manager</button>-->

<%@ include file="footer.jspf"%>