<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SinCtrlAltF4</title>
    <link rel="shortcut icon" href="img/logo.png">
    <!-- Estilos y funcionalidad personalizada -->
    <s:include value="includes/includes.jsp"/>
    <link rel="stylesheet" href="css/final.css">
    <script src="js/final.js" defer></script>
</head>
<body>
	<audio id="musica" src="audio/pantalla4.mp3" loop></audio>
	<button id="boton-audio"><img src="img/botones/musica.png" alt="Piedra" width="100px"></button>
    <div class="columna1">
		<div class="contenedor-filas">
			<div class="fila-jugador">
				<img src="img/personajes/ryu-win.png" id="player1" alt="player1" width="250px">
			</div>
			<div class="fila">
				 <img src="img/personajes/win-los-1.png" id="resultado" alt="versus" width="250px">
			</div>
			<div class="fila-jugador">
				<img src="img/personajes/ken-lose.png" id="player2" alt="player2" width="250px">
			</div>
		</div>
    </div>
    <div class="columna2">
        <form action="Final" method="post">
        	<input type="hidden" name="accion" value="jugar"/>
        	<button class="botones"><img src="img/botones/nuevojuego.png" alt="Piedra" width="100px"></button>
        </form>
        <form action="Final" method="post">
        	<input type="hidden" name="accion" value="salir"/>
            <button class="botones"><img src="img/botones/salir.png" alt="Piedra" width="100px"></button>
        </form>
    </div>
    <input type="hidden" id="ganador" name="ganador" value="<s:property value="#session.ganador" />"/>
</body>
</html>