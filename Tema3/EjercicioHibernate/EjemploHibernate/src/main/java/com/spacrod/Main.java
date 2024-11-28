package com.spacrod;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        //paso 1
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Persona.class).buildSessionFactory();
        //paso 2
        Session session = sessionFactory.getCurrentSession();
        try{
            //paso 3
            session.beginTransaction();
            //paso 4
            Persona persona = new Persona();
            persona.setNombre("Carla");
            persona.setEdad(50);
            //paso 5
            session.save(persona);
            //paso 6
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            sessionFactory.close();
            session.close();
        }
    }
}