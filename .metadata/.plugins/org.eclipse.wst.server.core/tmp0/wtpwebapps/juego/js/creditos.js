$(document).ready(function(){
	//audio de la pg
	cargaAudio();
});
function cargaAudio() {
	let audioEncendido = true;
	const musica = $('#musica')[0];
	musica.play();
	$('#boton-audio').click(function(){
		audioEncendido ? musica.pause() : musica.play();
		audioEncendido = !audioEncendido;
	});
}