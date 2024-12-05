package com.spacrod.ejerciciosparteuno.ejerciciocuatro;

import com.spacrod.ejerciciosparteuno.Estudiante;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestEstudiante {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            Estudiante estudiante = session.createQuery("from Estudiante order by id asc limit 1", Estudiante.class).getSingleResult();
            estudiante.setNombre("Pepa");
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
