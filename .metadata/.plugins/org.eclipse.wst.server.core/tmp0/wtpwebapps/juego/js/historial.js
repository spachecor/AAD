let noData = "NO DATA";
let audioEncendido = true;
$(document).ready(function(){
	cargaAudio();
    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: '/juego/Historial.action?accion=obtenerDatos',
        success: function(data){
			console.log(data)
			if(data==null||data.length==0){
				insertarDatos(noData, noData, noData, noData);
			}else{
				data.forEach(function(juego){
					const fecha = new Date(juego.hora);
					const fechaFormateada = fecha.toLocaleString("es-ES", {
						year: "numeric",
						month: "2-digit",
						day: "2-digit",
						hour: "2-digit",
						minute: "2-digit",
						second: "2-digit",
						hour12: false
					});
					insertarDatos(juego.jugadorUno.nombre, juego.jugadorDos.nombre, fechaFormateada,juego.ganador.nombre);
				});				
			}
        },
        error: function(jqXHR, textStatus, errorThrown){
			console.log(errorThrown)
			insertarDatos(noData, noData, noData, noData);
        }
    });
});
function insertarDatos(player1, player2, time, winner){
	const tbody = $('tbody');

	const tr = $('<tr></tr>');
	const tdPlayer1 = $('<td></td>');
	const pPlayer1 = $('<p class="jacquarda-bastarda-9-regular"></p>').text(player1);
	tdPlayer1.append(pPlayer1);

	const tdPlayer2 = $('<td></td>');
	const pPlayer2 = $('<p class="jacquarda-bastarda-9-regular"></p>').text(player2);
	tdPlayer2.append(pPlayer2);
			
	const tdTime = $('<td></td>');
	const pTime = $('<p class="jacquarda-bastarda-9-regular"></p>').text(time);
	tdTime.append(pTime);
					
	const tdWinner = $('<td></td>');
	const pWinner = $('<p class="jacquarda-bastarda-9-regular"></p>').text(winner);
	tdWinner.append(pWinner);
					
	tr.append(tdPlayer1).append(tdPlayer2).append(tdTime).append(tdWinner);
	tbody.append(tr);
}
function cargaAudio() {
	const musica = $('#musica')[0];
	musica.play();
	$('#boton-audio').click(function(){
		audioEncendido ? musica.pause() : musica.play();
		audioEncendido = !audioEncendido;
	});
}