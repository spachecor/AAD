//fondos aleatorios
let fondos = new Map();
fondos.set(1, 'img/fondos/1.gif');
fondos.set(2, 'img/fondos/2.gif');
fondos.set(3, 'img/fondos/3.gif');
fondos.set(4, 'img/fondos/4.gif');
fondos.set(5, 'img/fondos/5.gif');
fondos.set(6, 'img/fondos/6.gif');
fondos.set(7, 'img/fondos/7.gif');
fondos.set(8, 'img/fondos/8.gif');
fondos.set(9, 'img/fondos/9.gif');
fondos.set(10, 'img/fondos/10.gif');

//recogemos la opcion del primero
let opciones = [];
let aux = 0;
let auxOpcion = null;
let auxPrimerTurno = true;
let aniadido = false;
const esMaquina = $('#nombrePlayer2').text() === 'Machine';

$(document).ready(function(){
	//audio de la pg
	let audioEncendido = false;
	const audio = $('<audio></audio>');
	audio.attr('src', 'audio/pantalla3.mp3');
	audio.attr('loop', 'loop');
	$('body').append(audio);
	$('#boton-audio').click(function(){
		if(!audioEncendido){
			audio[0].play();
			audioEncendido=true;
		}else{
			audio[0].pause();
			audioEncendido=false;
		}
	});
	// El fondo será aleatorio
	let srcFondo = fondos.get(Math.floor(Math.random() * 10) + 1); // Ajuste para incluir el 10
	$('body').css('background-image', 'url(' + srcFondo + ')');

	// Ajustamos las barras de vida según la vida de los participantes
	ajustarBarrasVida();

	// Solo recogemos la opcion si no estaba añadida
	$(document).keydown(function(e){
		switch(e.which) {
			case 49:
				if(!aniadido){
					auxOpcion = 0;
					aniadido = true;
				}
				break;
			case 50:
				if(!aniadido){
					auxOpcion = 1;
					aniadido = true;
				}
				break;
			case 51:
				if(!aniadido){
					auxOpcion = 2;
					aniadido = true;
				}
				break;
		}
	})

	// Comprobamos la opcion del jugador
	comprobarOpcionElegida();
});

/**
 * Función que revisa cada medio segundo si se ha elegido una opcion.
 */
function comprobarOpcionElegida(){
	let intervalo = setInterval(function(){
		// Si se añadió una opción, la registramos y continuamos con el flujo
		if(aniadido){
			if (esMaquina) {
				$(document).off('keydown');
			}
			aniadido = false;
			opciones.push(auxOpcion);
			auxOpcion = null;
			clearInterval(intervalo);
			aux=0;
			finTurno();
		// Si no se añadió nada, se asigna un valor aleatorio después del tiempo límite
		} else if (aux === 30 || (!auxPrimerTurno && aux === 4 && esMaquina)) {
			// Si no se ha asignado ninguna opción, se asigna una opción aleatoria (0, 1 o 2)
			if(auxOpcion === null) {
				auxOpcion = Math.floor(Math.random() * 3);
				opciones.push(auxOpcion);
				auxOpcion = null;
				aniadido = false;
			}
			clearInterval(intervalo);
			aux=0;
			finTurno();
		}
		aux++;
	}, 500);
}

function finTurno(){
	if(auxPrimerTurno){
		$('#piedra1').attr('src', 'img/botones/piedra-f-des.png');
		$('#papel1').attr('src', 'img/botones/papel-f-des.png');
		$('#tijera1').attr('src', 'img/botones/tijeras-f-des.png');
		
		$('#piedra2').attr('src', 'img/botones/piedra-f.png');
		$('#papel2').attr('src', 'img/botones/papel-f.png');
		$('#tijera2').attr('src', 'img/botones/tijeras-f.png');
		
		$('#temporizador').attr('src', 'img/vida/tiempo.gif');
		// La 1º vez que entramos, ponemos 1º turno a false, para que se sepa que ya se ha realizado la tirada del primer turno
		if(auxPrimerTurno) auxPrimerTurno = false;
		// Una vez cambiados los estilos, llamamos a volver a elegir opción
		comprobarOpcionElegida();
	} else {
		$('#piedra2').attr('src', 'img/botones/piedra-f-des.png');
		$('#papel2').attr('src', 'img/botones/papel-f-des.png');
		$('#tijera2').attr('src', 'img/botones/tijeras-f-des.png');
		$('#temporizador').attr('src', 'img/vida/tiempoFin.png');
		resolverJuego();
	}
}

function resolverJuego(){
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: '/juego/Juego.action?accion=jugada&opcionPlayer1=' + opciones[0] + '&opcionPlayer2=' + opciones[1],
		success: function(data){
			let src1 = null;
			let src2 = null;
			switch (opciones[0]){
				case 0:
					src1 = 'img/movimientos/ryu-piedra.gif';
					break;
				case 1:
					src1 = 'img/movimientos/ryu-papel.gif';
					break;
				case 2:
					src1 = 'img/movimientos/ryu-tijeras.gif';
			}
			switch (opciones[1]){
				case 0:
					src2 = 'img/movimientos/ken-piedra.gif';
					break;
				case 1:
					src2 = 'img/movimientos/ken-papel.gif';
					break;
				case 2:
					src2 = 'img/movimientos/ken-tijeras.gif';
			}
			$('#imgPlayer1').attr('src', src1);
			$('#imgPlayer2').attr('src', src2);
			if(data != null){
				setTimeout(function(){
					comprobarVidas(data);
				}, 4000);
			} else {
				setTimeout(function(){
					window.location.reload();
				}, 4000);
			}
		}
	});
}

function comprobarVidas(data){
	let vidasPlayer1 = data.juego.jugadorUno.vidas;
	let vidasPlayer2 = data.juego.jugadorDos.vidas;
	if(vidasPlayer1 === 0) asignarGanador("player2", "player1");
	else if(vidasPlayer2 === 0) asignarGanador("player1", "player2");
	else window.location.reload();
}

function asignarGanador(jugadorVencedor, jugadorPerdedor){
	$.ajax({
		type: 'GET',
		url: '/juego/Juego.action?accion=asignarGanador&nombreGanador=' + jugadorVencedor + '&nombrePerdedor=' + jugadorPerdedor,
		success: function(){
			window.location.href = 'http://localhost:8080/juego/Final';
		}
	});
}

function ajustarBarrasVida(){
	switch($('#vidasplayer1').val()){
		case "0":
			$('#vidaIzq').attr('src', 'img/vida/barra_izq4-4.png');
			break;
		case "1":
			$('#vidaIzq').attr('src', 'img/vida/barra_izq3-4.png');
			break;
		case "2":
			$('#vidaIzq').attr('src', 'img/vida/barra_izq2-4.png');
			break;
		case "3":
			$('#vidaIzq').attr('src', 'img/vida/barra_izq1-4.png');
			break;
	}
	switch($('#vidasplayer2').val()){
		case "0":
			$('#vidaDcha').attr('src', 'img/vida/barra_der4-4.png');
			break;
		case "1":
			$('#vidaDcha').attr('src', 'img/vida/barra_der3-4.png');
			break;
		case "2":
			$('#vidaDcha').attr('src', 'img/vida/barra_der2-4.png');
			break;
		case "3":
			$('#vidaDcha').attr('src', 'img/vida/barra_der1-4.png');
			break;
	}
}
