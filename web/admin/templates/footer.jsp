</div><!-- /content -->
<div data-role="panel" class="jqm-navmenu-panel" data-position="left" data-display="overlay" data-theme="a">
    <ul class="jqm-list ui-alt-icon ui-nodisc-icon">
        <li data-filtertext="demos homepage" data-icon="home"><a href="${pageContext.servletContext.contextPath}/admin">Inicio</a></li>
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
                    Anuncios
                </a>
            </h3>
            <div class="ui-collapsible-content ui-body-inherit ui-collapsible-content-collapsed" aria-hidden="true">
                <ul>
                    <li data-filtertext="form checkboxradio widget checkbox input checkboxes controlgroups"><a href="${pageContext.servletContext.contextPath}/admin/edit_ad" data-ajax="false">Crear</a></li>
                    <li data-filtertext="form checkboxradio widget radio input radio buttons controlgroups"><a href="${pageContext.servletContext.contextPath}/admin/ads" data-ajax="false">Buscar</a></li>
                </ul>
            </div>
        </li>
    </ul>
</div><!-- /panel -->
<div data-role="footer" data-position="fixed" data-tap-toggle="false" class="jqm-footer">
    <p>Tus anuncios 2.0</p>
    <p>Copyright 2014 - Nemesis</p>
</div><!-- /footer -->
</div><!-- /page -->
</body>
</html>
