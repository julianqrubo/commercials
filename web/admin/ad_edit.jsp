<%@include file="templates/header.jsp" %>
<h1>${_title_}</h1>

<form action="${pageContext.servletContext.contextPath}/admin/ads" method="post" >

    <input type="hidden" id="id" name="id" value="${id}"/>

    <label for="name">Título:</label>
    <input type="text" name="title" id="title" value="${_entity_.title}" req="true"  />

    <label for="description">Descripci&oacute;n</label>
    <textarea cols="40" rows="8" name="description" id="description" data-mini="false">${_entity_.description}</textarea>

    <label for="tel-1">Tel&eacute;fono:</label>
    <input type="tel" data-clear-btn="true" name="phone1" id="phone1" value="${_entity_.phone1}">

    <label for="tel-1">Tel&eacute;fono 2:</label>
    <input type="tel" data-clear-btn="true" name="phone2" id="phone2" value="${_entity_.phone2}">
    
    <label for="tel-1">Tel&eacute;fono 3:</label>
    <input type="tel" data-clear-btn="true" name="phone3" id="phone3" value="${_entity_.phone3}">

    <label for="country-label">País:</label>
    <input id="country-label" autocomplete="off" data-type="search" placeholder="Selecciona un país..." value="${refs.countryLabel}">
    <input id="country" name="country" type="hidden" value="${_entity_.country}"/>
    <ul id="country-list" data-role="listview" data-inset="true" data-filter="true" data-input="#country-label" service="/admin/countries/list"></ul>
    
    <label for="department-label">Departamento</label>
    <input id="department-label" autocomplete="off" data-type="search" placeholder="Selecciona un departamento..." value="${refs.departmentLabel}">
    <input id="department" name="department" type="hidden" value="${_entity_.department}"/>
    <ul id="department-list" data-role="listview" data-inset="true" data-filter="true" data-input="#department-label" service="/admin/departments/list"></ul>
    
    <label for="city-label">Ciudad</label>
    <input id="city-label" autocomplete="off" data-type="search" placeholder="Selecciona una ciudad..." value="${refs.cityLabel}">
    <input id="city" name="city" type="hidden" value="${_entity_.city}"/>
    <ul id="city-list" data-role="listview" data-inset="true" data-filter="true" data-input="#city-label" service="/admin/cities/list"></ul>
    
    
    <input type="submit" value="Guardar" />
    <c:if test="${not empty id}">
        <input type="button" value="Eliminar" id="delete_country" />
    </c:if>
</form>


<%@include file="templates/footer.jsp" %>