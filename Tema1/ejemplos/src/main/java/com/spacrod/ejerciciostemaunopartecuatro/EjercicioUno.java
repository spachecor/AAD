package com.spacrod.ejerciciostemaunopartecuatro;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class EjercicioUno {
    public static void main(String[] args) {
        /*
        * Ejercicio 1: Crear un Documento XML Simple. Crea un programa que genere un fichero XML llamado fruta.xml que
        * contenga la información de una fruta. El XML debe tener la siguiente estructura:
        * <fruta>
        *   <nombre>Manzana</nombre>
        *   <color>Rojo</color>
        *   <cantidad>10</cantidad>
        * </fruta>
        * */
        try {
            // Crear un documento XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Crear el elemento raíz
            Element raiz = doc.createElement("fruta");
            doc.appendChild(raiz);

            // Crear el elemento nombre
            Element nombre = doc.createElement("nombre");
            nombre.appendChild(doc.createTextNode("Manzana"));
            raiz.appendChild(nombre);

            // Crear el elemento color
            Element color = doc.createElement("color");
            color.appendChild(doc.createTextNode("Rojo"));
            raiz.appendChild(color);

            // Crear el elemento cantidad
            Element cantidad = doc.createElement("cantidad");
            cantidad.appendChild(doc.createTextNode("10"));
            raiz.appendChild(cantidad);

            // Escribir el contenido del documento en un fichero XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // Habilitar la indentación
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("xml/fruta.xml"));
            transformer.transform(source, result);

            System.out.println("Documento XML creado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
