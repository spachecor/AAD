package com.spachecor.ejerciciofinalsgb.model.collections;

import com.spachecor.ejerciciofinalsgb.model.dao.LibroDAOGenericImpl;
import com.spachecor.ejerciciofinalsgb.model.documents.DocumentosManager;
import com.spachecor.ejerciciofinalsgb.model.entity.Libro;
import com.spachecor.ejerciciofinalsgb.model.repository.BaseXSessionUtil;
import org.basex.api.client.ClientSession;

public class ColeccionesManager {
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
    private static void modificarSubColeccion(String url, String nuevaSubColeccion, String viejaSubColeccion){
        DocumentosManager.modificarDocumento(url, url.replace(viejaSubColeccion, nuevaSubColeccion));
    }
    public static void modificarSubColeccionPorId(Libro libro, String nuevaSubColeccion, String viejaSubColeccion){
        LibroDAOGenericImpl libroDao = new LibroDAOGenericImpl();
        String url = libroDao.obtenerRutaEntidad(libro);
        ColeccionesManager.modificarSubColeccion(url, nuevaSubColeccion, viejaSubColeccion);
    }
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
