package com.spachecor.ejerciciofinalsgb.model.dao;

import com.spachecor.ejerciciofinalsgb.model.entity.Entidad;
import com.spachecor.ejerciciofinalsgb.model.mapper.Mapper;
import com.spachecor.ejerciciofinalsgb.model.repository.BaseXSessionUtil;
import org.basex.api.client.ClientSession;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase abstracta EntidadGenericDAOImpl que tiene de utilidad el ser el DAO genérico de todas las Entidades que
 * hereden de Entidad. Para usarse hay que crear una clase hija, que herede esta clase, y así asignar el Mapper,
 * el CollectionPath y el EntityTag.
 * @param <T> El tipo de Entidad que consume la EntidadGenericDAOImpl
 * @author Selene
 * @version 1.0
 */
public abstract class EntidadGenericDAOImpl<T extends Entidad> implements GenericDAO<T> {
    /**
     * Funcion que hace que cada implementacion de esta clase proporcione su Mapper correspondiente
     * @return El Mapper correspondiente a la Entidad de tipo T
     */
    protected abstract Mapper<T> getMapper();

    /**
     * Funcion que devuelve la ruta raiz en la base de datos XML donde se almacenan los nodos de la Entidad tipo T
     * @return La ruta raiz de la Entidad tipo T en la base de datos XML
     */
    protected abstract String getCollectionPath();

    /**
     * Funcion que devuelve el nombre de la etiqueta XML que identifica la Entidad tipo T en la base de datos XML
     * @return El nombre de la etiqueta de la Entidad tipo T
     */
    protected abstract String getEntityTag();

    @Override
    public List<T> listar() {
        String xquery = "collection('"+BaseXSessionUtil.DATABASE+"')//"+this.getEntityTag()+"[starts-with(db:path(.), '"+this.getCollectionPath()+"')]";
        return this.getListaAPartirDeQuery(xquery);
    }

    @Override
    public Optional<T> buscarPorId(Integer id) {
        try(ClientSession session = BaseXSessionUtil.getSession()){
            String xquery = "collection('"+BaseXSessionUtil.DATABASE+"')//"+this.getEntityTag()+"[starts-with(db:path(.), '"+this.getCollectionPath()+"')]"+"[id="+id+"]";
            //String xquery = "for $t in "+this.getCollectionPath()+"/"+this.getEntityTag()+"[id="+id+"] return $t";
            String resultado = session.query(xquery).execute();
            //si ha habido resultado:
            if (resultado != null && !resultado.trim().isEmpty()) {
                //asumimos que el xml que viene esta bien formado porque viene solo un resultado
                return Optional.of(getMapper().deXML(resultado));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //si no tenemos resultado, devolvemos un Optional vacío
        return Optional.empty();
    }

    @Override
    public void crear(String url, String subColeccion) {
        try (ClientSession session = BaseXSessionUtil.getSession()) {
            //creamos el backup antes que nada para realizar una "gestion de transacciones"
            BaseXSessionUtil.iniciarBackup(session);
            //mantenemos la agregación del documento en esta clase en vez de llamar a DocumentosManger para poder gestionarla y hacer backup en caso necesario
            String insertQuery = "add to "+this.getCollectionPath()+ ((subColeccion!=null&&!subColeccion.isEmpty())?"/"+subColeccion:"") +"/ "+url;
            try{
                session.execute(insertQuery);
            }catch (Exception e){
                //si sale mal, restauramos al backup y relanzamos la excepcion
                BaseXSessionUtil.restaurarBackup(session);
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(T t) {
        try (ClientSession session = BaseXSessionUtil.getSession()) {
            //creamos el backup antes que nada para realizar una "gestion de transacciones"
            BaseXSessionUtil.iniciarBackup(session);
            String xml = getMapper().aXML(t);
            String updateQuery = "replace node collection('"+BaseXSessionUtil.DATABASE+"')//"+this.getEntityTag()+"[starts-with(db:path(.), '"+this.getCollectionPath()+"')][id="+t.getId()+"] with "+xml;
            try{
                session.query(updateQuery).execute();
            }catch (Exception e){
                //si sale mal, restauramos al backup y relanzamos la excepcion
                BaseXSessionUtil.restaurarBackup(session);
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(T t) {
        try (ClientSession session = BaseXSessionUtil.getSession()) {
            //creamos el backup antes que nada para realizar una "gestion de transacciones"
            BaseXSessionUtil.iniciarBackup(session);
            String deleteQuery = "delete node collection('"+BaseXSessionUtil.DATABASE+"')//"+this.getEntityTag()+"[starts-with(db:path(.), '"+this.getCollectionPath()+"')][id="+t.getId()+"]";
            //eliminamos tambien el recurso, eliminanto to el rastro de nuestro registro
            String ruta = this.obtenerRutaEntidad(t);
            String deleteResource = "delete "+ruta;
            try{
                session.query(deleteQuery).execute();
                session.execute(deleteResource);
            }catch (Exception e){
                //si sale mal, restauramos al backup y relanzamos la excepcion
                BaseXSessionUtil.restaurarBackup(session);
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected List<T> getListaAPartirDeQuery(String query) {
        List<T> lista = new ArrayList<>();
        try(ClientSession session = BaseXSessionUtil.getSession()){
            String resultado = session.query(query).execute();
            //si no hay resultados, se devuelve la lista vacia
            if (resultado == null || resultado.trim().isEmpty()) {
                return lista;
            }
            //envolvemos en un envoltorio el resultado porque contendrá varios nodos y daría problemas al ser un xml mal formado
            String envoltorioXML = "<root>"+resultado+"</root>";

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(envoltorioXML.getBytes(StandardCharsets.UTF_8)));

            //obtenemos todos los nodos de la entidad
            NodeList nodeList = doc.getElementsByTagName(getEntityTag());
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                //convertimos el nodo a String usando un Transformer
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                StringWriter writer = new StringWriter();
                //transformamos el elemento xml en un string con el StringWriter
                transformer.transform(new DOMSource(element), new StreamResult(writer));
                String xmlString = writer.getBuffer().toString();

                T entity = this.getMapper().deXML(xmlString);
                lista.add(entity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }
    public String obtenerRutaEntidad(T t){
        String url = null;
        try(ClientSession session = BaseXSessionUtil.getSession()){
            url = session.query("for $doc in collection('"+BaseXSessionUtil.DATABASE+"')//"+this.getEntityTag()+" where $doc/id = "+t.getId()+" and starts-with(db:path($doc), '"+this.getCollectionPath()+"') return db:path($doc)").execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }
}
