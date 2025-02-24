package com.spachecor.gestorbiblioteca.model.repository;

import org.basex.api.client.ClientSession;

import java.io.IOException;

public class BaseXSessionUtil {
    private static final String HOST = "localhost";
    private static final int PORT = 1984;
    private static final String USER = "selene";
    private static final String PASSWORD = "selene";
    private static final String DATABASE = "library";

    public static ClientSession getSession() throws IOException {
        ClientSession clientSession = new ClientSession(BaseXSessionUtil.HOST, BaseXSessionUtil.PORT, BaseXSessionUtil.USER, BaseXSessionUtil.PASSWORD);
        clientSession.execute("open "+BaseXSessionUtil.DATABASE);
        return clientSession;
    }
}
