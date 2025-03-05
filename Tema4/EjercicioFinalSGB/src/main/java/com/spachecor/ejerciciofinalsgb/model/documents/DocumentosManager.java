package com.spachecor.ejerciciofinalsgb.model.documents;

import com.spachecor.ejerciciofinalsgb.model.entity.Entidad;
import com.spachecor.ejerciciofinalsgb.model.entity.Libro;
import com.spachecor.ejerciciofinalsgb.model.entity.Prestamo;
import com.spachecor.ejerciciofinalsgb.model.entity.Usuario;
import com.spachecor.ejerciciofinalsgb.model.repository.BaseXSessionUtil;
import org.basex.api.client.ClientSession;

/**
 * Clase DocumentosManager, que se encarga de gestionar el CRUD de los documentos en nuestra base de datos BaseX
 * @author Selene
 * @version 1.0
 */
public class DocumentosManager {
    /**
     * Funcion que lista todos los documentos contenidos en nuestra base de datos
     * @return Un array de Strings que contiene la lista de documentos albergados en nuestra base de datos
     */
    public static String[] listarDocumentos(){
        try(ClientSession session = BaseXSessionUtil.getSession()){
            return session.query("for $doc in collection('"+BaseXSessionUtil.DATABASE+"') " +
                    "return db:path($doc)").execute().split("\\r?\\n");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Funcion que se encarga de agregar un nuevo documento en la base de datos
     * @param clase La clase del objeto de tipo Entidad al que pertenecen las entidades que se van a agregar a la base
     *              de datos
     * @param url La url donde se encuentra el documento
     * @param subCategoria La subcategoria a la que pertenece la entidad, que puede ser null si no pertence a ninguna,
     *                     como pasaría si introducimos libros o préstamos
     * @param <T> Objetos que hereden de Entidad
     */
    public static <T extends Entidad> void agregarDocumento(Class<T> clase, String url, String subCategoria){
        try(ClientSession session = BaseXSessionUtil.getSession()){
            String rutaInterna = BaseXSessionUtil.DATABASE+"/";
            if(clase.equals(Libro.class)) rutaInterna+="books/"+((subCategoria==null)?"":subCategoria+"/");
            else if(clase.equals(Usuario.class)) rutaInterna+="users/";
            else if(clase.equals(Prestamo.class)) rutaInterna+="loans/";
            session.execute("add to "+rutaInterna+" "+url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Funcion que define como se modifica la ruta de un documento en nuestra base de datos
     * @param antiguaRuta La ruta interna anterior
     * @param nuevaRuta La nueva interna ruta
     */
    public static void modificarDocumento(String antiguaRuta, String nuevaRuta){
        try(ClientSession session = BaseXSessionUtil.getSession()){
            session.execute("rename "+antiguaRuta+" "+nuevaRuta);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Funcion que define como se elimina un documento de la base de datos
     * @param ruta La ruta interna donde se encuentra la base de datos
     */
    public static void eliminarDocumento(String ruta){
        try(ClientSession session = BaseXSessionUtil.getSession()){
            session.execute("delete "+ruta);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Funcion que exporta la base de datos y su estructura
     * @param url La URL donde almacenar la base de datos
     */
    public static void exportarBBDD(String url){
        try(ClientSession session = BaseXSessionUtil.getSession()){
            System.out.println("export \""+url+"\"");
            session.execute("export \""+url+"\"");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
