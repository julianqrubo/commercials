<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="templates/header.jsp" %>

<div data-role="header">
    <h3>${_title_}</h3><a href="${pageContext.servletContext.contextPath}/admin/departments/create" class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-right ui-icon-plus">Crear</a>
</div>

<form>
        <input id="filterTable-input-deptartments" data-type="search">
</form>
<table data-role="table" id="departments-table" data-filter="true" data-input="#filterTable-input-deptartments" class="ui-responsive">
    <thead>
        <tr>
            <th data-priority="persist">Nombre</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${__list__}" var="department" >
            <tr>
                <td><a href="departments/edit/${department.id}">${department.name}</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<%@include file="templates/footer.jsp" %>