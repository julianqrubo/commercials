<%@include file="templates/header.jsp" %>
<h1>${_title_}</h1>

<form action="${pageContext.servletContext.contextPath}/admin/cities" method="post" class="ui-filterable">

    <input type="hidden" id="id" name="id" value="${id}"/>

    <label for="names">Nombre:</label>
    <input type="text" name="name" id="name" value="${_entity_.name}" req="true"  />

    <label for="code">C&oacute;digo</label>
    <input type="text" name="code" id="code" value="${_entity_.code}" req="true"  />

    <label for="names">País:</label>

    <input id="country-label" autocomplete="off" data-type="search" placeholder="Selecciona un país..." value="${refs.countryLabel}">
    <input id="country" name="country" type="hidden" value="${_entity_.country}"/>
    <ul id="country-list" data-role="listview" data-inset="true" data-filter="true" data-input="#country-label" service="/admin/countries/list"></ul>
    
    
    <label for="names">Departamento</label>

    <input id="department-label" autocomplete="off" data-type="search" placeholder="Selecciona un departamento..." value="${refs.departmentLabel}">
    <input id="department" name="department" type="hidden" value="${_entity_.department}"/>
    <ul id="department-list" data-role="listview" data-inset="true" data-filter="true" data-input="#department-label" service="/admin/departments/list"></ul>
    

    <input type="submit" value="Guardar" />
    <c:if test="${not empty id}">
        <input type="button" value="Eliminar" id="delete_city" />
    </c:if>

</form>




<%@include file="templates/footer.jsp" %>