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
    private Session session;
    private Class<T> entityClass;

    GenericRepository(Session session, Class<T> entityClass) {
        this.session = session;
        this.entityClass = entityClass;
    }

    /**
     * Método que lista todas las entidades de la tabla a la que pertenece la entidad
     * @return devuelve un objeto List de T con la lista de entidades encontradas
     */
    List<T> listar(){
        return this.session.createQuery("select t from "+this.entityClass.getSimpleName()+" t").getResultList();
    }

    /**
     * Método que obtiene una entidad en concreto que coincida con el id indicado
     * @param id El id de la entidad solicitada
     * @return La entidad solicitada o null si no la encuentra
     */
    T porId(Integer id){
        return this.session.find(this.entityClass, id);
    }
    /**
     * Método que comprueba si la entidad pasada existe en la base de datos. Si existe, la
     * actualiza, si no, la crea en el repositorio
     * @param t La entidad a actualizar/crear
     */
    void guardar(T t){
        if(t.getId() != null&&t.getId()>0&&porId(t.getId())!=null)this.session.merge(t);
        else this.session.persist(t);
    }

    /**
     * Método que elimina la entidad que coincide con el id indicado
     * @param id El id de la entidad a eliminar
     */
    void eliminar(Integer id){
        this.session.remove(id);
    }

}
