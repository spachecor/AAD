package com.spachecor.ejerciciofinalsgb.model.repository;

import com.spachecor.ejerciciofinalsgb.model.documents.DocumentosManager;
import com.spachecor.ejerciciofinalsgb.model.entity.Libro;
import com.spachecor.ejerciciofinalsgb.model.entity.Prestamo;
import com.spachecor.ejerciciofinalsgb.model.entity.Usuario;
import com.thoughtworks.xstream.core.BaseException;
import org.basex.api.client.ClientSession;

import java.awt.print.Book;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Clsae BaseXSessionUtil que define como crear la sesion para conectarse a la base de datos y operar con ella.
 * @author Selene
 * @version 1.0
 */
public class BaseXSessionUtil {
    private static final String HOST = "localhost";
    private static final int PORT = 1984;
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";
    public static final String DATABASE = "librarywithcollections";

    /**
     * Funcion que crea la sesion para acceder a la base de datos XML nativa con BaseX y abre la base de datos concreta.
     * @return El objeto ClientSession necesario y configurado para hacer consultas a la base de datos
     */
    public static ClientSession getSession() {
        try{
            ClientSession clientSession = new ClientSession(BaseXSessionUtil.HOST, BaseXSessionUtil.PORT, BaseXSessionUtil.USER, BaseXSessionUtil.PASSWORD);
            //si la bbdd no existe, la creamos y le metemos algunas entidades de ejemplo
            if(!Boolean.parseBoolean(clientSession.query("db:exists('"+BaseXSessionUtil.DATABASE+"')").execute())){
                clientSession.execute("create db "+BaseXSessionUtil.DATABASE);
                BaseXSessionUtil.crearDatosDePrueba();
            }
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
     * Funcion que genera una serie de entidades de prueba
     */
    private static void crearDatosDePrueba(){
        DocumentosManager.agregarDocumento(Libro.class, Paths.get("src/main/resources/com/spachecor/ejerciciofinalsgb/dbexample/books/book1.xml").toAbsolutePath().toString(), "fiction");
        DocumentosManager.agregarDocumento(Libro.class, Paths.get("src/main/resources/com/spachecor/ejerciciofinalsgb/dbexample/books/book2.xml").toAbsolutePath().toString(), "fiction");
        DocumentosManager.agregarDocumento(Libro.class, Paths.get("src/main/resources/com/spachecor/ejerciciofinalsgb/dbexample/books/book3.xml").toAbsolutePath().toString(), "nonfiction");
        DocumentosManager.agregarDocumento(Libro.class, Paths.get("src/main/resources/com/spachecor/ejerciciofinalsgb/dbexample/books/book4.xml").toAbsolutePath().toString(), "nonfiction");
        DocumentosManager.agregarDocumento(Libro.class, Paths.get("src/main/resources/com/spachecor/ejerciciofinalsgb/dbexample/books/book5.xml").toAbsolutePath().toString(), "children");
        DocumentosManager.agregarDocumento(Usuario.class, Paths.get("src/main/resources/com/spachecor/ejerciciofinalsgb/dbexample/users/user1.xml").toAbsolutePath().toString(), null);
        DocumentosManager.agregarDocumento(Usuario.class, Paths.get("src/main/resources/com/spachecor/ejerciciofinalsgb/dbexample/users/user2.xml").toAbsolutePath().toString(), null);
        DocumentosManager.agregarDocumento(Usuario.class, Paths.get("src/main/resources/com/spachecor/ejerciciofinalsgb/dbexample/users/user3.xml").toAbsolutePath().toString(), null);
        DocumentosManager.agregarDocumento(Prestamo.class, Paths.get("src/main/resources/com/spachecor/ejerciciofinalsgb/dbexample/loans/loan1.xml").toAbsolutePath().toString(), null);
        DocumentosManager.agregarDocumento(Prestamo.class, Paths.get("src/main/resources/com/spachecor/ejerciciofinalsgb/dbexample/loans/loan2.xml").toAbsolutePath().toString(), null);
        DocumentosManager.agregarDocumento(Prestamo.class, Paths.get("src/main/resources/com/spachecor/ejerciciofinalsgb/dbexample/loans/loan3.xml").toAbsolutePath().toString(), null);
    }
}
