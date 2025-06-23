package com.bcopstein.historyservice;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistroDeVendaJPA_ItfRep extends CrudRepository<RegistroDeVenda, Long> {
    
    @Query("SELECT r FROM RegistroDeVenda r WHERE YEAR(r.data) = :ano AND MONTH(r.data) = :mes")
    List<RegistroDeVenda> findByDataYearAndDataMonth(@Param("ano") int ano, @Param("mes") int mes);

}
