package com.ccsw.tutorial.prestamo;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.tutorial.clients.model.Clients;
import com.ccsw.tutorial.game.model.Game;
import com.ccsw.tutorial.prestamo.model.Prestamo;

/**
 * @author ccsw
 *
 */
public interface PrestamoRepository extends CrudRepository<Prestamo, Long>, JpaSpecificationExecutor<Prestamo> {

    /**
     * Método para recuperar un listado paginado de {@link Prestamo}
     *
     * @param pageable pageable
     * @return {@link Page} de {@link Prestamo}
     */
    // Page<Prestamo> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = { "game", "clients", "datein" })
    Page<Prestamo> findAll(Specification<Prestamo> spec, Pageable pageable);

    int countAllByGameAndDateinGreaterThanEqualAndDateinLessThanEqualAndDateoutGreaterThanEqualAndDateoutGreaterThanEqual(
            Game game, LocalDate datein, LocalDate dateout, LocalDate datein2, LocalDate dateout2);

    int countAllByGameAndDateinLessThanEqualAndDateinLessThanEqualAndDateoutGreaterThanEqualAndDateoutLessThanEqual(
            Game game, LocalDate datein, LocalDate dateout, LocalDate datein2, LocalDate dateout2);

    int countAllByGameAndDateinLessThanEqualAndDateinLessThanEqualAndDateoutGreaterThanEqualAndDateoutGreaterThanEqual(
            Game game, LocalDate datein, LocalDate dateout, LocalDate datein2, LocalDate dateout2);

    int countAllByGameAndDateinGreaterThanEqualAndDateinLessThanEqualAndDateoutGreaterThanEqualAndDateoutLessThanEqual(
            Game game, LocalDate datein, LocalDate dateout, LocalDate datein2, LocalDate dateout2);

    int countAllByClientsAndDateinGreaterThanEqualAndDateinLessThanEqualAndDateoutGreaterThanEqualAndDateoutGreaterThanEqual(
            Clients client, LocalDate datein, LocalDate dateout, LocalDate datein2, LocalDate dateout2);

    int countAllByClientsAndDateinLessThanEqualAndDateinLessThanEqualAndDateoutGreaterThanEqualAndDateoutLessThanEqual(
            Clients client, LocalDate datein, LocalDate dateout, LocalDate datein2, LocalDate dateout2);

    int countAllByClientsAndDateinLessThanEqualAndDateinLessThanEqualAndDateoutGreaterThanEqualAndDateoutGreaterThanEqual(
            Clients client, LocalDate datein, LocalDate dateout, LocalDate datein2, LocalDate dateout2);

    int countAllByClientsAndDateinGreaterThanEqualAndDateinLessThanEqualAndDateoutGreaterThanEqualAndDateoutLessThanEqual(
            Clients client, LocalDate datein, LocalDate dateout, LocalDate datein2, LocalDate dateout2);

}
