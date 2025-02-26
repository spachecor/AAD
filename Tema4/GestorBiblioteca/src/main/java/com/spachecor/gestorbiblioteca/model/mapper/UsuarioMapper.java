package com.spachecor.gestorbiblioteca.model.mapper;

import com.spachecor.gestorbiblioteca.model.entity.Usuario;
import org.w3c.dom.Element;

/**
 * Clase UsuarioMapper que sirve para definir como se mapea un usuario de XML a Usuario y viceversa.
 * @author Selene
 * @version 1.0
 */
public class UsuarioMapper implements Mapper<Usuario> {
    @Override
    public Usuario deXML(String xml) {
        Usuario usuario = new Usuario();
        try{
            Element root = this.obtenerElementoRaiz(xml);

            usuario.setId(Integer.parseInt(root.getElementsByTagName("id").item(0).getTextContent()));
            usuario.setDni(root.getElementsByTagName("dni").item(0).getTextContent());
            usuario.setNombre(root.getElementsByTagName("name").item(0).getTextContent());
            usuario.setEmail(root.getElementsByTagName("email").item(0).getTextContent());
            usuario.setTelefono(root.getElementsByTagName("phone").item(0).getTextContent());
            usuario.setDireccion(root.getElementsByTagName("address").item(0).getTextContent());
        }catch (Exception e){
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public String aXML(Usuario usuario) {
        return "<user>" +
                "<id>"+usuario.getId()+"</id>" +
                "<dni>"+usuario.getDni()+"</dni>" +
                "<name>"+usuario.getNombre()+"</name>" +
                "<email>"+usuario.getEmail()+"</email>" +
                "<phone>"+usuario.getTelefono()+"</phone>" +
                "<address>"+usuario.getDireccion()+"</address>" +
                "</user>";
    }
}
