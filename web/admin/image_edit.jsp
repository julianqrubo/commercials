<%@include file="templates/header.jsp" %>
<h1>${_title_}</h1>

<h3>Se recomienda utilizar im&aacute;genes de 1090 X 500 pixeles</h3>
<form action="${pageContext.servletContext.contextPath}/admin/images" method="post" >

    <input type="hidden" id="id" name="id" value="${id}"/>
    <input type="hidden" name="path" id="path" value="${_entity_.path}" req="true"  />

    <label for="name">Nombre:</label>
    <input type="text" name="name" id="name" value="${_entity_.name}" req="true"  />

    <label for="file">Archivo:</label>
    <div class="image-file">
        <span class="btn btn-success fileinput-button">
            <i class="glyphicon glyphicon-plus"></i>
            <span>Seleccione el archivo...</span>
            <!-- The file input field used as target for the file upload widget -->
            <input id="fileupload" type="file" name="fileupload" data-url="${pageContext.servletContext.contextPath}/admin/upload" path="path">
        </span>
        <div style="height: 10px;">&nbsp;</div>
        <div id="progress" class="progress">
            <div class="progress-bar progress-bar-success"></div>
        </div>
        <!-- The container for the uploaded files -->
        <div id="files" class="files"></div>
    </div>

    <label for="state">Es principal:</label>
    <select id="ismain" name="ismain" data-role="flipswitch" data-wrapper-class="custom-size-flipswitch">
        <option value="0" ${ _entity_.ismain == 0 ? "selected='true'" : "" }>No</option>
        <option value="1" ${ _entity_.ismain == 1 ? "selected='true'" : "" }>Si</option>
    </select>

    <input type="submit" value="Guardar" />
    <c:if test="${not empty id}">
        <input type="button" value="Eliminar" id="delete_country" />
    </c:if>
</form>


<%@include file="templates/footer.jsp" %>