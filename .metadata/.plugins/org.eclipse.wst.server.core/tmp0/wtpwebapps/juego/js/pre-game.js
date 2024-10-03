let audioEncendido = true;
$(document).ready(function(){
	//audio de la pg
	cargaAudio();	
	if($('#opcionJuego').val()==="1vsMachine"){
		$('#player2').attr('disabled', 'true');
		$('#player2').val("Machine");
	}
	//interceptar el formulario para que no haya valores nullos o vacíos
	$('#nombreJugadoresForm').on('submit', function(event){
		const form = event.currentTarget;
		//detenemos el comportamiento
		event.preventDefault();
		let player1Correcto = $('#player1').val()!=null&&$('#player1').val().length>0;
		let player2Correcta = $('#player2').val()!=null&&$('#player2').val().length>0;
		if(player1Correcto&&player2Correcta){
			event.currentTarget.submit();
		}else{
			Swal.fire({
				title: "¡Terrible!",
				text: "Por favor, rellene todos los campos",
				icon: "error",
				confirmButtonText: "¡Voy!",
				confirmButtonColor: "#FF0000"
			});
		}
	});
});
function cargaAudio() {
	const musica = $('#musica')[0];
	musica.play();
	$('#boton-audio').click(function(){
		audioEncendido ? musica.pause() : musica.play();
		audioEncendido = !audioEncendido;
	});
}