package com.ccsw.tutorial.prestamo.model;

import java.time.LocalDate;

import com.ccsw.tutorial.clients.model.ClientsDto;
import com.ccsw.tutorial.game.model.GameDto;

/**
 * @author ccsw
 *
 */
public class PrestamoDto {

    private Long id;

    private GameDto game;

    private ClientsDto clients;

    private LocalDate datein;

    private LocalDate dateout;

    /**
     * @return id
     */

    public Long getId() {
        return id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return game
     */
    public GameDto getGame() {
        return game;
    }

    /**
     * @param game new value of {@link #getGame}.
     */

    public void setGame(GameDto game) {
        this.game = game;
    }

    /**
     * @return clients
     */

    public ClientsDto getClients() {
        return clients;
    }

    /**
     * @param clients new value of {@link #getClients}.
     */

    public void setClients(ClientsDto clients) {
        this.clients = clients;
    }

    /**
     * @return datein
     */

    public LocalDate getDatein() {
        return datein;
    }

    /**
     * @param datein new value of {@link #getDatein}.
     */

    public void setDatein(LocalDate datein) {
        this.datein = datein;
    }

    /**
     * @return dateout
     */

    public LocalDate getDateout() {
        return dateout;
    }

    /**
     * @param dateout new value of {@link #getDateout}.
     */

    public void setDateout(LocalDate dateout) {
        this.dateout = dateout;
    }

}