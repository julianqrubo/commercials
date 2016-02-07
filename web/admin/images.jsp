<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="templates/header.jsp" %>

<div data-role="header">
    <h3>${_title_}</h3><a href="${pageContext.servletContext.contextPath}/admin/images/create" class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-right ui-icon-plus">Crear</a>
</div>

<form>
        <input id="filterTable-input-images" data-type="search">
</form>
<table data-role="table" id="image-table" data-filter="true" data-input="#filterTable-input-images" class="ui-responsive">
    <thead>
        <tr>
            <th data-priority="persist">Nombre</th>
            <th data-priority="persist">Principal</th>
            <th data-priority="persist"></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${__list__}" var="image" >
            <tr>
                <td><a href="images/edit/${image.id}">${image.name}</a></td>
                <td>${image.mainImage}</td>
                <td><img src="upload?getthumb=${image.id}" alt="${image.name}" /></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<%@include file="templates/footer.jsp" %>