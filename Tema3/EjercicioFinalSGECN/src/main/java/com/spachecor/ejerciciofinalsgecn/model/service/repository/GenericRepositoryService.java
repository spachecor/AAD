package com.spachecor.ejerciciofinalsgecn.model.service.repository;

import com.spachecor.ejerciciofinalsgecn.model.entity.Entidad;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase que define los métodos con los que se realizará el CRUD con la base de datos. Paso final para acceder.
 * Hace uso del repositorio para el acceso.
 * @param <T> entidad que herede de Entidad
 * @see GenericRepository
 * @author Selene
 * @version 1.0
 */
public class GenericRepositoryService<T extends Entidad> {
    private final GenericRepository<T> repository;

    public GenericRepositoryService(Class<T> entityClass) {
        this.repository = new GenericRepository<>(entityClass);
    }

    /**
     * Método que lista todas las entidades de la tabla a la que pertenezca la entidad t
     * @return Un objeto de tipo List con una lista de entidades encontradas
     */
    public List<T> listar() {
        Session session = ServiceUtil.getSession();
        List<T> list = new ArrayList<>();
        try{
            session.beginTransaction();
            list = this.repository.listar(session);
            session.getTransaction().commit();
        }finally {
            return list;
        }
    }

    /**
     * Método que busca una entidad por el id proporcionado
     * @param id El id de la entidad a buscar
     * @return Un objeto Optional que englobará la entidad encontrada o null
     */
    public Optional<T> buscarPorId(Integer id) {
        Session session = ServiceUtil.getSession();
        Optional<T> result = Optional.empty();
        try {
            session.beginTransaction();
            result = Optional.ofNullable(this.repository.porId(session, id));
            session.getTransaction().commit();
        }finally {
            return result;
        }
    }

    /**
     * Método que guarda o actualiza la entidad que se le pase. Se realiza la transacción.
     * @param t La entidad a actualizar/guardar
     */
    public void guardar(T t) {
        Session session = ServiceUtil.getSession();
        try{
            session.getTransaction().begin();
            repository.guardar(session, t);
            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    /**
     * Método que elimina la entidad cuyo id coincida con el proporcionado
     * @param t La enitidad a eliminar
     */
    public void eliminar(T t) {
        Session session = ServiceUtil.getSession();
        try{
            session.getTransaction().begin();
            this.repository.eliminar(session, t);
            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
