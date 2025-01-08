package com.spachecor.ejerciciofinalsgecn;

import com.spachecor.ejerciciofinalsgecn.model.entity.Curso;
import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import com.spachecor.ejerciciofinalsgecn.model.entity.Nota;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        List<Curso> cursos = session.createQuery("from Curso").list();
        List<Nota> notas = session.createQuery("from Nota").list();
        List<Estudiante> estudiantes = session.createQuery("from Estudiante").list();
        Object estudianteCurso = session.createNativeQuery(
                "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'ESTUDIANTE_CURSO'"
        ).getResultList();
        System.out.println("Leido");
        cursos.forEach(System.out::println);
        notas.forEach(System.out::println);
        estudiantes.forEach(System.out::println);
        System.out.println(estudianteCurso);
        
        /*try{
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

            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally{
            session.close();
            sessionFactory.close();
        }*/



        System.out.println("hola");
    }
}
