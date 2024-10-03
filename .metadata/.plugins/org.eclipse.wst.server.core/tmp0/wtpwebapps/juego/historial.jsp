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
    <link rel="stylesheet" href="css/historial.css">
    <script src="js/historial.js" defer></script>
</head>
<body>
	<audio id="musica" src="audio/creditos.mp3" loop></audio>
	<button id="boton-audio"><img src="img/botones/musica.png" alt="Piedra" width="100px"></button>
	<form action="Historial" method="post">
		<input type="hidden" name="accion" value="salir"/>
		<button class="botones2 botones" id="salir"><img src="img/botones/salir.png" alt="Piedra" width="100px"></button>
	</form>
	<table>
		<thead>
			<tr>
				<td>
					<img src="img/history/player1.png" alt="player1" width="300px" height="37px" class="mx-4">
				</td>
				<td>
					<img src="img/history/player2.png" alt="player1" width="300px" height="37px" class="mx-4">
				</td>
				<td>
					<img src="img/history/time.png" alt="player1" width="150px" height="37px" class="mx-4">
				</td>
				<td>
					<img src="img/history/winner.png" alt="player1" width="300px" height="37px" class="mx-4">
				</td>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
</body>
</html>