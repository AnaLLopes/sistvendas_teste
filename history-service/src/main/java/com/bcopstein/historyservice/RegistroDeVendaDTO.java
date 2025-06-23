package com.bcopstein.historyservice;

import java.time.LocalDate;
import java.math.BigDecimal;

public class RegistroDeVendaDTO {
    private LocalDate data;
    private BigDecimal valorVenda;
    private BigDecimal valorImposto;

    public RegistroDeVendaDTO() {}

    public RegistroDeVendaDTO(LocalDate data, BigDecimal valorVenda, BigDecimal valorImposto) {
        this.data = data;
        this.valorVenda = valorVenda;
        this.valorImposto = valorImposto;
    }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public BigDecimal getValorVenda() { return valorVenda; }
    public void setValorVenda(BigDecimal valorVenda) { this.valorVenda = valorVenda; }

    public BigDecimal getValorImposto() { return valorImposto; }
    public void setValorImposto(BigDecimal valorImposto) { this.valorImposto = valorImposto; }

    @Override
    public String toString() {
        return "RegistroDeVendaDTO [data=" + data + ", valorVenda=" + valorVenda + ", valorImposto=" + valorImposto + "]";
    }
}
