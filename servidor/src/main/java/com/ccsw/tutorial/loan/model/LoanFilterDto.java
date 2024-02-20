package com.ccsw.tutorial.loan.model;

import java.time.LocalDate;

public class LoanFilterDto {

    private Long idGame;
    private Long idClient;
    private LocalDate filteredDate;

    /**
     * @return game
     */
    public Long getIdGame() {

        return this.idGame;
    }

    /**
     * @param game new value of {@link #getGame}.
     */
    public void setIdGame(Long idGame) {

        this.idGame = idGame;
    }

    /**
     * @return client
     */
    public Long getIdClient() {

        return this.idClient;
    }

    /**
     * @param client new value of {@link #getClient}.
     */
    public void setIdClient(Long idClient) {

        this.idClient = idClient;
    }

    /**
     * @return init_date
     */
    public LocalDate getFilteredDate() {

        return this.filteredDate;
    }

    /**
     * @param init_date new value of {@link #getInitDate}.
     */
    public void setFilteredDate(LocalDate filteredDate) {

        this.filteredDate = filteredDate;
    }
}
