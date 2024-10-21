package com.spacrod;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class EjemploTrece {
    public static void main(String[] args) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        DefaultHandler handler = new DefaultHandler() {
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                System.out.println("Inicio del elemento: " + qName);
            }
            public void endElement(String uri, String localName, String qName) throws SAXException {
                System.out.println("Fin del elemento: " + qName);
            }
            public void characters(char ch[], int start, int length) throws SAXException {
                System.out.println("Contenido: " + new String(ch, start, length));
            }
        };
        saxParser.parse("xml/frutas.xml", handler);
    }
}
