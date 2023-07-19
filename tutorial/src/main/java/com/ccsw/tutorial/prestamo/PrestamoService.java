package com.ccsw.tutorial.prestamo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;

/**
 * @author ccsw
 *
 */
public interface PrestamoService {

    /**
     * Recupera un {@link Prestamo} a través de su ID
     *
     * @param id PK de la entidad
     * @return {@link Prestamo}
     */
    Prestamo get(Long id);

    /**
     * Método para recuperar un listado paginado de {@link Prestamo}
     *
     * @param dto dto de búsqueda
     * @return {@link Page} de {@link Prestamo}
     */
    Page<Prestamo> findPage(PrestamoSearchDto dto);

    /**
     * Método para crear o actualizar un {@link Prestamo}
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     * @throws Exception
     */
    void save(Long id, PrestamoDto dto) throws Exception;

    /**
     * Método para crear o actualizar un {@link Prestamo}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

    /**
     * Recupera un listado de prestamos {@link Prestamo}
     *
     * @return {@link List} de {@link Prestamo}
     */
    List<Prestamo> findAll();

    /**
     * Recupera los prestamos filtrando opcionalmente por título, cliente y/o fecha
     *
     * @param game_id    id del juego
     * @param clients_id id del cliente
     * @param datein     fecha del prestamo
     * @return {@link List} de {@link Prestamo}
     */
    List<Prestamo> find(String game, Long clients_id, LocalDate datein);

}