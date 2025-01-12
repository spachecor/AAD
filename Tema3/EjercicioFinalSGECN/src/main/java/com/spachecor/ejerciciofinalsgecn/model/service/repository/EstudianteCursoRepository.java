package com.spachecor.ejerciciofinalsgecn.model.service.repository;

import com.spachecor.ejerciciofinalsgecn.model.entity.Curso;
import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import org.hibernate.Session;

public class EstudianteCursoRepository {
    private Session session;
    public EstudianteCursoRepository() {
        this.session = null;
    }
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
