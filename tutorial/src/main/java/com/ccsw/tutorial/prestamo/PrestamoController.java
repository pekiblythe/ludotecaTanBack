package com.ccsw.tutorial.prestamo;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @prestamo ccsw
 *
 */
@Tag(name = "Prestamo", description = "API of Prestamo")
@RequestMapping(value = "/prestamo")
@RestController
@CrossOrigin(origins = "*")
public class PrestamoController {

    @Autowired
    PrestamoService prestamoService;

    @Autowired
    ModelMapper mapper;

    /**
     * Método para recuperar un listado paginado de {@link Prestamo}
     *
     * @param dto dto de búsqueda
     * @return {@link Page} de {@link PrestamoDto}
     */

    /*
     * @Operation(summary = "Find Page", description =
     * "Method that return a page of Prestamos")
     * 
     * @RequestMapping(path = "", method = RequestMethod.POST) public
     * Page<PrestamoDto> findPage(@RequestBody PrestamoSearchDto dto) {
     * 
     * Page<Prestamo> page = this.prestamoService.findPage(dto);
     * 
     * return new PageImpl<>( page.getContent().stream().map(e -> mapper.map(e,
     * PrestamoDto.class)).collect(Collectors.toList()), page.getPageable(),
     * page.getTotalElements()); }
     */
    /**
     * Método para crear o actualizar un {@link Prestamo}
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    @Operation(summary = "Save or Update", description = "Method that saves or updates a Prestamo")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody PrestamoDto dto)
            throws Exception {

        this.prestamoService.save(id, dto);
    }

    /**
     * Método para crear o actualizar un {@link Prestamo}
     *
     * @param id PK de la entidad
     */
    @Operation(summary = "Delete", description = "Method that deletes a Prestamo")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {

        this.prestamoService.delete(id);
    }

    /**
     * Método para recuperar una lista de {@link Prestamo}
     *
     * @param game_id    id del juego
     * @param clients_id id del cliente
     * @param datein     fecha del prestamo
     * @return {@link List} de {@link Prestamo}
     */
    @Operation(summary = "Find page", description = "Method that return a filtered page of Prestamo")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Page<PrestamoDto> findPage(@RequestBody PrestamoSearchDto dto) {

        Page<Prestamo> page = prestamoService.findPage(dto);
        return new PageImpl<>(
                page.getContent().stream().map(e -> mapper.map(e, PrestamoDto.class)).collect(Collectors.toList()),
                page.getPageable(), page.getTotalElements());
    }

}
