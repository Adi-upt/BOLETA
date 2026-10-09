<%-- 
    Document   : add
    Created on : 1 oct. 2026, 18:04:31
    Author     : Hpuser
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Cargo</title>
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
            <h1>Agregar Cargo</h1>
            <form action="ControladorCargo" method="post">
                <label for="txtIdcargo">Idcargo:</label>
                <input type="text" id="txtIdcargo" name="txtIdcargo" maxlength="5" required>
                <label for="txtNombre">Nombre:</label>
                <input type="text" id="txtNombre" name="txtNombre" maxlength="20" required>

                <label for="txtDescripcion">Descripción:</label>
                <textarea id="txtDescripcion" name="txtDescripcion" rows="3" maxlength="100"></textarea>

                <div class="acciones">
                    <input type="hidden" name="accion" value="Agregar">
                    <button type="submit" class="btn azul">Guardar</button>
                    <a class="btn gris" href="ControladorCargo?accion=listar">Regresar</a>
                </div>
            </form>
        </div>
    </body>
</html>
