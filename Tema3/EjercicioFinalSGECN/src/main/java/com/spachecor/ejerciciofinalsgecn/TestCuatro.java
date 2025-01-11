package com.spachecor.ejerciciofinalsgecn;

import com.spachecor.ejerciciofinalsgecn.model.entity.*;
import com.spachecor.ejerciciofinalsgecn.model.service.repository.GenericRepositoryService;
import com.spachecor.ejerciciofinalsgecn.model.service.repository.ServiceUtil;
import org.hibernate.Session;

import java.time.LocalDate;
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
        Estudiante juan = new Estudiante();
        juan.setNombre("Juan");
        juan.setApellido("Carmona");
        juan.setEmail("juan@gmail.com");
        Estudiante selene = new Estudiante();
        selene.setNombre("Selene");
        selene.setApellido("Pacheco");
        selene.setEmail("selene@gmail.com");
        Curso dam = new Curso();
        dam.setNombre("2º DAM");
        dam.setDescripcion("Desarrollo de Aplicaciones Multiplataforma");
        Nota notaJuan = new Nota();
        notaJuan.setValor(10D);
        notaJuan.setFecha(LocalDate.now());
        Nota notaSelene = new Nota();
        notaSelene.setValor(5D);
        notaSelene.setFecha(LocalDate.now());

        notaJuan.setEstudiante(juan);
        notaJuan.setCurso(dam);
        notaSelene.setEstudiante(selene);
        notaSelene.setCurso(dam);

        dam.getEstudiantes().add(juan);
        dam.getEstudiantes().add(selene);
        dam.getNotas().add(notaJuan);
        dam.getNotas().add(notaSelene);

        juan.getNotas().add(notaJuan);
        selene.getNotas().add(notaSelene);
        juan.getCursos().add(dam);
        selene.getCursos().add(dam);

        cursoGenericRepositoryService.guardar(dam);
    }
}
