package com.spachecor.ejerciciofinalsgecn.model.service.repository;

import com.spachecor.ejerciciofinalsgecn.model.entity.Entidad;
import org.hibernate.Session;

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
    private Session session;
    private GenericRepository<T> repository;

    public GenericRepositoryService(Session session, Class<T> entityClass) {
        this.session = session;
        this.repository = new GenericRepository<>(session, entityClass);
    }

    /**
     * Método que lista todas las entidades de la tabla a la que pertenezca la entidad t
     * @return Un objeto de tipo List con una lista de entidades encontradas
     */
    public List<T> listar() {
        return this.repository.listar();
    }

    /**
     * Método que busca una entidad por el id proporcionado
     * @param id El id de la entidad a buscar
     * @return Un objeto Optional que englobará la entidad encontrada o null
     */
    public Optional<T> buscarPorId(Integer id) {
        return Optional.ofNullable(this.repository.porId(id));
    }

    /**
     * Método que guarda o actualiza la entidad que se le pase. Se realiza la transacción.
     * @param t La entidad a actualizar/guardar
     */
    public void guardar(T t) {
        try{
            this.session.getTransaction().begin();
            this.repository.guardar(t);
            this.session.getTransaction().commit();
        }catch (Exception e) {
            this.session.getTransaction().rollback();
        }
    }

    /**
     * Método que elimina la entidad cuyo id coincida con el proporcionado
     * @param id El id de la enitidad a eliminar
     */
    public void eliminar(Integer id) {
        try{
            this.session.getTransaction().begin();
            this.repository.eliminar(id);
            this.session.getTransaction().commit();
        }catch (Exception e) {
            this.session.getTransaction().rollback();
        }
    }
}
