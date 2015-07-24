<%@include file="templates/header.jsp" %>
<h1>${_title_}</h1>


<form action="${pageContext.servletContext.contextPath}/admin/users" method="post">
    
    <input type="hidden" id="id" name="id" value="${id}"/>

    <label for="names">Nombres:</label>
    <input type="text" name="names" id="names" value="${_entity_.names}" req="true"  />

    <label for="last_names">Apellidos:</label>
    <input type="text" name="last_names" id="last_names" value="${_entity_.last_names}" req="true" />

    <label for="email">Correo:</label>
    <input type="text" name="email" id="email" value="${_entity_.email}" req="true" />

    <label for="username">Usuario:</label>
    <input type="text" name="username" id="username" value="${_entity_.username}" req="true" />

    <label for="password">Contrase&ntilde;a:</label>
    <input type="password" name="password" id="password" value="${_entity_.password}" req="true" />

    <input type="submit" value="Guardar" />

</form>


<%@include file="templates/footer.jsp" %>