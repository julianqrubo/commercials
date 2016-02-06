<%@include file="templates/header.jsp" %>
<h1>Tus anuncios</h1>
<p><strong>Tus anuncios es una aplicaci&oacute;n que te permite publicar tus anuncios de una manera r&aacute;pida y sencilla.</strong></p>
<img src="${pageContext.servletContext.contextPath}/_assets/img/home.jpg" alt="Tus anuncios.com">

</div><!-- /content -->
<div data-role="panel" class="jqm-navmenu-panel" data-position="left" data-display="overlay" data-theme="a">
    <h2>Inicio de sesi&oacute;n</h2>
    <c:if test="${not empty error}">
    <h4 class="ui-bar ui-bar-a ui-corner-all">${error}</h4>
    </c:if>

          <div class="ui-body ui-body-a ui-corner-all">
        <form action="${pageContext.servletContext.contextPath}/admin" method="post">
            <label for="username">Usuario:</label>
            <input type="text" name="username" id="username" placeholder="Usuario" value="">

            <label for="password">Contrase&ntilde;a:</label>
            <input type="password" name="password" id="password" value="" autocomplete="off">

            <input type="submit" value="Iniciar sesi&oacute;n" />
        </form>
    </div>
</div>

<div data-role="footer" data-position="fixed" data-tap-toggle="false" class="jqm-footer">
    <p>Tus anuncios 2.0</p>
    <p>Copyright 2015 - Nemesis</p>
</div><!-- /footer -->
</div><!-- /page -->
</body>
</html>