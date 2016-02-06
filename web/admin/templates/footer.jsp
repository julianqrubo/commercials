<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</div><!-- /content -->
<div data-role="panel" class="jqm-navmenu-panel" data-position="left" data-display="overlay" data-theme="a">
    <ul class="jqm-list ui-alt-icon ui-nodisc-icon">
        <li data-filtertext="Inicio" data-icon="home"><a href="${pageContext.servletContext.contextPath}/admin">Inicio</a></li>
            <c:if test="${sessionScope.__user__.right2}">
            <li data-role="collapsible" data-enhanced="true" data-collapsed-icon="carat-d" data-expanded-icon="carat-u" data-iconpos="right" data-inset="false" class="ui-collapsible ui-collapsible-themed-content ui-collapsible-collapsed">
                <h3 class="ui-collapsible-heading ui-collapsible-heading-collapsed">
                    <a href="#" class="ui-collapsible-heading-toggle ui-btn ui-btn-icon-right ui-btn-inherit ui-icon-carat-d">
                        Usuarios
                    </a>
                </h3>
                <div class="ui-collapsible-content ui-body-inherit ui-collapsible-content-collapsed" aria-hidden="true">
                    <ul>
                        <li data-filtertext="form checkboxradio widget checkbox input checkboxes controlgroups"><a href="${pageContext.servletContext.contextPath}/admin/users/create" data-ajax="false">Crear</a></li>
                        <li data-filtertext="form checkboxradio widget radio input radio buttons controlgroups"><a href="${pageContext.servletContext.contextPath}/admin/users" data-ajax="false">Buscar</a></li>
                    </ul>
                </div>
            </li>
            <li data-role="collapsible" data-enhanced="true" data-collapsed-icon="carat-d" data-expanded-icon="carat-u" data-iconpos="right" data-inset="false" class="ui-collapsible ui-collapsible-themed-content ui-collapsible-collapsed">
                <h3 class="ui-collapsible-heading ui-collapsible-heading-collapsed">
                    <a href="#" class="ui-collapsible-heading-toggle ui-btn ui-btn-icon-right ui-btn-inherit ui-icon-carat-d">
                        Paises
                    </a>
                </h3>
                <div class="ui-collapsible-content ui-body-inherit ui-collapsible-content-collapsed" aria-hidden="true">
                    <ul>
                        <li data-filtertext="form checkboxradio widget checkbox input checkboxes controlgroups"><a href="${pageContext.servletContext.contextPath}/admin/countries/create" data-ajax="false">Crear</a></li>
                        <li data-filtertext="form checkboxradio widget radio input radio buttons controlgroups"><a href="${pageContext.servletContext.contextPath}/admin/countries" data-ajax="false">Buscar</a></li>
                    </ul>
                </div>
            </li>
            <li data-role="collapsible" data-enhanced="true" data-collapsed-icon="carat-d" data-expanded-icon="carat-u" data-iconpos="right" data-inset="false" class="ui-collapsible ui-collapsible-themed-content ui-collapsible-collapsed">
                <h3 class="ui-collapsible-heading ui-collapsible-heading-collapsed">
                    <a href="#" class="ui-collapsible-heading-toggle ui-btn ui-btn-icon-right ui-btn-inherit ui-icon-carat-d">
                        Departamentos
                    </a>
                </h3>
                <div class="ui-collapsible-content ui-body-inherit ui-collapsible-content-collapsed" aria-hidden="true">
                    <ul>
                        <li data-filtertext="form checkboxradio widget checkbox input checkboxes controlgroups"><a href="${pageContext.servletContext.contextPath}/admin/departments/create" data-ajax="false">Crear</a></li>
                        <li data-filtertext="form checkboxradio widget radio input radio buttons controlgroups"><a href="${pageContext.servletContext.contextPath}/admin/departments" data-ajax="false">Buscar</a></li>
                    </ul>
                </div>
            </li>
            <li data-role="collapsible" data-enhanced="true" data-collapsed-icon="carat-d" data-expanded-icon="carat-u" data-iconpos="right" data-inset="false" class="ui-collapsible ui-collapsible-themed-content ui-collapsible-collapsed">
                <h3 class="ui-collapsible-heading ui-collapsible-heading-collapsed">
                    <a href="#" class="ui-collapsible-heading-toggle ui-btn ui-btn-icon-right ui-btn-inherit ui-icon-carat-d">
                        Ciudades
                    </a>
                </h3>
                <div class="ui-collapsible-content ui-body-inherit ui-collapsible-content-collapsed" aria-hidden="true">
                    <ul>
                        <li data-filtertext="form checkboxradio widget checkbox input checkboxes controlgroups"><a href="${pageContext.servletContext.contextPath}/admin/cities/create" data-ajax="false">Crear</a></li>
                        <li data-filtertext="form checkboxradio widget radio input radio buttons controlgroups"><a href="${pageContext.servletContext.contextPath}/admin/cities" data-ajax="false">Buscar</a></li>
                    </ul>
                </div>
            </li>
        </c:if>
        <c:if test="${sessionScope.__user__.right1}">
            <li data-role="collapsible" data-enhanced="true" data-collapsed-icon="carat-d" data-expanded-icon="carat-u" data-iconpos="right" data-inset="false" class="ui-collapsible ui-collapsible-themed-content ui-collapsible-collapsed">
                <h3 class="ui-collapsible-heading ui-collapsible-heading-collapsed">
                    <a href="#" class="ui-collapsible-heading-toggle ui-btn ui-btn-icon-right ui-btn-inherit ui-icon-carat-d">
                        Anuncios
                    </a>
                </h3>
                <div class="ui-collapsible-content ui-body-inherit ui-collapsible-content-collapsed" aria-hidden="true">
                    <ul>
                        <li data-filtertext="form checkboxradio widget checkbox input checkboxes controlgroups"><a href="${pageContext.servletContext.contextPath}/admin/ads/create" data-ajax="false">Crear</a></li>
                        <li data-filtertext="form checkboxradio widget radio input radio buttons controlgroups"><a href="${pageContext.servletContext.contextPath}/admin/ads" data-ajax="false">Buscar</a></li>
                    </ul>
                </div>
            </li>
        </c:if>
        <li data-filtertext="Cerrar sesión" data-icon="lock"><a href="${pageContext.servletContext.contextPath}/admin/logout">Cerrar sesi&oacute;n ( ${sessionScope.__user__.username} )</a></li>
    </ul>
</div><!-- /panel -->
<div data-role="footer" data-position="fixed" data-tap-toggle="false" class="jqm-footer">
    <p>Tus anuncios 2.0</p>
    <p>Copyright 2015 - Nemesis</p>
</div><!-- /footer -->
</div><!-- /page -->
</body>
</html>
