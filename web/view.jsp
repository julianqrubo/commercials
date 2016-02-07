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
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/slider/default/default.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/slider/bar/bar.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/nivo-slider.css">
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/slick/slick.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/slick/slick-theme.css"/>
        <script>
            var _app_root = '${pageContext.servletContext.contextPath}';
        </script>
        <script src="${pageContext.servletContext.contextPath}/js/jquery.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jquery.nivo.slider.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/slick/slick.min.js"></script>
    </head>
    <body class="public">
        <div class="user-header">
            <form>
                <input type="text" name="q" id="q" class="comm-search ui-input-text ui-input-search ui-btn" placeholder="Que est&aacute;s buscando..."/>
            </form>
        </div>
        <div class="content">
            <div class="slider-wrapper theme-bar">
                <div id="slider" class="nivoSlider">
                    <c:forEach items="${__list__}" var="image" >
                        <img src="admin/upload?getthumb=${image.id}&w=1090&h=500" data-thumb="admin/upload?getthumb=${image.id}" alt="${image.name}" />
                    </c:forEach>
                </div>
            </div>
            <div class="corrousel">
                <%--<c:forEach begin="${1}" end="${5}">--%>
                    <c:forEach items="${__list__}" var="image" >
                        <div style="margin: 5px;">
                            <img src="admin/upload?getthumb=${image.id}&w=130&h=90" data-thumb="admin/upload?getthumb=${image.id}" alt="${image.name}" />
                        </div>
                    </c:forEach>
                <%--</c:forEach>--%>
            </div>
        </div>
        <script type="text/javascript">
            $(window).load(function () {
                $('#slider').nivoSlider({prevText: '<', nextText: '>'});
                $('.corrousel').slick({
                    infinite: true,
                    slidesToShow: 8,
                    slidesToScroll: 1,
                    autoplay: true,
                    autoplaySpeed: 2000,
                    arrows: false
                });
            });
        </script>
    </body>
</html>
