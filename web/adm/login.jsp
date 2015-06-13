<%@include file="tpl/header.jsp" %>

<div id="top-header" class="color1">
<!--    <a class="signin" href="#">
            <span class="signin-icon">&nbsp;</span>
            <span class="signin-text">Inicio de sesi&oacute;n</span>
        </a>-->
    <a href="${pageContext.servletContext.contextPath}" style="color: #fff; line-height: 30px; vertical-align: middle;">&lt;&lt;Volver al sitio web</a>
</div>

<form action="${pageContext.servletContext.contextPath}/adm/login" method="post" id="login-form">
    <div class="color3">
        <div style="margin-bottom: 20px;">
            <div style="text-align: left;">
                <label for="username">Usuario:</label>
            </div>
            <input type="text" id="username" name="username" placeholder="Usuario" />
        </div>
        <div>
            <div style="text-align: left;">
                <label for="password">Contrase&ntilde;a:</label>
            </div>
            <input type="password" id="password" name="password" placeholder="Contrase&ntilde;a" />
        </div>
        <div style="margin-top: 20px;">
            <input type="submit" value="Entrar">
        </div>
    </div>
</form>

<%@include file="tpl/footer.jsp" %>