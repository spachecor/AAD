const JUGADORES = {
	JUGADOR_1: '-1',
	EMPATE: '0',
	JUGADOR_2: '1'
};
let audioEncendido = true;
$(document).ready(function(){
	//audio de la pg
	cargaAudio();
	if($('#ganador').val()==JUGADORES.JUGADOR_1){
		//gana player1
		$('#player1').attr('src', 'img/personajes/ryu-win.png');
		$('#player2').attr('src', 'img/personajes/ken-lose.png');
		$('#resultado').attr('src', 'img/personajes/win-los-1.png');
	}else{
		//gana player2
		$('#player1').attr('src', 'img/personajes/ryu-lose.png');
		$('#player2').attr('src', 'img/personajes/ken-win.png');
		$('#resultado').attr('src', 'img/personajes/win-los-2.png');
	}
});
function cargaAudio() {
	const musica = $('#musica')[0];
	musica.play();
	$('#boton-audio').click(function(){
		audioEncendido ? musica.pause() : musica.play();
		audioEncendido = !audioEncendido;
	});
}