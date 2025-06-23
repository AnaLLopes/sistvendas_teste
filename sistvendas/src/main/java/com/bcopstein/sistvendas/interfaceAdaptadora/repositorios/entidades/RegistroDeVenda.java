package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
//@Table(name = "registro_venda")
public class RegistroDeVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    private BigDecimal valorVenda;

    private BigDecimal valorImposto;

    public RegistroDeVenda() {}

    public RegistroDeVenda(LocalDate data, BigDecimal valorVenda, BigDecimal valorImposto) {
        this.data = data;
        this.valorVenda = valorVenda;
        this.valorImposto = valorImposto;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public BigDecimal getValorImposto() {
        return valorImposto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public void setValorImposto(BigDecimal valorImposto) {
        this.valorImposto = valorImposto;
    }
}
