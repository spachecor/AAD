$(document).ready(function(){
	//audio de la pg
	let audioEncendido = false;
	const audio = $('<audio></audio>');
	audio.attr('src', 'audio/pantalla1.mp3');
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
//cerrar ventana al salir
	$('#salir').click(function(){
		Swal.fire({
		  title: "Por favor, cierra esta pestaña manualmente.",
		  text: "Desgraciadamente, muchos navegadores no dejan cerrar pestañas así como así, por lo que necesitamos que la cierres tú. ¡Gracias por jugar!",
		  icon: "warning",
		  confirmButtonText: "¡Voy!",
		  confirmButtonColor: "#FF0000"
		});
	});
});