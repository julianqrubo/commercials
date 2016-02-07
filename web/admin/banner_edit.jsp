<%@include file="templates/header.jsp" %>
<h1>${_title_}</h1>

<form action="${pageContext.servletContext.contextPath}/admin/banner" method="post" >

    <input type="hidden" id="id" name="id" value="${id}"/>
    
    <label for="user1">Usuario 1:</label>
    <select name="user1" id="user1">
        <c:forEach items="${__list__}" var="user" >
            <option value="${user.id}" ${ user.id == _entity_.user1 ? "selected='true'" : "" } >${user.names} ${user.last_names}</option>
        </c:forEach>
    </select>
    
    <label for="user2">Usuario 2:</label>
    <select name="user2" id="user2">
        <c:forEach items="${__list__}" var="user" >
            <option value="${user.id}" ${ user.id == _entity_.user2 ? "selected='true'" : "" }>${user.names} ${user.last_names}</option>
        </c:forEach>
    </select>
    
    <label for="user3">Usuario 3:</label>
    <select name="user3" id="user3">
        <c:forEach items="${__list__}" var="user" >
            <option value="${user.id}" ${ user.id == _entity_.user3 ? "selected='true'" : "" }>${user.names} ${user.last_names}</option>
        </c:forEach>
    </select>
    
    <label for="user4">Usuario 4:</label>
    <select name="user4" id="user4">
        <c:forEach items="${__list__}" var="user" >
            <option value="${user.id}" ${ user.id == _entity_.user4 ? "selected='true'" : "" }>${user.names} ${user.last_names}</option>
        </c:forEach>
    </select>
    
    <label for="user5">Usuario 5:</label>
    <select name="user5" id="user5">
        <c:forEach items="${__list__}" var="user" >
            <option value="${user.id}" ${ user.id == _entity_.user5 ? "selected='true'" : "" }>${user.names} ${user.last_names}</option>
        </c:forEach>
    </select>

    <input type="submit" value="Guardar" />
    <c:if test="${not empty id}">
        <input type="button" value="Eliminar" id="delete_country" />
    </c:if>
</form>


<%@include file="templates/footer.jsp" %>