package com.spacrod;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class EjemploOnce {
    public static void main(String[] args) {
        try {
            // Cargar el esquema XSD
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("xml/biblioteca.xsd"));

            // Crear el validador
            Validator validator = schema.newValidator();

            // Validar el documento XML
            validator.validate(new StreamSource(new File("xml/biblioteca.xml")));
            System.out.println("El documento XML es válido.");
        } catch (Exception e) {
            System.out.println("El documento XML no es válido: " + e.getMessage());
        }
    }
}
