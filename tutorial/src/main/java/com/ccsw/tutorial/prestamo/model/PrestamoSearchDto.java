package com.ccsw.tutorial.prestamo.model;

import java.time.LocalDate;

import com.ccsw.tutorial.common.pagination.PageableRequest;

/**
 * @author ccsw
 *
 */
public class PrestamoSearchDto {

    private Long gameId;
    private Long clientsId;
    private LocalDate datein;
    private PageableRequest pageable;

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getClientsId() {
        return clientsId;
    }

    public void setClientsId(Long clientsId) {
        this.clientsId = clientsId;
    }

    public LocalDate getDatein() {
        return datein;
    }

    public void setDatein(LocalDate datein) {
        this.datein = datein;
    }

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }
}
