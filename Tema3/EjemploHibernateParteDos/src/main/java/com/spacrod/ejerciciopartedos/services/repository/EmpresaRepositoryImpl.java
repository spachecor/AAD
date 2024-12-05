package com.spacrod.ejerciciopartedos.services.repository;

import com.spacrod.ejerciciopartedos.Empresa;
import com.spacrod.ejerciciopartedos.services.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class EmpresaRepositoryImpl implements CRUDRepository<Empresa> {
    private final Session session;
    protected EmpresaRepositoryImpl(Session session) {
        this.session = session;
    }
    @Override
    public Empresa findById(Integer id) {
        return session.get(Empresa.class, id);
    }

    @Override
    public List<Empresa> findAll() {
        return session.createQuery("from Empresa", Empresa.class).list();
    }

    @Override
    public void save(Empresa empresa) {
        if (empresa.getId() == null||empresa.getId() == 0)session.persist(empresa);
        else session.merge(empresa);
    }

    @Override
    public void delete(Empresa empresa) {
        session.remove(empresa);
    }
}
