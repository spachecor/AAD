package com.spachecor.gestorbiblioteca.model.dao;

import com.spachecor.gestorbiblioteca.model.entity.Entidad;
import com.spachecor.gestorbiblioteca.model.mapper.Mapper;
import com.spachecor.gestorbiblioteca.model.repository.BaseXSessionUtil;
import org.basex.api.client.ClientSession;
import org.basex.query.value.item.Str;
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
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

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
        List<T> lista = new ArrayList<>();
        try(ClientSession clientSession = BaseXSessionUtil.getSession()){
            String xquery = "for $t in "+this.getCollectionPath()+"/"+this.getEntityTag()+" return $t";
            String resultado = clientSession.query(xquery).execute();
            //si no hay resultados, se devuelve la lista vacia
            if (resultado == null || resultado.trim().isEmpty()) {
                return lista;
            }
            //Envolvemos en un envoltorio el resultado porque contendrá varios nodos y daría problemas al no ser un xml mal formado
            String envoltorioXML = "<root>"+resultado+"</root>";

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(envoltorioXML.getBytes("UTF-8")));

            // Obtenemos todos los nodos de la entidad
            NodeList nodeList = doc.getElementsByTagName(getEntityTag());
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                // Convertimos el nodo a String usando un Transformer
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                StringWriter writer = new StringWriter();
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

    @Override
    public T buscarPorId(Integer id) {
        return null;
    }

    @Override
    public void crear(T t) {

    }

    @Override
    public void actualizar(T t) {

    }

    @Override
    public void eliminar(T t) {

    }
}
