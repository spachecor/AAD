package com.spachecor.ejemplobasex.ejerciciosuno;

import org.basex.api.client.ClientSession;

public class EjercicioUno {
    public static void main(String[] args) {
        try{
            ClientSession clientSession = new ClientSession("localhost", 1984, "goku", "goku");
            // Abrir la base de datos antes de ejecutar la consulta
            clientSession.execute("OPEN dragonball-db");
            /*
            * Ejercicio 1: Aumentar el nivel de poder de todos los Saiyajin: Incrementar en 500 unidades el nivel de
            * poder de todos los personajes cuya raza sea Saiyajin.
            * Pista: Utiliza replace value of node con una operación matemática sobre el valor actual.
            * */
            String updateQuery = "for $n in //personaje[raza='Saiyajin']/nivel_poder return replace value of node $n with $n + 500";
            clientSession.execute("XQUERY " + updateQuery);

            System.out.println("Nivel actualizado correctamente");

            String consultaQuery = "for $n in //personaje[raza='Saiyajin']/nivel_poder return $n";
            String result = clientSession.execute("XQUERY " + consultaQuery);
            System.out.println(result);

            // Cerrar la sesión
            clientSession.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
