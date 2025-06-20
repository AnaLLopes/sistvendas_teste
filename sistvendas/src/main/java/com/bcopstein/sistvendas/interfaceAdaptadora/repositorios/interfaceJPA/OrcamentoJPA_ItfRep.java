package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.interfaceJPA;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades.Orcamento;

// aqui define e gera automaticamente o repositorio, usand as classes anotadas pra saber oq colocar no banco
// as classes q estao na pasta entidade
public interface OrcamentoJPA_ItfRep extends CrudRepository<Orcamento,Long>{
    List<Orcamento> findAll();
    Optional<Orcamento> findById(long id);
}
