package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.interfaceJPA;

import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades.RegistroDeVenda;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegistroDeVendaJPA_ItfRep extends CrudRepository<RegistroDeVenda, Long> {
    
    // Método para buscar registros filtrando pelo mês e ano da data
    List<RegistroDeVenda> findByDataYearAndDataMonth(int ano, int mes);

    // Caso o método acima não funcione, pode ser necessário criar query manual com @Query
}