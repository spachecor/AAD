package com.spachecor.ejemplobasex;

import org.basex.api.client.ClientSession;

public class EjemploUno {
    public static void main(String[] args) {
        try{
            ClientSession clientSession = new ClientSession("localhost", 1984, "goku", "goku");
            // Abrir la base de datos antes de ejecutar la consulta
            clientSession.execute("OPEN dragonball-db");

            // Consulta XQuery
            /*String query = "for $p in //personaje " +
                    "return " +
                    "<personaje>" +
                    "<nombre>{$p/nombre/text()}</nombre>" +
                    "<clase>{if ($p/nivel_poder > 8500) then 'Clase S' else 'Clase A'}</clase>" +
                    "</personaje>";*/
            String query = "for $p in //planetas/planeta " +
                    "return " +
                    "<planeta>" +
                    "<nombre>{$p/nombre/text()}</nombre>" +
                    "<habitabilidad>{" +
                    "if ($p/habitantes > 1000000) then 'Alta habitabilidad' " +
                    "else if ($p/habitantes < 1000) then 'Baja habitabilidad' " +
                    "else 'Media habitabilidad'" +
                    "}</habitabilidad>" +
                    "</planeta>";

            String result = clientSession.execute("XQUERY " + query);

            // Mostrar resultado
            System.out.println("Resultado de la consulta:");
            System.out.println(result);

            // Cerrar la sesión
            clientSession.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
