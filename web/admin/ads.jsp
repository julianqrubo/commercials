<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="templates/header.jsp" %>

<div data-role="header">
    <h3>${_title_}</h3>
    <c:if test="${__list__.size() < applicationScope.__ads_count__}">
        <a href="${pageContext.servletContext.contextPath}/admin/ads/create" class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-right ui-icon-plus">Crear</a>
    </c:if>
</div>

<form>
        <input id="filterTable-input-ads" data-type="search">
</form>
<table data-role="table" id="ad-table" data-filter="true" data-input="#filterTable-input-ads" class="ui-responsive">
    <thead>
        <tr>
            <th data-priority="persist">T&iacute;tulo</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${__list__}" var="ad" >
            <tr>
                <td><a href="ads/edit/${ad.id}">${ad.title}</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<%@include file="templates/footer.jsp" %>