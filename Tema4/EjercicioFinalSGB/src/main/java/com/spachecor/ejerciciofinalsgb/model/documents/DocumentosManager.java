package com.spachecor.ejerciciofinalsgb.model.documents;

import com.spachecor.ejerciciofinalsgb.model.entity.Entidad;
import com.spachecor.ejerciciofinalsgb.model.entity.Libro;
import com.spachecor.ejerciciofinalsgb.model.entity.Prestamo;
import com.spachecor.ejerciciofinalsgb.model.entity.Usuario;
import com.spachecor.ejerciciofinalsgb.model.repository.BaseXSessionUtil;
import org.basex.api.client.ClientSession;

public class DocumentosManager {
    public static String[] listarDocumentos(){
        try(ClientSession session = BaseXSessionUtil.getSession()){
            return session.query("for $doc in collection('"+BaseXSessionUtil.DATABASE+"') " +
                    "return db:path($doc)").execute().split("\\r?\\n");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
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
    public static void modificarDocumento(String antiguaRuta, String nuevaRuta){
        try(ClientSession session = BaseXSessionUtil.getSession()){
            session.execute("rename "+antiguaRuta+" "+nuevaRuta);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
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
            session.execute("export "+url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
