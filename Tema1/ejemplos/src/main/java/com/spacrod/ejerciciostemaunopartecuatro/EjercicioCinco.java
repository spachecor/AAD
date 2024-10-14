package com.spacrod.ejerciciostemaunopartecuatro;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
            System.out.println("Seleccione una fruta por el nº que le precede");
            for (int i = 0; i < listaFrutas.getLength(); i++) {
                Element fruta = (Element) listaFrutas.item(i);
                System.out.println("Fruta nº: "+i+1);
                System.out.println("Nombre: " + fruta.getElementsByTagName("nombre").item(0).getTextContent());
                System.out.println("Color: " + fruta.getElementsByTagName("color").item(0).getTextContent());
                System.out.println("Cantidad: " + fruta.getElementsByTagName("cantidad").item(0).getTextContent());
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
                    //todo terminar hacer metodos
                    case 1:
                        modificarNombreFruta();
                        break;
                    case 2:
                        modificarColorFruta();
                        break;
                    case 3:
                        modificarCantidadFruta();
                        break;
                }
                //CODIGO PARA MODIFICAR LA FRUTA ELEGIDA, TOMAREMOS ÚNICAMENTE LA FRUTA QUE HA SELECCIONADO EL USUARIO
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}
