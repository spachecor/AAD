//agregamos los fondos aleatorios dentro de un objeto vacío con ayuda de un bucle for
const fondos = {};
for (let i = 1; i <= 10; i++) {
	fondos[i] = `img/fondos/${i}.gif`;
}
//usamos el objeto OPCIONES para asociar las diferentes opciones a los valores 0, 1 y 2
const OPCIONES = {
	PIEDRA: '0',
	PAPEL: '1',
	TIJERA: '2'
};
//usamos el objeto JUGADORES para mapear a los jugadores o el empate, usado para declarar el ganador
const JUGADORES = {
	JUGADOR_1: '-1',
	EMPATE: '0',
	JUGADOR_2: '1'
};
//comprobamos y almacenamos si jugamos contra la maquina
const esMaquina = $('#nombrePlayer2').text() === 'Machine';
const vidaJugador1Dom = $('#vidaIzq');
const vidaJugador2Dom = $('#vidaDcha');


const piedraJugador1 = $('#piedra1');
const papelJugador1 = $('#papel1');
const tijeraJugador1 = $('#tijera1');

const piedraJugador2 = $('#piedra2');
const papelJugador2 = $('#papel2');
const tijeraJugador2 = $('#tijera2');

const temporizador = $('#temporizador');
//predeterminadamente el audio estará encendido
let audioEncendido = true;

window.addEventListener('load', async () => {
	cargaAudio();
	cargarFondo();
	let hayGanador = false;
	let resultadoJugador1, resultadoJugador2;
	while (!hayGanador) {
		// Jugador 1
		resultadoJugador1 = await escogerResultado(false);
		piedraJugador1.attr('src', 'img/botones/piedra-f-des.png');
		papelJugador1.attr('src', 'img/botones/papel-f-des.png');
		tijeraJugador1.attr('src', 'img/botones/tijeras-f-des.png');

		piedraJugador2.attr('src', 'img/botones/piedra-f.png');
		papelJugador2.attr('src', 'img/botones/papel-f.png');
		tijeraJugador2.attr('src', 'img/botones/tijeras-f.png');

		temporizador.attr('src', 'img/vida/tiempo.gif');
		
		// Jugador 2
		resultadoJugador2 = await escogerResultado(esMaquina);
		piedraJugador2.attr('src', 'img/botones/piedra-f-des.png');
		papelJugador2.attr('src', 'img/botones/papel-f-des.png');
		tijeraJugador2.attr('src', 'img/botones/tijeras-f-des.png');

		temporizador.attr('src', 'img/vida/tiempoFin.png');
		await comprobarGanador(resultadoJugador1, resultadoJugador2);
		piedraJugador1.attr('src', 'img/botones/piedra-f.png');
		papelJugador1.attr('src', 'img/botones/papel-f.png');
		tijeraJugador1.attr('src', 'img/botones/tijeras-f.png');

		temporizador.attr('src', 'img/vida/tiempo.gif');

		$('#imgPlayer1').attr('src', 'img/movimientos/ryu-parado.gif');
		$('#imgPlayer2').attr('src', 'img/movimientos/ken-parado.gif');
	}
});
/**
 * Funcion encargada de encender el audio y de gestionar el boton de encendido-apagado de la musica
 */
function cargaAudio() {
	const musica = $('#musica')[0];
	musica.play();
	$('#boton-audio').click(function(){
		audioEncendido ? musica.pause() : musica.play();
		audioEncendido = !audioEncendido;
	});
}
/**
 * Funcion que se encarga de cargar un fondo de partida aleatorio usando el objeto de fondos
 */
function cargarFondo() {
	$('body').css('background-image', `url(${fondos[(Math.floor(Math.random() * 10) + 1)]})`);
}
/**
 * Funcion asíncrona que devuelve una promesa. Se encarga de escoger un resultado, asignando uno aleatorio si el
 * jugador 2 es una máquina(para éste) y si pasan 15 segundos y no se ha escogido
 */
