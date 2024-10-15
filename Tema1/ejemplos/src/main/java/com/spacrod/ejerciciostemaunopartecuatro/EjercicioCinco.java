package com.spacrod.ejerciciostemaunopartecuatro;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class EjercicioCinco {
    public static void main(String[] args) {
        /*
        * Ejercicio 5: Modificar un Documento XML Existente. Crea un programa que lea el fichero frutas.xml, permita al
        * usuario modificar el color o la cantidad de una fruta específica (por ejemplo, cambiar la cantidad de la
        * primera fruta a “20”), y luego sobrescriba el fichero frutas.xml con la información actualizada. Asegúrate de
        * manejar correctamente la estructura XML al realizar las modificaciones.
        * */
        Scanner sc = new Scanner(System.in);
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
            System.out.println("Seleccione una fruta por el nº que le precede\n");
            for (int i = 0; i < listaFrutas.getLength(); i++) {
                Element fruta = (Element) listaFrutas.item(i);
                System.out.println("Fruta nº: "+(i+1));
                System.out.println("Nombre: " + fruta.getElementsByTagName("nombre").item(0).getTextContent());
                System.out.println("Color: " + fruta.getElementsByTagName("color").item(0).getTextContent());
                System.out.println("Cantidad: " + fruta.getElementsByTagName("cantidad").item(0).getTextContent()+"\n");
            }
            System.out.println("Seleccione la fruta: ");
            int frutaElegida = sc.nextInt();
            for (int i = 0; i < listaFrutas.getLength(); i++) {
                if((i+1)!=frutaElegida)continue;
                Element fruta = (Element) listaFrutas.item(i);
                System.out.println("¿Qué desea modificar?");
                System.out.println("1- Nombre\n2- Color\n3- Cantidad");
                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        Element nombre = (Element) fruta.getElementsByTagName("nombre").item(0);
                        sc.nextLine();
                        System.out.println("Introduzca un nuevo nombre: ");
                        nombre.setTextContent(sc.nextLine());
                        break;
                    case 2:
                        Element color = (Element) fruta.getElementsByTagName("color").item(0);
                        sc.nextLine();
                        System.out.println("Introduzca un nuevo color: ");
                        color.setTextContent(sc.nextLine());
                        break;
                    case 3:
                        Element cantidad = (Element) fruta.getElementsByTagName("cantidad").item(0);
                        sc.nextLine();
                        System.out.println("Introduzca una nueva cantidad: ");
                        cantidad.setTextContent(sc.nextLine());
                        break;
                }
                //CODIGO PARA MODIFICAR LA FRUTA ELEGIDA, TOMAREMOS ÚNICAMENTE LA FRUTA QUE HA SELECCIONADO EL USUARIO
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                Result output = new StreamResult(archivo);
                Source input = new DOMSource(doc);
                transformer.transform(input, output);
                System.out.println("Elemento modificado correctamente");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}
