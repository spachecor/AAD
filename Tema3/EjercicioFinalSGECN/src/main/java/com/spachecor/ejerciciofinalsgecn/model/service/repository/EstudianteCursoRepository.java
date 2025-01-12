package com.spachecor.ejerciciofinalsgecn.model.service.repository;

import com.spachecor.ejerciciofinalsgecn.model.entity.Curso;
import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import org.hibernate.Session;

/**
 * Clase EstudianteCursoRepository, que define el método para eliminar definitivamente un estudiante de un curso en concreto
 * @author Selene
 * @version 1.0
 */
public class EstudianteCursoRepository {
    private Session session;
    public EstudianteCursoRepository() {
        this.session = null;
    }

    /**
     * Método que elimina un estudiante de un curso en concreto.
     * @param estudiante El estudiante a eliminar
     * @param curso El curso del que eliminar el estudiante
     */
    public void eliminarEstudianteDeCurso(Estudiante estudiante, Curso curso){
        this.session = ServiceUtil.getSession();
        try{
            estudiante.getCursos().remove(curso);
            curso.getEstudiantes().remove(estudiante);
            session.beginTransaction();
            session.createNativeQuery("delete from estudiante_curso where id_estudiante = "+estudiante.getId()+" and id_curso = "+curso.getId()).executeUpdate();
            session.getTransaction().commit();
            new GenericRepositoryService<>(Estudiante.class).guardar(estudiante);
        }catch(Exception e){
            session.getTransaction().rollback();
        }
    }
}
