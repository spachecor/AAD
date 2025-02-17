package com.spachecor.ejemplobasex.ejerciciosuno;

import org.basex.api.client.ClientSession;

public class EjercicioCinco {
    public static void main(String[] args) {
        try{
            ClientSession clientSession = new ClientSession("localhost", 1984, "goku", "goku");
            // Abrir la base de datos antes de ejecutar la consulta
            clientSession.execute("OPEN dragonball-db");
            /*
             * Ejercicio 5: Reducir a la mitad el número de habitantes de Namek: Actualizar el número de habitantes
             * del planeta Namek dividiéndolo entre 2.
             * Pista: Usa replace value of node con una operación matemática para modificar el valor.
             * */
            String updateQuery = "for $h in //planetas/planeta[nombre='Namek']/habitantes return replace value of node $h with $h div 2";
            clientSession.execute("XQUERY "+updateQuery);

            String consultaQuery = "//planetas";
            String result = clientSession.execute("XQUERY "+consultaQuery);
            System.out.println(result);

            // Cerrar la sesión
            clientSession.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
