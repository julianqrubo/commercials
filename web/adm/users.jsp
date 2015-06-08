<%@include file="tpl/header.jsp" %>
    
<div id="top-header" class="color1">
    <span style="color: #fff; line-height: 30px; vertical-align: middle;">Registro de usuario</span>
</div>

<form action="${pageContext.servletContext.contextPath}/adm/users" method="post" id="form-data">
    <div class="color3">
        <td>
            <div style="margin-bottom: 20px;">
                <div style="text-align: left;">
                    <label for="name">Nombre</label>
                </div>
                <input type="text" id="name" name="name" placeholder="Nombre" />
            </div>
        </td>
        <div style="margin-bottom: 20px;">
            <div style="text-align: left;">
                <label for="surname">Apellido</label>
            </div>
            <input type="text" id="surname" name="surname" placeholder="Apellido" />
        </div>
        <div style="margin-bottom: 20px;">
            <div style="text-align: left;">
                <label for="email">Email</label>
            </div>
            <input type="text" id="email" name="email" placeholder="Email" />
        </div>
        <div style="margin-bottom: 20px;">
            <div style="text-align: left;">
                <label for="user">Usuario</label>
            </div>
            <input type="text" id="user" name="user" placeholder="Usuario" />
        </div>
        <div style="margin-bottom: 20px;">
            <div style="text-align: left;">
                <label for="password">Contrase&ntilde;a:</label>
            </div>
            <input type="password" id="password" name="password" placeholder="Contrase&ntilde;a" />
        </div>
        <div style="margin-bottom: 20px;">
            <div style="text-align: left;">
                <label for="confirm_password">Confirmar contrase&ntilde;a:</label>
            </div>
            <input type="password" id="confirm_password" name="confirm_password" placeholder="Confirmar contrase&ntilde;a" />
        </div>
        <div style="margin-top: 20px;">
            <input type="submit" value="Registrar">
        </div>
    </div>
</form>

<%@include file="tpl/footer.jsp" %>