async function escogerResultado(esMaquina) {
	//devolvemos una promesa que será resuelta dentro del cuerpo de la funcion
	//resolve es una funcion a la que llamamos para devolver un valor y finalizar la promesa
	return await new Promise(resolve => {
		//establecemos la cuenta atrás, 2 seg para la maquina y 15 para el humano
		setTimeout(() => {
			//se llama con una opcion aleatoria el valor de la jugada. Object.Values(OPCIONES) devuelve un array con los valores de las opciones
			resolve(Object.values(OPCIONES)[Math.floor(Math.random() * 2)]);
			//con resolve() estamos resolviendo la promesa
		}, esMaquina ? 2000 : 15000);
		//vetamos que solo se pueda escoger opción si no eres maquina
		if (!esMaquina) {
			document.addEventListener('keydown', e => {
				const teclaPulsadaAOpcion = {
					'1': OPCIONES.PIEDRA,
					'2': OPCIONES.PAPEL,
					'3': OPCIONES.TIJERA
				};
				resolve(teclaPulsadaAOpcion[e.key]);
				//con resolve() estamos resolviendo la promesa
			});
		}
	})
}
/**
 * Funcion que cambia las imagenes de las barras de vida de ambos jugadores según su nº de vidas restantes
 */
function ajustarBarrasVida(vidaJugador1, vidaJugador2) {
	//metemos las posibles barras en arrays
	const imagenesVidaPlayer1 = [
		'barra_izq4-4',
		'barra_izq3-4',
		'barra_izq2-4',
		'barra_izq1-4'
	];
	const imagenesVidaPlayer2 = [
		'barra_der4-4',
		'barra_der3-4',
		'barra_der2-4',
		'barra_der1-4'
	];
	//asignamos las barras según las vidas de los jugadores
	vidaJugador1Dom.attr('src', `img/vida/${imagenesVidaPlayer1[vidaJugador1]}.png`)
	vidaJugador2Dom.attr('src', `img/vida/${imagenesVidaPlayer2[vidaJugador2]}.png`)
}
/**
 * Funcion que comprueba, entre ambos jugadores, y segun su opcion elegida, quien gana el turno. Si algun jugador
 * se queda sin vidas, el contrincante será elegido como ganador y termina el juego
 */
async function comprobarGanador(resultadoJugador1, resultadoJugador2) {
	//llamada al servidor, hacemos una solicitud http enviando el resultado de ambos jugadores.
	//await indica que hay que esperar a que el servidor responda
	await fetch('/juego/Juego.action?accion=jugada&opcionPlayer1=' + resultadoJugador1 + '&opcionPlayer2=' + resultadoJugador2, {
		method: 'GET',
	})
	//cuando la solicitud "fetch" es completada, se gestiona la respuesta del servidor(en este caso un json)
	.then(async response => {
		//usamos dos objetos para almacenar los posibles movimientos de los jugadores
		const imagenesMovimientosJugador1 = {
			[OPCIONES.PIEDRA]: 'ryu-piedra',
			[OPCIONES.PAPEL]: 'ryu-papel',
			[OPCIONES.TIJERA]: 'ryu-tijeras'
		};

		const imagenesMovimientosJugador2 = {
			[OPCIONES.PIEDRA]: 'ken-piedra',
			[OPCIONES.PAPEL]: 'ken-papel',
			[OPCIONES.TIJERA]: 'ken-tijeras'
		};
		//asignamos el movimiento que tendrán según la respuesta del servidor
		$('#imgPlayer1').attr('src', `img/movimientos/${imagenesMovimientosJugador1[resultadoJugador1]}.gif`);
		$('#imgPlayer2').attr('src', `img/movimientos/${imagenesMovimientosJugador2[resultadoJugador2]}.gif`);
		//hacemos una pausa de 4 segundos para que se puedan apreciar correctamente las animaciones de los ataques.
		//se utiliza una promesa, que se resolverá cuando terminen los 4 seg
		await new Promise(resolve => setTimeout(resolve, 4000));
		//recogemos la respuesta en forma de json
		const data = await response.json();
		if (data) {
			const vidaJugador1 = data.juego.jugadorUno.vidas;
			const vidaJugador2 = data.juego.jugadorDos.vidas;
			ajustarBarrasVida(vidaJugador1, vidaJugador2);
			let jugadorGanador;
			if (vidaJugador1 === 0) {
				jugadorGanador = JUGADORES.JUGADOR_2;
				asignarGanador(jugadorGanador);
			} else if (vidaJugador2 === 0) {
				jugadorGanador = JUGADORES.JUGADOR_1;
				asignarGanador(jugadorGanador);
			}
		}
	});
}
/**
 * Funcion que asigna el jugador vencedor en el back y que redirige a la pg del final
 */
function asignarGanador(jugadorVencedor){
	$.ajax({
		type: 'GET',
		url: '/juego/Juego.action?accion=asignarGanador&nombreGanador=' + jugadorVencedor,
		success: function(){
			window.location.href = 'http://localhost:8080/juego/Final';
		}
	});
}