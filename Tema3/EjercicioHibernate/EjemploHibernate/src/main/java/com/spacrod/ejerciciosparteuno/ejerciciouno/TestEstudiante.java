package com.spacrod.ejerciciosparteuno.ejerciciouno;

import com.spacrod.ejerciciosparteuno.Estudiante;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class TestEstudiante {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            List<Estudiante> estudianteList = new ArrayList<>();
            estudianteList.add(new Estudiante("Juan", "Pérez", "juan.perez@exam", 18));
            estudianteList.add(new Estudiante("Juana", "Pereza", "juana.pereza@exam", 65));
            estudianteList.add(new Estudiante("Juane", "Perezote", "juane.perezote@exam", 7));
            for (Estudiante estudiante : estudianteList) {
                session.save(estudiante);
                System.out.println("Salvado");
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
