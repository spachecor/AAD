package com.spachecor.ejerciciofinalsgb;

import com.spachecor.ejerciciofinalsgb.controller.ColeccionesController;
import com.spachecor.ejerciciofinalsgb.model.collections.ColeccionesManager;
import com.spachecor.ejerciciofinalsgb.model.dao.LibroDAOGenericImpl;
import com.spachecor.ejerciciofinalsgb.model.dao.PrestamoDAOGenericImpl;
import com.spachecor.ejerciciofinalsgb.model.dao.UsuarioDAOGenericImpl;
import com.spachecor.ejerciciofinalsgb.model.entity.Usuario;
import com.spachecor.ejerciciofinalsgb.model.repository.BaseXSessionUtil;
import org.basex.api.client.ClientSession;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws IOException {
        /*EJEMPLO INSERCION
        UsuarioDAOGenericImpl userDAO = new UsuarioDAOGenericImpl();
        userDAO.crear(Paths.get("src/main/resources/com/spachecor/ejerciciofinalsgb/db/users/user1.xml").toAbsolutePath().toString());
        System.out.println(userDAO.listar());*/
        LibroDAOGenericImpl libroDAO = new LibroDAOGenericImpl();
        libroDAO.crear(Paths.get("src/main/resources/com/spachecor/ejerciciofinalsgb/db/books/book5.xml").toAbsolutePath().toString(), "children");
        System.out.println(libroDAO.listar());
    }
}
