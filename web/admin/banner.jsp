<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="templates/header.jsp" %>

<div data-role="header">

    <h3>${_title_}</h3>
    <c:if test="${__list__.size() < 1}">
        <a href="${pageContext.servletContext.contextPath}/admin/banner/create" class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-right ui-icon-plus">Crear</a>
    </c:if>
</div>

<table data-role="table" id="banner-table" data-filter="true" class="ui-responsive">
    <thead>
        <tr>
            <th data-priority="persist">Nombre</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${__list__}" var="banner" >
            <tr>
                <td><a href="banner/edit/${banner.id}">Banner principal</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<%@include file="templates/footer.jsp" %>