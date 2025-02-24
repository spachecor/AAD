package com.spachecor.gestorbiblioteca.model.mapper;

import com.spachecor.gestorbiblioteca.model.entity.Libro;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.ByteArrayInputStream;

public class LibroMapper implements Mapper<Libro> {
    @Override
    public Libro deXML(String xml) {
        Libro libro = new Libro();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
            Element root = doc.getDocumentElement();

            libro.setId(Integer.parseInt(root.getElementsByTagName("id").item(0).getTextContent()));
            libro.setTitulo(root.getElementsByTagName("title").item(0).getTextContent());
            libro.setAutor(root.getElementsByTagName("author").item(0).getTextContent());
            libro.setAnioPublicacion(Integer.parseInt(root.getElementsByTagName("publicationYear").item(0).getTextContent()));
            libro.setCategoria(root.getElementsByTagName("category").item(0).getTextContent());
            libro.setSubCategoria(root.getElementsByTagName("subcategory").item(0).getTextContent());
            libro.setIsbn(Long.parseLong(root.getElementsByTagName("isbn").item(0).getTextContent()));
            libro.setEditorial(root.getElementsByTagName("publisher").item(0).getTextContent());
            libro.setNumeroPaginas(Integer.parseInt(root.getElementsByTagName("pages").item(0).getTextContent()));
            libro.setNumeroCopiasDisponibles(Integer.parseInt(root.getElementsByTagName("availableCopies").item(0).getTextContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libro;
    }

    @Override
    public String aXML(Libro libro) {
        return "";
    }
}
