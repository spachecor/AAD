$(document).ready(function(){
	//audio de la pg
	cargaAudio();
//cerrar ventana al salir
	$('#salir').click(function(){
		Swal.fire({
		  title: "Por favor, cierra esta pestaña manualmente.",
		  text: "¡Terrible!, muchos navegadores no dejan cerrar pestañas así como así, por lo que necesitamos que la cierres tú. ¡Gracias por jugar!",
		  icon: "warning",
		  confirmButtonText: "¡Voy!",
		  confirmButtonColor: "#FF0000"
		});
	});
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