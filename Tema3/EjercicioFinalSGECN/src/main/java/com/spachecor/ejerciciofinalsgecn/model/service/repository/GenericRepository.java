package com.spachecor.ejerciciofinalsgecn.model.service.repository;

import com.spachecor.ejerciciofinalsgecn.model.entity.Entidad;
import org.hibernate.Session;

import java.util.List;

/**
 * Clase que define el comportamiento del repositorio. Sus métodos tendrán acceso directo a la base de datos.
 * Sus métodos se usarán a través del servicio correspondiente y no se podrán usar directamente.
 * @param <T> entidad que herede de Entidad
 * @see GenericRepositoryService
 * @author Selene
 * @version 1.0
 */
public class GenericRepository<T extends Entidad>{
    private Class<T> entityClass;

    GenericRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Método que lista todas las entidades de la tabla a la que pertenece la entidad
     * @param session la sesion actual para realizar la operacion
     * @return devuelve un objeto List de T con la lista de entidades encontradas
     */
    List<T> listar(Session session){
        return session.createQuery("from "+this.entityClass.getSimpleName()).getResultList();
    }

    /**
     * Método que obtiene una entidad en concreto que coincida con el id indicado
     * @param session la sesion actual para realizar la operacion
     * @param id El id de la entidad solicitada
     * @return La entidad solicitada o null si no la encuentra
     */
    T porId(Session session, Integer id){
        return session.find(this.entityClass, id);
    }
    /**
     * Método que comprueba si la entidad pasada existe en la base de datos. Si existe, la
     * actualiza, si no, la crea en el repositorio
     * @param session la sesion actual para realizar la operacion
     * @param t La entidad a actualizar/crear
     */
    void guardar(Session session, T t){
        if(t.getId() != null&&t.getId()>0&&porId(session, t.getId())!=null)session.merge(t);
        else session.persist(t);
    }

    /**
     * Método que elimina la entidad que coincide con el id indicado
     * @param session la sesion actual para realizar la operacion
     * @param t La entidad a eliminar
     */
    void eliminar(Session session, T t){
        session.remove(t);
    }

}
