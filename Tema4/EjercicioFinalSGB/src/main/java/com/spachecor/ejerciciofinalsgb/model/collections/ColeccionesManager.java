package com.spachecor.ejerciciofinalsgb.model.collections;

import com.spachecor.ejerciciofinalsgb.model.dao.LibroDAOGenericImpl;
import com.spachecor.ejerciciofinalsgb.model.repository.BaseXSessionUtil;
import org.basex.api.client.ClientSession;

public class ColeccionesManager {
    public static String[] listarColecciones(){
        try(ClientSession session = BaseXSessionUtil.getSession()){
            return session.query("distinct-values(for $doc in collection('librarywithcollections') " +
                    "let $parts := tokenize(db:path($doc), '/') " +
                    "return concat($parts[2]))").execute().split("\\r?\\n");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static String[] listarSubColeccionesLibros(){
        try(ClientSession session = BaseXSessionUtil.getSession()){
            return session.query("distinct-values(for $doc in collection('librarywithcollections')//book[starts-with(db:path(.), 'librarywithcollections/books')] " +
                    "let $parts := tokenize(db:path($doc), '/') " +
                    "return concat($parts[3]))").execute().split("\\r?\\n");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static void modificarSubcoleccion(String nuevaSubColeccion, String viejaSubColeccion){
        try(ClientSession session = BaseXSessionUtil.getSession()){
            String[] rutas = session.query(
                    "for $doc in collection('librarywithcollections') " +
                            "where starts-with(db:path($doc), 'librarywithcollections/books/"+viejaSubColeccion+"') " +
                            "return db:path($doc)").execute().split("\\r?\\n");
            for(String ruta : rutas){
                session.execute("rename "+ruta+" "+ruta.replace(viejaSubColeccion, nuevaSubColeccion));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void eliminarSubcoleccion(String subColeccion){
        try(ClientSession session = BaseXSessionUtil.getSession()){
            String[] rutas = session.query(
                    "for $doc in collection('librarywithcollections') " +
                            "where starts-with(db:path($doc), 'librarywithcollections/books/"+subColeccion+"') " +
                            "return db:path($doc)").execute().split("\\r?\\n");
            for(String ruta : rutas){
                System.out.println("delete "+ruta);
                session.execute("delete "+ruta);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
