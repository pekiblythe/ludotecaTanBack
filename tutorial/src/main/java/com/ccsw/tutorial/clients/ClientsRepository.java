package com.ccsw.tutorial.clients;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.tutorial.clients.model.Clients;

/**
 * @author ccsw
 *
 */
public interface ClientsRepository extends CrudRepository<Clients, Long> {

    Clients findByName(String name);
}
