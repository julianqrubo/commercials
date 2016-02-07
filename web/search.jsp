<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Tus anuncios</title>
        <link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/favicon.ico">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/themes/default/jquery.mobile-1.4.5.min.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/_assets/css/jqm-demos.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/main.css">
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
        <script>
            var _app_root = '${pageContext.servletContext.contextPath}';
        </script>
        <script src="${pageContext.servletContext.contextPath}/js/jquery.js"></script>
    </head>
    <body class="public">
        <div class="user-header">
            <form>
                <input type="text" name="q" id="q" class="comm-search ui-input-text ui-input-search ui-btn" placeholder="Que est&aacute;s buscando..." value="${q}"/>
            </form>
        </div>
        <div class="content">
            <c:forEach items="${__list__}" var="ad">
                <div class="ad-title">${ad.title}</div>
                <div class="ad-dsc">${ad.description}</div>
            </c:forEach>
        </div>
    </body>
</html>
