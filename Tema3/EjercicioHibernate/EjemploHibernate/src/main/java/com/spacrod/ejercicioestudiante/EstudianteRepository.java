package com.spacrod.ejercicioestudiante;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EstudianteRepository {
    private SessionFactory sessionFactory;
    public EstudianteRepository() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }
    public Boolean save(Estudiante estudiante) {
        Session session = sessionFactory.getCurrentSession();

    }
}
