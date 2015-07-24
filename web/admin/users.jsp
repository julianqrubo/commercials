<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="templates/header.jsp" %>

<div data-role="header">
    <h3>Usuarios</h3><a href="${pageContext.servletContext.contextPath}/admin/users/create" class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-right ui-icon-plus">Crear</a>
</div>

<form>
        <input id="filterTable-input" data-type="search">
</form>
<table data-role="table" id="movie-table" data-filter="true" data-input="#filterTable-input" class="ui-responsive">
        <thead>
                <tr>
                        <th data-priority="persist">Nombre</th>
                        <th data-priority="4">Correo</th>
                    </tr>
                </thead>
            <tbody>
        <c:forEach items="${__list__}" var="user" >
                        <tr>
                                <td><a href="users/edit/${user.id}">${user.names} ${user.last_names}</a></td>
                                <td>${user.email}</td>
                            </tr>
            </c:forEach>
                </tbody>
        </table>
<%@include file="templates/footer.jsp" %>