package com.spacrod.ejerciciopartedos.services.repository;

import com.spacrod.ejerciciopartedos.Estudiante;
import com.spacrod.ejerciciopartedos.services.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class EstudianteRepositoryImpl implements CRUDRepository<Estudiante>{
    private final Session session;
    protected EstudianteRepositoryImpl(Session session) {
        this.session = session;
    }
    @Override
    public Estudiante findById(Integer id) {
        return session.get(Estudiante.class, id);
    }

    @Override
    public List<Estudiante> findAll() {
        return session.createQuery("from Estudiante", Estudiante.class).list();
    }

    @Override
    public void save(Estudiante estudiante) {
        if(estudiante.getId()==null||estudiante.getId()==0)session.persist(estudiante);
        else session.persist(estudiante);
    }

    @Override
    public void delete(Estudiante estudiante) {
        session.remove(estudiante);
    }
}