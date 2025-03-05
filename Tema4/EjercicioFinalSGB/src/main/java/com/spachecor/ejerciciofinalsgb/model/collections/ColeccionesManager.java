package com.spachecor.ejerciciofinalsgb.model.collections;

import com.spachecor.ejerciciofinalsgb.model.dao.LibroDAOGenericImpl;
import com.spachecor.ejerciciofinalsgb.model.documents.DocumentosManager;
import com.spachecor.ejerciciofinalsgb.model.entity.Libro;
import com.spachecor.ejerciciofinalsgb.model.repository.BaseXSessionUtil;
import org.basex.api.client.ClientSession;

/**
 * Clase ColeccionesManager, que se encarga de gestionar el CRUD de las colecciones(simuladas, porque basex no tiene
 * colecciones como tal) en la base de datos.
 * @author Selene
 * @version 1.0
 */
public class ColeccionesManager {
    /**
     * Funcion que obtiene una lista con todas las colecciones principales, es decir, Libro, Usuario y Préstamo
     * @return La lista con las colecciones principales
     */
    public static String[] listarColecciones(){
        try(ClientSession session = BaseXSessionUtil.getSession()){
            return session.query("distinct-values(for $doc in collection('"+BaseXSessionUtil.DATABASE+"') " +
                    "let $parts := tokenize(db:path($doc), '/') " +
                    "return concat($parts[2]))").execute().split("\\r?\\n");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Funcion que se encarga de listar las subcolecciones, es decir, las colecciones dentro de la coleccion de libros,
     * que representan las categorias de los libros
     * @return La lista con las categorias de libros, que son las subcolecciones de libros
     */
    public static String[] listarSubColeccionesLibros(){
        try(ClientSession session = BaseXSessionUtil.getSession()){
            return session.query("distinct-values(for $doc in collection('"+BaseXSessionUtil.DATABASE+"')//book[starts-with(db:path(.), '"+BaseXSessionUtil.DATABASE+"/books')] " +
                    "let $parts := tokenize(db:path($doc), '/') " +
                    "return concat($parts[3]))").execute().split("\\r?\\n");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Funcion que modifica el nombre de una subcoleccion(realmente modifica la ruta del documento)
     * @param url La ruta interna donde se encuentra la subcoleccion
     * @param nuevaSubColeccion El nuevo nombre de la coleccion
     * @param viejaSubColeccion El antiguo nombre de la coleccion
     */
    private static void modificarSubColeccion(String url, String nuevaSubColeccion, String viejaSubColeccion){
        DocumentosManager.modificarDocumento(url, url.replace(viejaSubColeccion, nuevaSubColeccion));
    }

    /**
     * Funcion que se encarga de modificar la categoria(o subcoleccion) una entidad de tipo Libro, que realmente modifica
     * su ruta interna en la base de datos
     * @param libro La entidad de tipo Libro
     * @param nuevaSubColeccion El nuevo nombre de la categoria o subcoleccion
     * @param viejaSubColeccion El antiguo nombre de la categoria o subcoleccion
     */
    public static void modificarSubColeccionPorId(Libro libro, String nuevaSubColeccion, String viejaSubColeccion){
        LibroDAOGenericImpl libroDao = new LibroDAOGenericImpl();
        String url = libroDao.obtenerRutaEntidad(libro);
        ColeccionesManager.modificarSubColeccion(url, nuevaSubColeccion, viejaSubColeccion);
    }

    /**
     * Funcion que se encarga de modificar la categoria o subcoleccion de todos los miembros de una subcoleccion completa
     * @param nuevaSubColeccion El nuevo nombre de la subcoleccion
     * @param viejaSubColeccion El antiguo nombre de la subcoleccion
     */
    public static void modificarTodosLosMiembrosSubcoleccion(String nuevaSubColeccion, String viejaSubColeccion){
        String[] rutas = null;
        try(ClientSession session = BaseXSessionUtil.getSession()){
            rutas = session.query(
                    "for $doc in collection('"+BaseXSessionUtil.DATABASE+"') " +
                            "where starts-with(db:path($doc), '"+BaseXSessionUtil.DATABASE+"/books/"+viejaSubColeccion+"') " +
                            "return db:path($doc)").execute().split("\\r?\\n");
        }catch (Exception e){
            e.printStackTrace();
        }
        if(rutas != null){
            for(String ruta : rutas){
                ColeccionesManager.modificarSubColeccion(ruta, nuevaSubColeccion, viejaSubColeccion);
            }
        }
    }

    /**
     * Funcion que elimina una subcoleccion completa, con todas las entidades albergadas en ella
     * @param subColeccion El nombre de la subcoleccion a eliminar
     */
    public static void eliminarSubcoleccion(String subColeccion){
        try(ClientSession session = BaseXSessionUtil.getSession()){
            String[] rutas = session.query(
                    "for $doc in collection('"+BaseXSessionUtil.DATABASE+"') " +
                            "where starts-with(db:path($doc), '"+BaseXSessionUtil.DATABASE+"/books/"+subColeccion+"') " +
                            "return db:path($doc)").execute().split("\\r?\\n");
            for(String ruta : rutas){
                DocumentosManager.eliminarDocumento(ruta);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
