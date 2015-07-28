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
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
        <script>
            var _app_root = '${pageContext.servletContext.contextPath}';
        </script>
        <script src="${pageContext.servletContext.contextPath}/js/jquery.js"></script>
        <script src="${pageContext.servletContext.contextPath}/_assets/js/index.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jquery.mobile-1.4.5.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/main.js"></script>
    </head>
    <body>
        <div data-role="page" class="jqm-demos jqm-home">
            <div data-role="header" class="jqm-header">
                <h2><img src="${pageContext.servletContext.contextPath}/_assets/img/logo.png" alt="Commercials 2.0"></h2>
                <a href="#" class="jqm-navmenu-link ui-btn ui-btn-icon-notext ui-corner-all ui-icon-bars ui-nodisc-icon ui-alt-icon ui-btn-left">Menu</a>
                <!--<a href="#" class="jqm-search-link ui-btn ui-btn-icon-notext ui-corner-all ui-icon-search ui-nodisc-icon ui-alt-icon ui-btn-right">Search</a>-->
            </div><!-- /header -->
            <div role="main" class="ui-content jqm-content">