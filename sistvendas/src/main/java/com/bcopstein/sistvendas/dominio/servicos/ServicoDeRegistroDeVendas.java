package com.bcopstein.sistvendas.dominio.servicos;

import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades.RegistroDeVenda;
import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.interfaceJPA.RegistroDeVendaJPA_ItfRep;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class ServicoDeRegistroDeVendas {

    private final RegistroDeVendaJPA_ItfRep repo;

    public ServicoDeRegistroDeVendas(RegistroDeVendaJPA_ItfRep repo) {
        this.repo = repo;
    }

    public RegistroDeVenda salvar(RegistroDeVenda registro) {
        return repo.save(registro);
    }

    public TotaisMesDTO totaisDoMes(int ano, int mes) {
        YearMonth ym = YearMonth.of(ano, mes);
        List<RegistroDeVenda> registros = (List<RegistroDeVenda>) repo.findAll();

        BigDecimal totalVendido = registros.stream()
            .filter(r -> YearMonth.from(r.getData()).equals(ym))
            .map(RegistroDeVenda::getValorVenda)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalImpostos = registros.stream()
            .filter(r -> YearMonth.from(r.getData()).equals(ym))
            .map(RegistroDeVenda::getValorImposto)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new TotaisMesDTO(totalVendido, totalImpostos);
    }

    public static class TotaisMesDTO {
        private BigDecimal totalVendido;
        private BigDecimal totalImpostos;

        public TotaisMesDTO(BigDecimal totalVendido, BigDecimal totalImpostos) {
            this.totalVendido = totalVendido;
            this.totalImpostos = totalImpostos;
        }

        public BigDecimal getTotalVendido() {
            return totalVendido;
        }

        public BigDecimal getTotalImpostos() {
            return totalImpostos;
        }
    }

}
