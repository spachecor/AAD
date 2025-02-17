package com.spachecor.ejemplobasex.ejerciciosuno;

import org.basex.api.client.ClientSession;

public class EjercicioDos {
    public static void main(String[] args) {
        try{
            ClientSession clientSession = new ClientSession("localhost", 1984, "goku", "goku");
            // Abrir la base de datos antes de ejecutar la consulta
            clientSession.execute("OPEN dragonball-db");
            /*Ejercicio 2: Cambiar el planeta de origen de Vegeta: Modificar el planeta de origen de Vegeta y establecerlo
            en “Tierra”.
            Pista: Usa replace value of node para actualizar el contenido del nodo.*/
            String updateQuery = "replace value of node //personaje[nombre='Vegeta']/planeta_origen with 'Tierra'";
            clientSession.execute("XQUERY "+updateQuery);

            String consultaQuery = "//personaje[nombre='Vegeta']/planeta_origen";
            String resultado = clientSession.execute("XQUERY "+consultaQuery);
            System.out.println(resultado);
            // Cerrar la sesión
            clientSession.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
