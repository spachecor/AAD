<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>¡Ponle nombre al personaje!</title>
    <link rel="shortcut icon" href="img/logo.png">
	<!-- Estilo y funcionalidad personalizados -->
    <s:include value="includes/includes.jsp"/>
    <link rel="stylesheet" href="css/pre-game.css">
    <script src="js/pre-game.js" defer></script>
</head>
<body>
	<audio id="musica" src="audio/pantalla2.mp3" loop></audio>
	<button id="boton-audio"><img src="img/botones/musica.png" alt="Piedra" width="100px"></button>
    <div class="columna1">
        <form action="Jugar" method="post" id="nombreJugadoresForm">
            <div class="contenedor-filas">
                <div class="fila-jugador">
                    <img src="img/personajes/ryu-win.png" alt="player1" width="250px">
                    <input type="text" name="player1" id="player1">
                </div>
                <div class="fila">
                    <img src="img/personajes/vs.png" alt="versus" width="250px">
                </div>
                <div class="fila-jugador">
                    <img src="img/personajes/ken-win.png" alt="player2" width="250px">
                    <input type="text" name="player2" id="player2">
                </div>
            </div>
            <button class="botones"><img src="img/botones/jugar.png" alt="Piedra" width="100px"></button>
        </form>
    </div>
    <div class="columna2">
        <form action="Jugar" method="post">
        	<input type="hidden" name="accion" value="salir"/>
        	<button class="botones"><img src="img/botones/salir.png" alt="Piedra" width="100px"></button>
        </form>
    </div>
    <input type="hidden" id="opcionJuego" name="opcionJuego" value="<s:property value="#session.opcionJuego"/>"/>
</body>
</html>