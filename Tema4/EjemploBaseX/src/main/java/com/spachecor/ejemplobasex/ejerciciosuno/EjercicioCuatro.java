package com.spachecor.ejemplobasex.ejerciciosuno;

import org.basex.api.client.ClientSession;

public class EjercicioCuatro {
    public static void main(String[] args) {
        try{
            ClientSession clientSession = new ClientSession("localhost", 1984, "goku", "goku");
            // Abrir la base de datos antes de ejecutar la consulta
            clientSession.execute("OPEN dragonball-db");
            /*
            * Ejercicio 4: Eliminar a Freezer de la base de datos: Borrar completamente el nodo <personaje> de Freezer
            * si existe en la base de datos.
            * Pista: Usa delete node para eliminar el personaje correspondiente.
            * */

            String deleteQuery = "delete node //personaje[nombre='Freezer']";
            clientSession.execute("XQUERY "+deleteQuery);

            String consultaQuery = "//personajes";
            String result = clientSession.execute("XQUERY "+consultaQuery);
            System.out.println(result);

            // Cerrar la sesión
            clientSession.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
