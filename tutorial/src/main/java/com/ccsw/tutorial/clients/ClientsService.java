package com.ccsw.tutorial.clients;

import java.util.List;

import com.ccsw.tutorial.clients.model.Clients;
import com.ccsw.tutorial.clients.model.ClientsDto;

/**
 * @author ccsw
 *
 */
public interface ClientsService {

    /**
     * Recupera una {@link Clients} a partir de su ID
     *
     * @param id PK de la entidad
     * @return {@link Clients}
     */
    Clients get(Long id);

    /**
     * Método para recuperar todas las {@link Clients}
     *
     * @return {@link List} de {@link Clients}
     */
    List<Clients> findAll();

    /**
     * Método para crear o actualizar una {@link Clients}
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, ClientsDto dto) throws Exception;

    /**
     * Método para borrar una {@link Clients}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

}