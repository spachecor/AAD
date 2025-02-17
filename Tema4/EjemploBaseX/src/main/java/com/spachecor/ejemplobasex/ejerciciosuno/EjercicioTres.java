package com.spachecor.ejemplobasex.ejerciciosuno;

import org.basex.api.client.ClientSession;

public class EjercicioTres {
    public static void main(String[] args) {
        try{
            ClientSession clientSession = new ClientSession("localhost", 1984, "goku", "goku");
            // Abrir la base de datos antes de ejecutar la consulta
            clientSession.execute("OPEN dragonball-db");
            /*
            * Ejercicio 3: Añadir una nueva técnica a Goku: Insertar la técnica “Ultra Instinto” en la lista de
            * técnicas de Goku.
            * Pista: Utiliza insert node ... into ... para agregar un nuevo nodo dentro de <tecnicas>.
            * */
            String insertQuery =
                    "insert node <tecnica><nombre>Ultra Instinto</nombre><usuario>Goku</usuario></tecnica> into //tecnicas";
            clientSession.execute("XQUERY "+insertQuery);

            String consultaQuery = "//tecnicas";
            String result = clientSession.execute("XQUERY "+consultaQuery);
            System.out.println(result);
            // Cerrar la sesión
            clientSession.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
