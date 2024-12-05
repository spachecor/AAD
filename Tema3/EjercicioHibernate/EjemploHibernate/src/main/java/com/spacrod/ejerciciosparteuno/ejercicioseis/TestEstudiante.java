package com.spacrod.ejerciciosparteuno.ejercicioseis;

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
            Estudiante estudiante = session.createQuery("from Estudiante where email like 'juan.perez@example.com'", Estudiante.class).getSingleResult();
            estudiante.setNombre("Amatisto");
            estudiante.setApellido("Jiménez");
            estudiante.setEdad(57);
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
