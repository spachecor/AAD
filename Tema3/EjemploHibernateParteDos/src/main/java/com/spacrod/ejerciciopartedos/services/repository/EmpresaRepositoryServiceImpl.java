package com.spacrod.ejerciciopartedos.services.repository;

import com.spacrod.ejerciciopartedos.Empresa;
import com.spacrod.ejerciciopartedos.services.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class EmpresaRepositoryServiceImpl implements CRUDRepositoryService<Empresa>{
    private final EmpresaRepositoryImpl empresaRepository;
    private final Session session;
    public EmpresaRepositoryServiceImpl() {
        this.session = HibernateUtil.getSession();
        this.empresaRepository = new EmpresaRepositoryImpl(session);
    }
    @Override
    public Optional<Empresa> findById(Integer id) {
        return Optional.ofNullable(this.empresaRepository.findById(id));
    }

    @Override
    public List<Empresa> findAll() {
        return this.empresaRepository.findAll();
    }

    @Override
    public Boolean save(Empresa empresa) {
        try{
            this.session.beginTransaction();
            this.empresaRepository.save(empresa);
            this.session.getTransaction().commit();
            return true;
        }catch (Exception e) {
            this.session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public Boolean delete(Empresa empresa) {
        try{
            this.session.beginTransaction();
            this.empresaRepository.delete(empresa);
            this.session.getTransaction().commit();
            return true;
        }catch (Exception e) {
            this.session.getTransaction().rollback();
            return false;
        }
    }
    @Override
    public void destruct() {
        this.session.close();
    }
}
