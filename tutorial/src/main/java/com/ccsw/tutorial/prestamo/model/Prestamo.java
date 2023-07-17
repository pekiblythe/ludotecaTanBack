package com.ccsw.tutorial.prestamo.model;

import java.time.LocalDate;

import com.ccsw.tutorial.clients.model.Clients;
import com.ccsw.tutorial.game.model.Game;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author ccsw
 *
 */
@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "clients_id", nullable = false)
    private Clients clients;

    @Column(name = "datein", nullable = false)
    private LocalDate datein;

    @Column(name = "dateout", nullable = false)
    private LocalDate dateout;

    /**
     * @return id
     */
    public Long getId() {

        return this.id;
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
    public Game getGame() {

        return this.game;
    }

    /**
     * @param game new value of {@link #getGame}.
     */
    public void setGame(Game game) {

        this.game = game;
    }

    /**
     * @return clients
     */
    public Clients getClients() {

        return this.clients;
    }

    /**
     * @param clients new value of {@link #getClient}.
     */
    public void setClients(Clients clients) {

        this.clients = clients;
    }

    /**
     * @return datein
     */
    public LocalDate getDatein() {

        return this.datein;
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

        return this.dateout;
    }

    /**
     * @param dateout new value of {@link #getDateout}.
     */
    public void setDateout(LocalDate dateout) {

        this.dateout = dateout;
    }

}