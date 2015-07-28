<%@include file="templates/header.jsp" %>
<h1>${_title_}</h1>

<form action="${pageContext.servletContext.contextPath}/admin/countries" method="post">

    <input type="hidden" id="id" name="id" value="${id}"/>

    <label for="names">Nombre:</label>
    <input type="text" name="name" id="name" value="${_entity_.name}" req="true"  />

    <label for="names">País:</label>
    <input id="country" data-type="search" placeholder="Selecciona un país...">
    <select name="country" id="country" data-native-menu="false" class="filterable-select">
    </select>

    <input type="submit" value="Guardar" />

</form>




<%@include file="templates/footer.jsp" %>