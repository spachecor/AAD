package com.spacrod.ejerciciopartedos.services.repository;

import com.spacrod.ejerciciopartedos.Estudiante;
import com.spacrod.ejerciciopartedos.services.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class EstudianteRepositoryServiceImpl implements CRUDRepositoryService<Estudiante> {
    private final EstudianteRepositoryImpl estudianteRepository;
    private final Session session;
    public EstudianteRepositoryServiceImpl() {
        this.session = HibernateUtil.getSession();
        this.estudianteRepository = new EstudianteRepositoryImpl(session);
    }
    @Override
    public Optional<Estudiante> findById(Integer id) {
        return Optional.ofNullable(this.session.get(Estudiante.class, id));
    }

    @Override
    public List<Estudiante> findAll() {
        return this.estudianteRepository.findAll();
    }

    @Override
    public Boolean save(Estudiante estudiante) {
        try{
            this.session.beginTransaction();
            this.estudianteRepository.save(estudiante);
            this.session.getTransaction().commit();
            return true;
        }catch(Exception e){
            this.session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public Boolean delete(Estudiante estudiante) {
        try{
            this.session.beginTransaction();
            this.estudianteRepository.delete(estudiante);
            this.session.getTransaction().commit();
            return true;
        }catch(Exception e){
            this.session.getTransaction().rollback();
            return false;
        }
    }
    @Override
    public void destruct() {
        this.session.close();
    }
}
