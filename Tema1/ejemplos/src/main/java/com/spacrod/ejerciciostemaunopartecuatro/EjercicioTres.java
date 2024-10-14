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

public class EjercicioTres {
    public static void main(String[] args) {
        /*
        * Ejercicio 3: Crear un Documento XML con Múltiples Frutas. Modifica el programa del Ejercicio 1 para que
        * genere un fichero XML llamado frutas.xml que contenga información de varias frutas. La estructura debe
        * ser similar a:
        *  <frutas>
        *    <fruta>
        *     <nombre>Manzana</nombre>
        *     <color>Rojo</color>
        *     <cantidad>10</cantidad>
        *   </fruta>
        *   <fruta>
        *     <nombre>Banana</nombre>
        *     <color>Amarillo</color>
        *     <cantidad>5</cantidad>
        *   </fruta>
        * </frutas>
        * */
        try {
            // Crear un documento XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Crear el elemento raíz
            Element raiz = doc.createElement("frutas");
            doc.appendChild(raiz);

            EjercicioTres.crearFruta(raiz, doc, new Fruta("Manzana", "Rojo", 10));
            EjercicioTres.crearFruta(raiz, doc, new Fruta("Banana", "Amarillo", 5));

            // Escribir el contenido del documento en un fichero XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // Habilitar la indentación
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("xml/frutas.xml"));
            transformer.transform(source, result);

            System.out.println("Documento XML creado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void crearFruta(Element raiz, Document doc, Fruta frutaObj){
        // Crear el elemento nombre
        Element fruta = doc.createElement("fruta");
        raiz.appendChild(fruta);

        // Crear el elemento nombre
        Element nombre = doc.createElement("nombre");
        nombre.appendChild(doc.createTextNode(frutaObj.getNombre()));
        fruta.appendChild(nombre);

        // Crear el elemento color
        Element color = doc.createElement("color");
        color.appendChild(doc.createTextNode(frutaObj.getColor()));
        fruta.appendChild(color);

        // Crear el elemento cantidad
        Element cantidad = doc.createElement("cantidad");
        cantidad.appendChild(doc.createTextNode(String.valueOf(frutaObj.getCantidad())));
        fruta.appendChild(cantidad);
    }
}
