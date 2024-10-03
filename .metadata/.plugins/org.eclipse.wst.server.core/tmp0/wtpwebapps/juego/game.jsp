<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>¡Piedra, Papel o Tijera!</title>
    <link rel="shortcut icon" href="img/logo.png">
    <!--Estilos y funcionalidad personalizado-->
    <s:include value="includes/includes.jsp"/>
    <link rel="stylesheet" href="css/game.css">
    <script src="js/game.js" defer></script>
</head>
<body>
	<audio id="musica" src="audio/pantalla3.mp3" loop></audio>
	<button id="boton-audio"><img src="img/botones/musica.png" alt="Piedra" width="100px"></button>
    <div class="nombres">
        <p class="jacquarda-bastarda-9-regular"><b><s:property value="#session.player1" /></b></p>
        <p class="jacquarda-bastarda-9-regular" id="nombrePlayer2"><b><s:property value="#session.player2" /></b></p>
    </div>
    <div class="barra-vida">
        <img src="img/vida/barra_izq1-4.png" height="30px" id="vidaIzq">
        <img src="img/vida/ko-inicio.png" width="70px">
        <img src="img/vida/barra_der1-4.png" height="30px" id="vidaDcha">
    </div>
    <div class="tiempo">
        <img src="img/vida/tiempo.gif" id="temporizador" height="30px">
    </div>
    <div class="juego">
        <div class="botones1">
            <img src="img/botones/piedra-f.png" id="piedra1" alt="Piedra" width="75px">
            <img src="img/botones/papel-f.png" id="papel1" alt="Papel" width="75px">
            <img src="img/botones/tijeras-f.png" id="tijera1" alt="Tijeras" width="75px">
        </div>

        <div class="botones2">
            <img src="img/botones/piedra-f-des.png" id="piedra2" alt="Piedra" width="75px">
            <img src="img/botones/papel-f-des.png" id="papel2" alt="Papel" width="75px">
            <img src="img/botones/tijeras-f-des.png" id="tijera2" alt="Tijeras" width="75px">
        </div>

        <div class="jugador1">
            <img src="img/movimientos/ryu-parado.gif" width="500px" id="imgPlayer1">
        </div>
        <div class="jugador2">
            <img src="img/movimientos/ken-parado.gif" width="500px" id="imgPlayer2">
        </div>
    </div>
    <input type="hidden" id="vidasplayer1" name="vidasplayer1" value="<s:property value="#session.vidasplayer1"/>"/>
    <input type="hidden" id="vidasplayer2" name="vidasplayer2" value="<s:property value="#session.vidasplayer2"/>"/>
</body>
</html>