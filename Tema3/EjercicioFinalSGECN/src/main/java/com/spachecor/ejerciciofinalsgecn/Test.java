package com.spachecor.ejerciciofinalsgecn;

import com.spachecor.ejerciciofinalsgecn.model.entity.Curso;
import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import com.spachecor.ejerciciofinalsgecn.model.entity.Nota;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        
        Estudiante jose = new Estudiante();
        jose.setNombre("Jose");
        jose.setApellido("Gómez");
        jose.setEmail("jose@gmail.com");

        Curso informaticaUno = new Curso();
        informaticaUno.setNombre("Informatica Uno");
        informaticaUno.setDescripcion("Informatica Uno - descripcion");

        jose.getCursos().add(informaticaUno);
        informaticaUno.getEstudiantes().add(jose);

        Curso informaticaDos = new Curso();
        informaticaDos.setNombre("Informatica Dos");
        informaticaDos.setDescripcion("Informatica Dos - descripcion");

        jose.getCursos().add(informaticaDos);
        informaticaDos.getEstudiantes().add(jose);

        Nota notaJoseInformaticaUno = new Nota();
        notaJoseInformaticaUno.setFecha(LocalDate.now());
        notaJoseInformaticaUno.setValor(8D);
        notaJoseInformaticaUno.setEstudiante(jose);
        notaJoseInformaticaUno.setCurso(informaticaUno);
        informaticaUno.getNotas().add(notaJoseInformaticaUno);

        Nota notaJoseInformaticaDos = new Nota();
        notaJoseInformaticaDos.setFecha(LocalDate.now());
        notaJoseInformaticaDos.setValor(8.5D);
        notaJoseInformaticaDos.setEstudiante(jose);
        notaJoseInformaticaDos.setCurso(informaticaDos);
        informaticaDos.getNotas().add(notaJoseInformaticaDos);

        System.out.println("hola");
    }
}
