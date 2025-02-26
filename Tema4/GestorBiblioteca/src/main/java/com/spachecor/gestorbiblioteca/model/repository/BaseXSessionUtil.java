package com.spachecor.gestorbiblioteca.model.repository;

import org.basex.api.client.ClientSession;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.file.Paths;

/**
 * Clsae BaseXSessionUtil que define como crear la sesion para conectarse a la base de datos y operar con ella.
 * @author Selene
 * @version 1.0
 */
public class BaseXSessionUtil {
    private static final String HOST = "localhost";
    private static final int PORT = 1984;
    private static final String USER = "selene";
    private static final String PASSWORD = "selene";
    public static final String DATABASE = "library";
    public static final String URL_DATABASE_ORIGINAL = Paths.get("src/main/resources/com/spachecor/gestorbiblioteca/db/library.xml").toAbsolutePath().toString();

    /**
     * Funcion que crea la sesion para acceder a la base de datos XML nativa con BaseX y abre la base de datos concreta.
     * @return El objeto ClientSession necesario y configurado para hacer consultas a la base de datos
     * @throws IOException Excepcion que puede generar
     */
    public static ClientSession getSession() {
        try{
            ClientSession clientSession = new ClientSession(BaseXSessionUtil.HOST, BaseXSessionUtil.PORT, BaseXSessionUtil.USER, BaseXSessionUtil.PASSWORD);
            clientSession.execute("open "+BaseXSessionUtil.DATABASE);
            return clientSession;
        }catch (IOException e){
            System.err.println("Error: "+e.getMessage());
            System.out.println("Finalizando programa...");
            System.exit(1);
            return null;
        }
    }

    /**
     * Funcion que crea un backup de de base de datos actual
     * @param session El objeto ClientSession para ejecutar la creacion del backup
     * @throws IOException Excepcion que puede lanzar en el proceso
     */
    public static void iniciarBackup(ClientSession session) throws IOException {
        session.execute("create backup "+BaseXSessionUtil.DATABASE);
    }

    /**
     * Funcion que restaura la base de datos al backup anterior
     * @param session El objeto ClientSession para ejecutar la restauracion al ultimo backup
     * @throws IOException Excepcion que puede lanzar
     */
    public static void restaurarBackup(ClientSession session) throws IOException {
        session.execute("restore "+BaseXSessionUtil.DATABASE);
    }

    /**
     * Funcion que persiste en el xml que originó la base de datos
     * @param session El objeto ClientSession para ejecutar la persistencia de el xml padre
     * @throws IOException Excepcion que puede lanzar
     */
    public static void persistirEnBBDDOriginal(ClientSession session) throws IOException {
        session.execute("export "+BaseXSessionUtil.URL_DATABASE_ORIGINAL);
    }
}
