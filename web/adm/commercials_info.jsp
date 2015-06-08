<%@include file="tpl/header.jsp" %>
    
<div id="top-header" class="color1">
    <span style="color: #fff; line-height: 30px; vertical-align: middle;">Información del anuncio</span>
</div>

<form action="${pageContext.servletContext.contextPath}/adm/commercials_info" method="post" id="form-data" style="margin-top: 60px;">
    <div class="color3">
        <div style="margin-bottom: 15px;">
            <div style="float: left; padding-top: 10px;">
                <span for="commercial_name">Nombre comercial:</span>
            </div>
            <div style="float: left; padding-top: 8px; padding-left: 10px;">
                <input type="text" id="commercial_name" name="commercial_name" placeholder="Nombre comercial" />
            </div>
            <div style="clear: both;"></div>
        </div>
        
        <div style="margin-bottom: 15px;">
            <div style="float: left; padding-top: 17px;">
                <span for="description">Descripción:</span>
            </div>
            <div style="float: left; padding-top: 8px; padding-left: 10px;">
                <textarea rows="2" cols="70"></textarea>
            </div>
            <div style="clear: both;"></div>
        </div>
        
        <div style="margin-bottom: 15px;">
            <div style="text-align: left; float: left; padding-top: 5px;">
                <span for="commercial_address">Dirección comercial:</span>
            </div>
            <div style="margin-bottom: 15px;">
                <div style="float: left; width: 20%; box-sizing: border-box; padding: 5px;">
                    <select id="commercial_address_nom1" name="commercial_address_nom1" required="" style="width: 100%;">
                        <option value=""></option><option value="AP">Apartamento</option><option value="AUTOP">Autopista</option><option value="AV">Avenida</option><option value="BL">Bloque</option><option value="BDG">Bodega</option><option value="BUL">Boulevar</option><option value="CL">Calle</option><option value="CR">Carrera</option><option value="CARRT">Carretera</option><option value="CS">Casa</option><option value="CASER">Caserío</option><option value="CC">Centro Comercial</option><option value="CE">Centro Empresarial</option><option value="CIR">Circunvalar</option><option value="CON">Conjunto</option><option value="CORREG">Corregimiento</option><option value="DG">Diagonal</option><option value="EDIF">Edificio</option><option value="ET">Etapa</option><option value="FCA">Finca</option><option value="HDA">Hacienda</option><option value="INT">Interior</option><option value="KM">Kilometro</option><option value="L">Local</option><option value="LT">Lote</option><option value="MZN">Manzana</option><option value="MOD">Modulo</option><option value="NTE">Norte</option><option value="NO">Número</option><option value="OCC">Occidente</option><option value="OF">Oficina</option><option value="PARC">Parcela</option><option value="PJ">Pasaje</option><option value="PT">Peatonal</option><option value="P">Piso</option><option value="PO">Portería</option><option value="PTO">Puesto</option><option value="SEC">Sector</option><option value="TR">Torre</option><option value="TV">Transversal</option><option value="URB">Urbanización</option><option value="VDA">Vereda</option><option value="VIA">Vía</option>
                    </select>
                </div>
                <div style="float: left; width: 20%; box-sizing: border-box; padding: 5px;">
                    <input type="text" id="commercial_address_txt1" name="commercial_address_txt1" value="" required="" style="width: 100%;">
                </div>
                <div style="float: left; width: 20%; box-sizing: border-box; padding: 5px;">
                    <select id="commercial_address_nom2" name="commercial_address_nom2" required="" style="width: 100%;">
                        <option value=""></option><option value="AP">Apartamento</option><option value="AUTOP">Autopista</option><option value="AV">Avenida</option><option value="BL">Bloque</option><option value="BDG">Bodega</option><option value="BUL">Boulevar</option><option value="CL">Calle</option><option value="CR">Carrera</option><option value="CARRT">Carretera</option><option value="CS">Casa</option><option value="CASER">Caserío</option><option value="CC">Centro Comercial</option><option value="CE">Centro Empresarial</option><option value="CIR">Circunvalar</option><option value="CON">Conjunto</option><option value="CORREG">Corregimiento</option><option value="DG">Diagonal</option><option value="EDIF">Edificio</option><option value="ET">Etapa</option><option value="FCA">Finca</option><option value="HDA">Hacienda</option><option value="INT">Interior</option><option value="KM">Kilometro</option><option value="L">Local</option><option value="LT">Lote</option><option value="MZN">Manzana</option><option value="MOD">Modulo</option><option value="NTE">Norte</option><option value="NO">Número</option><option value="OCC">Occidente</option><option value="OF">Oficina</option><option value="PARC">Parcela</option><option value="PJ">Pasaje</option><option value="PT">Peatonal</option><option value="P">Piso</option><option value="PO">Portería</option><option value="PTO">Puesto</option><option value="SEC">Sector</option><option value="TR">Torre</option><option value="TV">Transversal</option><option value="URB">Urbanización</option><option value="VDA">Vereda</option><option value="VIA">Vía</option>
                    </select>
                </div>
                <div style="float: left; width: 20%; box-sizing: border-box; padding: 5px;">
                    <input type="text" id="commercial_address_txt2" name="commercial_address_txt2" value="" required="" style="width: 100%;">
                </div>
                <div style="clear: both;"></div>
            </div>
        </div>
        
        <div style="margin-bottom: 15px;">
            <div style="text-align: left; float: left; padding-top: 10px;">
                <span for="city">Ciudad:</span>
            </div>
            <div style="float: left; padding-left: 10px;">
                <input type="text" id="city" name="city" placeholder="Ciudad" />
            </div>
            <div style="clear: both;"></div>
        </div>
        
        <div style="margin-bottom: 15px;">
            <div style="text-align: left; float: left; padding-top: 10px;">
                <span for="neighborhood">Barrio:</span>
            </div>
            <div style="float: left; padding-left: 10px; width: 60px;">
                <input type="text" id="neighborhood" name="neighborhood" placeholder="Barrio" />
            </div>
            <div style="clear: both;"></div>
        </div>
        
        <div style="margin-bottom: 15px;" id="phone_commercial">
            <div style="text-align: left; float: left; padding-top: 10px;">
                <span for="phone">Teléfono:</span>
            </div>
            <div style="float: left; padding-left: 10px; width: 60px;">
                <input type="text" id="phone" name="phone" placeholder="Teléfono" />
            </div>
            <div style="clear: both;"></div>
        </div>
        
        <div style="margin-bottom: 15px;" id="phone_commercial">
            <div style="text-align: left; float: left; padding-top: 10px;">
                <span for="email">Email:</span>
            </div>
            <div style="float: left; padding-left: 10px; width: 60px;">
                <input type="text" id="email" name="email" placeholder="Email" />
            </div>
            <div style="clear: both;"></div>
        </div>
        
        <div style="margin-bottom: 15px;" id="phone_commercial">
            <div style="text-align: left; float: left; padding-top: 10px;">
                <span for="web_page">Página Web:</span>
            </div>
            <div style="float: left; padding-left: 10px; width: 60px;">
                <input type="text" id="web_page" name="web_page" placeholder="URL" />
            </div>
            <div style="clear: both;"></div>
        </div>
        
        <div style="margin-bottom: 15px;" id="phone_commercial">
            <div style="text-align: left; float: left; padding-top: 10px;">
                <span for="facebook">Facebook:</span>
            </div>
            <div style="float: left; padding-left: 10px; width: 60px;">
                <input type="text" id="facebook" name="facebook" placeholder="URL del perfil" />
            </div>
            <div style="clear: both;"></div>
        </div>
        
        <div style="margin-bottom: 15px;" id="phone_commercial">
            <div style="text-align: left; float: left; padding-top: 10px;">
                <span for="linkedin">linkedin:</span>
            </div>
            <div style="float: left; padding-left: 10px; width: 60px;">
                <input type="text" id="linkedin" name="linkedin" placeholder="URL del perfil" />
            </div>
            <div style="clear: both;"></div>
        </div>
        
        <div style="margin-bottom: 15px;" id="phone_commercial">
            <div style="text-align: left; float: left; padding-top: 10px;">
                <span for="details">Más Información:</span>
            </div>
            <div style="float: left; padding-left: 10px; width: 60px;">
                <input type="text" id="details" name="details" placeholder="URL para más información" />
            </div>
            <div style="clear: both;"></div>
        </div>
        <div style="margin-top: 15px;">
            <input type="submit" value="Registrar">
        </div>
    </div>
</form>

<%@include file="tpl/footer.jsp" %>