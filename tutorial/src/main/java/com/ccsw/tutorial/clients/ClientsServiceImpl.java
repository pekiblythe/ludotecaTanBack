package com.ccsw.tutorial.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.clients.model.Clients;
import com.ccsw.tutorial.clients.model.ClientsDto;

import jakarta.transaction.Transactional;

/**
 * @author ccsw
 *
 */
@Service
@Transactional
public class ClientsServiceImpl implements ClientsService {

    @Autowired
    ClientsRepository clientsRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Clients get(Long id) {

        return this.clientsRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Clients> findAll() {

        return (List<Clients>) this.clientsRepository.findAll();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws Exception
     */
    @Override
    public void save(Long id, ClientsDto dto) throws Exception {

        Clients busqueda = this.clientsRepository.findByName(dto.getName());
        Clients clients;

        if (id == null) {
            clients = new Clients();
        } else {
            clients = this.get(id);
        }

        clients.setName(dto.getName());

        if (busqueda == null) {
            this.clientsRepository.save(clients);
        } else {
            throw new Exception();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.clientsRepository.deleteById(id);
    }

}