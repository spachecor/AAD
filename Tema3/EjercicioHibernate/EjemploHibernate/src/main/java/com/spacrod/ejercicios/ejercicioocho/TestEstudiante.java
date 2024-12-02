package com.spacrod.ejercicios.ejercicioocho;

import com.spacrod.ejercicios.Estudiante;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TestEstudiante {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            List<Estudiante> estudianteList = session.createQuery("from Estudiante where edad < 18").getResultList();
            for(Estudiante estudiante : estudianteList){
                session.delete(estudiante);
            }
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
