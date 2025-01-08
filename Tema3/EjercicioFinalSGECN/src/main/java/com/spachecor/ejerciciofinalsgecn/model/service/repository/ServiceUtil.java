package com.spachecor.ejerciciofinalsgecn.model.service.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase ServiceUtil, que se encarga de gestionar la sesion para la persistencia con Hibernate
 * @author Selene
 * @version 1.0
 */
public class ServiceUtil {
    private static final SessionFactory sessionFactory;
    static {
        sessionFactory = ServiceUtil.buildSessionFactory();
    }

    /**
     * Método que crea el SessionFactory para el resto del programa
     * @return El SessionFactory del programa
     */
    private static SessionFactory buildSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }

    /**
     * Método que crea un objeto Session por cada conexión a partir del SessionFactory
     * @return El Session de cada conexión
     */
    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
