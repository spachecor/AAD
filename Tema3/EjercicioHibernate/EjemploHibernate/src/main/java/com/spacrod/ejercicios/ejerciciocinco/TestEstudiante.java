package com.spacrod.ejercicios.ejerciciocinco;

import com.spacrod.ejercicios.Estudiante;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestEstudiante {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            Estudiante estudiante = session.createQuery("from Estudiante where email like 'enrique.gutierrez@example.com'", Estudiante.class).getSingleResult();
            estudiante.setEdad(3);
            session.update(estudiante);
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
