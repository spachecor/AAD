package com.spacrod.ejerciciosparteuno.ejerciciotrece;

import com.spacrod.ejerciciosparteuno.Estudiante;
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
            List<Estudiante> estudianteList = session.createQuery("from Estudiante where edad = (select min(edad) from Estudiante ) order by id asc limit 1", Estudiante.class).getResultList();
            estudianteList.forEach(System.out::println);
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
