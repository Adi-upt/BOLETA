<%-- 
    Document   : edit
    Created on : 1 oct. 2026, 18:04:38
    Author     : Hpuser
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.clsCargo"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Cargo</title>
        <style>
            body{font-family:Segoe UI,Arial,sans-serif;background:#f4f6f9;margin:0;padding:30px;}
            .caja{max-width:850px;margin:auto;background:#fff;padding:25px;border-radius:8px;box-shadow:0 2px 8px rgba(0,0,0,.1);}
            h1{color:#1f3c88;margin-top:0;}
            table{width:100%;border-collapse:collapse;margin-top:15px;}
            th{background:#1f3c88;color:#fff;padding:10px;text-align:left;}
            td{padding:9px;border-bottom:1px solid #ddd;}
            tr:hover td{background:#eef3ff;}
            label{display:block;margin-top:12px;font-weight:bold;}
            input[type=text],textarea{width:100%;padding:8px;margin-top:4px;border:1px solid #bbb;border-radius:4px;box-sizing:border-box;}
            .btn{display:inline-block;padding:7px 14px;border-radius:4px;color:#fff;text-decoration:none;border:none;cursor:pointer;font-size:14px;}
            .azul{background:#1f3c88;} .verde{background:#2e8b57;} .amarillo{background:#d39e00;} .rojo{background:#c0392b;} .gris{background:#777;}
            .acciones{margin-top:18px;}
        </style>
    </head>
    <body>
        <div class="caja">
            <h1>Editar Cargo</h1>
            <%
                clsCargo c = (clsCargo) request.getAttribute("cargo");
                if (c == null) {
            %>
                <p>No se encontró el cargo solicitado.</p>
                <a class="btn gris" href="ControladorCargo?accion=listar">Regresar</a>
            <%
                } else {
                    String nombre = c.getNombre() == null ? "" : c.getNombre().replace("\"", "&quot;");
                    String descripcion = c.getDescripcion() == null ? "" : c.getDescripcion();
            %>
            <form action="ControladorCargo" method="post">
                <label for="txtId">ID:</label>
                <input type="text" id="txtId" name="txtId" value="<%= c.getIdCargo() %>" readonly>

                <label for="txtNombre">Nombre:</label>
                <input type="text" id="txtNombre" name="txtNombre" value="<%= nombre %>" maxlength="20" required>

                <label for="txtDescripcion">Descripción:</label>
                <textarea id="txtDescripcion" name="txtDescripcion" rows="3" maxlength="100"><%= descripcion %></textarea>

                <div class="acciones">
                    <input type="hidden" name="accion" value="Actualizar">
                    <button type="submit" class="btn azul">Actualizar</button>
                    <a class="btn gris" href="ControladorCargo?accion=listar">Regresar</a>
                </div>
            </form>
            <%
                }
            %>
        </div>
    </body>
</html>
