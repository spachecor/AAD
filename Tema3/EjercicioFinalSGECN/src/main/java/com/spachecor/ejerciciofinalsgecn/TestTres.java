package com.spachecor.ejerciciofinalsgecn;

import com.spachecor.ejerciciofinalsgecn.model.entity.Curso;
import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import com.spachecor.ejerciciofinalsgecn.model.entity.Nota;
import com.spachecor.ejerciciofinalsgecn.model.service.repository.GenericRepositoryService;
import com.spachecor.ejerciciofinalsgecn.model.service.repository.ServiceUtil;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public class TestTres {
    public static void main(String[] args) {
        GenericRepositoryService<Curso> cursoGenericRepositoryService = new GenericRepositoryService<>(Curso.class);
        GenericRepositoryService<Estudiante> estudianteGenericRepositoryService = new GenericRepositoryService<>(Estudiante.class);
        GenericRepositoryService<Nota> notaGenericRepositoryService = new GenericRepositoryService<>(Nota.class);

        //insertamos datos
        /*Estudiante juan  = new Estudiante();
        juan.setNombre("Juan Domingo");
        juan.setApellido("Carmona Ostos");
        juan.setEmail("juando90@gmail.com");

        Estudiante selene = new Estudiante();
        selene.setNombre("Selene");
        selene.setApellido("Pacheco Rodríguez");
        selene.setEmail("spachecor03@gmail.com");

        Curso curso = new Curso();
        curso.setNombre("2º DAM");
        curso.setDescripcion("Segundo de Desarrollo de Aplicaciones Multiplataforma");

        Nota notaJuan = new Nota();
        notaJuan.setFecha(LocalDate.now());
        notaJuan.setValor(8D);

        Nota notaSelene = new Nota();
        notaSelene.setFecha(LocalDate.now());
        notaSelene.setValor(10D);

        juan.getCursos().add(curso);
        selene.getCursos().add(curso);

        curso.getNotas().add(notaJuan);
        curso.getNotas().add(notaSelene);
        curso.getEstudiantes().add(juan);
        curso.getEstudiantes().add(selene);

        notaJuan.setCurso(curso);
        notaSelene.setCurso(curso);

        cursoGenericRepositoryService.guardar(curso);*/
        List<Curso> cursos = cursoGenericRepositoryService.listar();
        cursos.forEach(System.out::println);
        List<Estudiante> estudiantes = estudianteGenericRepositoryService.listar();
        estudiantes.forEach(System.out::println);
        List<Nota> notas = notaGenericRepositoryService.listar();
        notas.forEach(System.out::println);
        System.out.println("Hola");
    }
}
