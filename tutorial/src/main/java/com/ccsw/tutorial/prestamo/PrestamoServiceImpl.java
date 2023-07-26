package com.ccsw.tutorial.prestamo;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.clients.ClientsService;
import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;

import jakarta.transaction.Transactional;

/**
 * @prestamo ccsw
 *
 */
@Service
@Transactional
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    PrestamoRepository prestamoRepository;

    @Autowired
    GameService gameService;

    @Autowired
    ClientsService clientsService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Prestamo get(Long id) {

        return this.prestamoRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    /*
     * @Override public Page<Prestamo> findPage(PrestamoSearchDto dto) {
     * 
     * return this.prestamoRepository.findAll(dto.getPageable().getPageable()); }
     */
    /**
     * {@inheritDoc}
     * 
     * @throws Exception
     */
    @Override
    public void save(Long id, PrestamoDto dto) throws Exception {

        Prestamo prestamo;

        if (id == null) {
            prestamo = new Prestamo();
        } else {
            prestamo = this.prestamoRepository.findById(id).orElse(null);
        }

        BeanUtils.copyProperties(dto, prestamo, "id", "game", "clients");

        prestamo.setGame(gameService.get(dto.getGame().getId()));
        prestamo.setClients(clientsService.get(dto.getClients().getId()));

        if (dto.getDatein().isAfter(dto.getDateout())) {
            throw new Exception("La fecha de fin no puede ser anterior a la de inicio");
        } else if ((dto.getDateout().compareTo(dto.getDatein())) >= 14) {
            throw new Exception("El periodo de préstamo máximo solo puede ser de 14 días");
        } else {
            this.prestamoRepository.save(prestamo);
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

        this.prestamoRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    /*
     * @Override public List<Prestamo> findAll() {
     * 
     * return (List<Prestamo>) this.prestamoRepository.findAll(); }
     */

    @Override
    public Page<Prestamo> findPage(String game_id, Long clients_id, LocalDate datein, PrestamoSearchDto dto) {

        PrestamoSpecification titleSpec = new PrestamoSpecification(new SearchCriteria("game.id", ":", game_id));
        PrestamoSpecification clientIdSpec = new PrestamoSpecification(
                new SearchCriteria("clients.id", ":", clients_id));
        PrestamoSpecification dateSpec = new PrestamoSpecification(
                new SearchCriteria("datein", "datePrestamo", datein));

        Specification<Prestamo> spec = Specification.where(titleSpec).and(clientIdSpec).and(dateSpec);

        return this.prestamoRepository.findAll(spec, dto.getPageable().getPageable());
    }
}