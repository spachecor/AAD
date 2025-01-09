package com.spachecor.ejerciciofinalsgecn;

import com.spachecor.ejerciciofinalsgecn.model.entity.Curso;
import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import com.spachecor.ejerciciofinalsgecn.model.entity.Nota;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;

public class TestDos {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();


        /*Session session = sessionFactory.getCurrentSession();

        try{
            session.beginTransaction();
            Estudiante jose = new Estudiante();
            jose.setNombre("Jose");
            jose.setApellido("Gómez");
            jose.setEmail("jose@gmail.com");

            session.persist(jose);

            Curso informaticaUno = new Curso();
            informaticaUno.setNombre("Informatica Uno");
            informaticaUno.setDescripcion("Informatica Uno - descripcion");

            session.persist(informaticaUno);

            jose.getCursos().add(informaticaUno);
            informaticaUno.getEstudiantes().add(jose);

            Curso informaticaDos = new Curso();
            informaticaDos.setNombre("Informatica Dos");
            informaticaDos.setDescripcion("Informatica Dos - descripcion");

            session.persist(informaticaDos);

            jose.getCursos().add(informaticaDos);
            informaticaDos.getEstudiantes().add(jose);

            Nota notaJoseInformaticaUno = new Nota();
            notaJoseInformaticaUno.setFecha(LocalDate.now());
            notaJoseInformaticaUno.setValor(8D);
            notaJoseInformaticaUno.setEstudiante(jose);
            notaJoseInformaticaUno.setCurso(informaticaUno);
            informaticaUno.getNotas().add(notaJoseInformaticaUno);

            session.persist(notaJoseInformaticaUno);

            Nota notaJoseInformaticaDos = new Nota();
            notaJoseInformaticaDos.setFecha(LocalDate.now());
            notaJoseInformaticaDos.setValor(8.5D);
            notaJoseInformaticaDos.setEstudiante(jose);
            notaJoseInformaticaDos.setCurso(informaticaDos);
            informaticaDos.getNotas().add(notaJoseInformaticaDos);

            session.persist(notaJoseInformaticaDos);

            System.out.println("RESULTADO SESSION-A");
            List<Curso> cursos = session.createQuery("from Curso").list();
            List<Nota> notas = session.createQuery("from Nota").list();
            List<Estudiante> estudiantes = session.createQuery("from Estudiante").list();
            cursos.forEach(System.out::println);
            notas.forEach(System.out::println);
            estudiantes.forEach(System.out::println);

            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }


        session = sessionFactory.getCurrentSession();

        try{
            session.beginTransaction();
            Estudiante ana = new Estudiante();
            ana.setNombre("Ana");
            ana.setApellido("Gómez");
            ana.setEmail("Ana@gmail.com");

            session.persist(ana);

            Curso informaticaUno = session.find(Curso.class, 7);

            ana.getCursos().add(informaticaUno);
            informaticaUno.getEstudiantes().add(ana);

            session.persist(informaticaUno);

            Curso informaticaDos = session.find(Curso.class, 8);

            ana.getCursos().add(informaticaDos);
            informaticaDos.getEstudiantes().add(ana);

            session.persist(informaticaDos);

            Nota notaJoseInformaticaUno = new Nota();
            notaJoseInformaticaUno.setFecha(LocalDate.now());
            notaJoseInformaticaUno.setValor(10D);
            notaJoseInformaticaUno.setEstudiante(ana);
            notaJoseInformaticaUno.setCurso(informaticaUno);
            informaticaUno.getNotas().add(notaJoseInformaticaUno);

            session.persist(notaJoseInformaticaUno);

            Nota notaJoseInformaticaDos = new Nota();
            notaJoseInformaticaDos.setFecha(LocalDate.now());
            notaJoseInformaticaDos.setValor(5.5D);
            notaJoseInformaticaDos.setEstudiante(ana);
            notaJoseInformaticaDos.setCurso(informaticaDos);
            informaticaDos.getNotas().add(notaJoseInformaticaDos);

            session.persist(notaJoseInformaticaDos);

            System.out.println("RESULTADO SESSION-B");
            List<Curso> cursos = session.createQuery("from Curso").list();
            List<Nota> notas = session.createQuery("from Nota").list();
            List<Estudiante> estudiantes = session.createQuery("from Estudiante").list();
            cursos.forEach(System.out::println);
            notas.forEach(System.out::println);
            estudiantes.forEach(System.out::println);

            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }*/

        Session sessionC = sessionFactory.openSession();
        try{
            sessionC.beginTransaction();
            List<Nota> notas = sessionC.createQuery("from Nota").getResultList();
            List<Estudiante> estudiantes = sessionC.createQuery("from Estudiante").getResultList();
            List<Curso> cursos = sessionC.createQuery("from Curso").getResultList();
            for(Nota nota : notas){
                sessionC.remove(nota);
            }
            for(Estudiante estudiante : estudiantes){
                sessionC.remove(estudiante);
            }
            for(Curso curso : cursos){
                sessionC.remove(curso);
            }
            List<List<Integer>> estudiantecurso = sessionC.createNativeQuery("select * from estudiante_curso").getResultList();
            Object estudianteCurso = sessionC.createNativeQuery(
                    "SELECT * FROM INFORMATION_SCHEMA.COLUMNS " +
                            "WHERE TABLE_NAME = 'ESTUDIANTE_CURSO'"
            ).getResultList();
            sessionC.getTransaction().commit();
        }catch(Exception e){
            sessionC.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            sessionC.close();
        }


        System.out.println("hola");
    }
}