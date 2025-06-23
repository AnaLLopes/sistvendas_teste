package com.bcopstein.sistvendas.aplicacao.casosDeUso;

import com.bcopstein.sistvendas.aplicacao.dtos.RegistroDeVendaDTO;
import com.bcopstein.sistvendas.aplicacao.dtos.OrcamentoDTO;
import com.bcopstein.sistvendas.dominio.modelos.OrcamentoModel;
import com.bcopstein.sistvendas.dominio.servicos.ServicoDeVendas;
import com.bcopstein.sistvendas.infra.VendaPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EfetivaOrcamentoUC {

    private final ServicoDeVendas servicoDeVendas;
    private final VendaPublisher vendaPublisher;

    @Autowired
    public EfetivaOrcamentoUC(ServicoDeVendas servicoDeVendas, VendaPublisher vendaPublisher) {
        this.servicoDeVendas = servicoDeVendas;
        this.vendaPublisher = vendaPublisher;
    }

    public OrcamentoDTO run(long idOrcamento) {
        OrcamentoModel orcamento = servicoDeVendas.efetivaOrcamento(idOrcamento);
        if (orcamento == null) {
            throw new IllegalArgumentException("Não foi possível efetivar o orçamento");
        }

        RegistroDeVendaDTO registro = new RegistroDeVendaDTO(
            LocalDate.now(),
            orcamento.getCustoConsumidor(),
            orcamento.getImpostoFederal() + orcamento.getImpostoRegional()
        );
        vendaPublisher.enviarVenda(registro);

        return OrcamentoDTO.fromModel(orcamento);
    }
}
