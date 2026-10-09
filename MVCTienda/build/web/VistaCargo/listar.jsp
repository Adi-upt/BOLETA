<%-- 
    Document   : listar
    Created on : 1 oct. 2026, 18:04:46
    Author     : Hpuser
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.clsCargo"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Cargos</title>
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
            <h1>Listado de Cargos</h1>
            <a class="btn verde" href="ControladorCargo?accion=add">+ Nuevo Cargo</a>
            <a class="btn gris" href="index.jsp">Inicio</a>

            <% String error = (String) request.getAttribute("error");
               if (error != null) { %>
                <div style="margin-top:15px;padding:12px;background:#fdecea;color:#a12622;border:1px solid #f5c2c0;border-radius:4px;">
                    <b>Error:</b> <%= error %>
                </div>
            <% } %>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>NOMBRE</th>
                        <th>DESCRIPCIÓN</th>
                        <th>ACCIONES</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    List<clsCargo> cargos = (List<clsCargo>) request.getAttribute("cargos");
                    if (cargos == null || cargos.isEmpty()) {
                %>
                    <tr><td colspan="4" style="text-align:center;">No hay cargos registrados.</td></tr>
                <%
                    } else {
                        for (clsCargo c : cargos) {
                %>
                    <tr>
                        <td><%= c.getIdCargo() %></td>
                        <td><%= c.getNombre() %></td>
                        <td><%= c.getDescripcion() == null ? "" : c.getDescripcion() %></td>
                        <td>
                            <a class="btn amarillo" href="ControladorCargo?accion=editar&id=<%= c.getIdCargo() %>">Editar</a>
                            <a class="btn rojo" href="ControladorCargo?accion=eliminar&id=<%= c.getIdCargo() %>"
                               onclick="return confirm('¿Seguro que desea eliminar el cargo <%= c.getNombre() %>?');">Eliminar</a>
                        </td>
                    </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
    </body>
</html>
