package com.spacrod;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EjemploDoce {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("xml/frutas.xml");

        // Acceder al elemento raíz
        Element raiz = document.getDocumentElement();
        System.out.println("Elemento raíz: " + raiz.getNodeName());
        NodeList frutas = raiz.getElementsByTagName("fruta");
        for (int i = 0; i < frutas.getLength(); i++) {
            Element fruta = (Element) frutas.item(i);
            System.out.println(fruta.getTagName()+":");
            NodeList elementos = fruta.getChildNodes();
            for (int j = 0; j < elementos.getLength(); j++) {
                Node nodo = elementos.item(j);
                Element elemento = null;
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    elemento = (Element) nodo;
                }else continue;
                System.out.println(elemento.getTagName()+": "+elemento.getTextContent());
            }
        }
    }
}
