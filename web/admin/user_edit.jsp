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

    <label for="state">Estado:</label>
    <select id="state" name="state" data-role="flipswitch" data-wrapper-class="custom-size-flipswitch">
        <option value="0" ${ _entity_.state == 0 ? "selected='true'" : "" }>Inactivo</option>
        <option value="1" ${ _entity_.state == 1 ? "selected='true'" : "" }>Activo</option>
    </select>

    <fieldset data-role="controlgroup">
        <legend>Derechos:</legend>
        <input type="checkbox" name="right1" id="right1" ${ _entity_.right1 ? "checked='checked'" : "" } value="1" >
        <label for="right1">Administrar perfil</label>
        <input type="checkbox" name="right2" id="right2" ${ _entity_.right2 ? "checked='checked'" : "" } value="2">
        <label for="right2">Administrar aplicación</label>
    </fieldset>

    <fieldset data-role="controlgroup">
        <legend>Clasificaci&oacute;n</legend>
        <input type="radio" name="type" id="type1" ${ _entity_.type1 ? "checked='checked'" : "" } value="1" >
        <label for="type1">Oro</label>
        <input type="radio" name="type" id="type2" ${ _entity_.type2 ? "checked='checked'" : "" } value="2">
        <label for="type2">Plata</label>
        <input type="radio" name="type" id="type3" ${ _entity_.type3 ? "checked='checked'" : "" } value="3">
        <label for="type3">Bronce</label>
    </fieldset>

    <input type="submit" value="Guardar" />

</form>


<%@include file="templates/footer.jsp" %>