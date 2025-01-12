package com.spachecor.ejerciciofinalsgecn;

import com.spachecor.ejerciciofinalsgecn.model.entity.*;
import com.spachecor.ejerciciofinalsgecn.model.service.repository.GenericRepositoryService;
import com.spachecor.ejerciciofinalsgecn.model.service.repository.ServiceUtil;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestCuatro {
    static GenericRepositoryService<Estudiante> estudianteGenericRepositoryService = new GenericRepositoryService<>(Estudiante.class);
    static GenericRepositoryService<Curso> cursoGenericRepositoryService = new GenericRepositoryService<>(Curso.class);
    static GenericRepositoryService<Nota> notaGenericRepositoryService = new GenericRepositoryService<>(Nota.class);
    public static void main(String[] args) throws InterruptedException {
        insertarDatos();
        TestCuatro.listarTodo();
        System.out.println("-------------------------------------------------------------");
        Thread.sleep(500);
        TestCuatro.listarTodo();
    }
    static void listarTodo(){
        List<Estudiante> estudianteList = estudianteGenericRepositoryService.listar();
        for (Estudiante estudianteTemp : estudianteList) {
            System.out.println(estudianteTemp);
        }
        List<Curso> cursoList = cursoGenericRepositoryService.listar();
        for (Curso cursoTemp : cursoList) {
            System.out.println(cursoTemp);
        }
        List<Nota> notaList = notaGenericRepositoryService.listar();
        for (Nota notaTemp : notaList) {
            System.out.println(notaTemp);
        }
    }
    static void insertarDatos(){
        try{
            // Crear estudiantes
            Estudiante juan = new Estudiante();
            juan.setNombre("Juan");
            juan.setApellido("Carmona");
            juan.setEmail("juan@gmail.com");

            Estudiante selene = new Estudiante();
            selene.setNombre("Selene");
            selene.setApellido("Pacheco");
            selene.setEmail("selene@gmail.com");

            // Crear curso
            Curso dam = new Curso();
            dam.setNombre("2º DAM");
            dam.setDescripcion("Desarrollo de Aplicaciones Multiplataforma");

            // Crear notas
            Nota notaJuan = new Nota();
            notaJuan.setValor(10D);
            notaJuan.setFecha(LocalDate.now());
            notaJuan.setEstudiante(juan);
            notaJuan.setCurso(dam);

            Nota notaSelene = new Nota();
            notaSelene.setValor(5D);
            notaSelene.setFecha(LocalDate.now());
            notaSelene.setEstudiante(selene);
            notaSelene.setCurso(dam);

            // Establecer relaciones bidireccionales
            dam.getEstudiantes().add(juan);

            juan.getCursos().add(dam);

            dam.getNotas().add(notaJuan);

            juan.getNotas().add(notaJuan);

            // Persistir curso
            cursoGenericRepositoryService.guardar(dam);
            dam.getEstudiantes().add(selene);
            selene.getCursos().add(dam);
            dam.getNotas().add(notaSelene);
            selene.getNotas().add(notaSelene);
            cursoGenericRepositoryService.guardar(dam);
            estudianteGenericRepositoryService.guardar(selene);
            notaGenericRepositoryService.guardar(notaSelene);


            Estudiante pamela = new Estudiante();
            pamela.setNombre("Pamela");
            pamela.setApellido("Castillo");
            pamela.setEmail("pamela@gmail.com");

            Estudiante rodolfo = new Estudiante();
            rodolfo.setNombre("Rodolfo");
            rodolfo.setApellido("Gomez");
            rodolfo.setEmail("rodolfo@gmail.com");
            estudianteGenericRepositoryService.guardar(pamela);
            estudianteGenericRepositoryService.guardar(rodolfo);
            System.out.println("hola");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
