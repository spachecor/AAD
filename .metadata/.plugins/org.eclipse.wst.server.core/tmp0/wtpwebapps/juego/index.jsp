<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SinCtrlAltF4</title>
    <link rel="shortcut icon" href="img/logo.png">
    <!-- Estilos y funcionalidad personalizada -->
    <s:include value="includes/includes.jsp"/>
    <link rel="stylesheet" href="css/index.css">
    <script src="js/index.js" defer></script>
</head>
<body>
	<audio id="musica" src="audio/pantalla1.mp3" loop></audio>
	<button id="boton-audio"><img src="img/botones/musica.png" alt="Piedra" width="100px"></button>
	<form action="Historial" method="post">
		<input type="hidden" name="accion" value="historial"/>
		<button id="botonHistorial"><img src="img/history/histor.png" alt="Piedra" width="100px"></button>
	</form>
	<div class="logo">
		<img src="img/logo/logo.png" alt="logo de street figther" width="500px">
    </div>
    <div class="opciones">
        <!--boton de 1vsmachine-->
        <form action="Entrar" method="post">
            <input type="hidden" name="accion" value="1vsMachine"/>
            <button class="botones"><img src="img/botones/1vscom.png" alt="Piedra" width="200px"></button>
        </form>
        <!--boton de 1vs1-->
        <form action="Entrar" method="post">
            <input type="hidden" name="accion" value="1vs1"/>
            <button class="botones"><img src="img/botones/1vs2.png" alt="Piedra" width="200px"></button>
        </form>
    </div>
    <div class="final">
        <button class="botones2 botones" id="salir"><img src="img/botones/salir.png" alt="Piedra" width="100px"></button>
        <form action="Creditos" method="post">
        	<button class="botones3 botones" id="creditos"><img src="img/botones/creditos.png" alt="Piedra" width="100px"></button>
        </form>
    </div>
</body>
</html>