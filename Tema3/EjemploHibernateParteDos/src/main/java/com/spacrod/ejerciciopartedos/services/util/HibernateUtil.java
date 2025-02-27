package com.spacrod.ejerciciopartedos.services.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    static {
        HibernateUtil.sessionFactory = buildSessionFactory();
    }

    private static SessionFactory buildSessionFactory() {
        return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
