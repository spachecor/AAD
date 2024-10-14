package com.spacrod.ejerciciostemaunopartecuatro;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class EjercicioCuatro {
    public static void main(String[] args) {
        /*
        * Ejercicio 4: Leer Múltiples Frutas de un Documento XML. Escribe un programa que lea el fichero frutas.xml
        * creado en el Ejercicio 3 y muestre en la consola el nombre, el color y la cantidad de cada fruta en el
        * archivo. Utiliza un bucle para iterar a través de todos los elementos.
        * */
        try {
            // Cargar el documento XML
            File archivo = new File("xml/frutas.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(archivo);

            // Normalizar el documento XML
            doc.getDocumentElement().normalize();

            // Obtener el elemento raíz
            Element raiz = doc.getDocumentElement();
            System.out.println("Elemento raíz: " + raiz.getNodeName());

            // Obtener los elementos título y autor
            NodeList listaFrutas = doc.getElementsByTagName("fruta");
            for (int i = 0; i < listaFrutas.getLength(); i++) {
                Element fruta = (Element) listaFrutas.item(i);
                System.out.println("Nombre: " + fruta.getElementsByTagName("nombre").item(0).getTextContent());
                System.out.println("Color: " + fruta.getElementsByTagName("color").item(0).getTextContent());
                System.out.println("Cantidad: " + fruta.getElementsByTagName("cantidad").item(0).getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
