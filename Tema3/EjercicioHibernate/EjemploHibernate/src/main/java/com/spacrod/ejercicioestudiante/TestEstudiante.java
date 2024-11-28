package com.spacrod.ejercicioestudiante;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestEstudiante {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            Estudiante estudiante = new Estudiante("Juan", "Pérez", "juan.perez@exam");
            session.save(estudiante);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
}
