<%@include file="templates/header.jsp" %>
<h1>${_title_}</h1>

<form action="${pageContext.servletContext.contextPath}/admin/countries" method="post" >

    <input type="hidden" id="id" name="id" value="${id}"/>

    <label for="name">Nombre:</label>
    <input type="text" name="name" id="name" value="${_entity_.name}" req="true"  />
    <label for="code">C&oacute;digo</label>
    <input type="text" name="code" id="code" value="${_entity_.code}" req="true"  />

    <input type="submit" value="Guardar" />
    <c:if test="${not empty id}">
        <input type="button" value="Eliminar" id="delete_country" />
    </c:if>
</form>


<%@include file="templates/footer.jsp" %>