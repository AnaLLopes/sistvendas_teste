package com.bcopstein.sistvendas.aplicacao.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bcopstein.sistvendas.dominio.modelos.ItemPedidoModel;
import com.bcopstein.sistvendas.dominio.modelos.OrcamentoModel;

public class OrcamentoDTO {
    private long id;
    private List<ItemPedidoDTO> itens;
    private double custoItens;
    private double impostoFederal;
    private double impostoRegional;
    private double desconto;
    private double custoConsumidor;
    private boolean efetivado;
    private String pais;
    private String regiao;
    private LocalDate data;
    private String nomeCliente;

    public OrcamentoDTO(long id, List<ItemPedidoDTO> itens, double custoItens, double impostoFederal,
            double impostoRegional, double desconto, double custoConsumidor, boolean efetivado, String pais,
            String regiao, LocalDate data, String nomeCliente) {
        this.id = id;
        this.itens = itens;
        this.custoItens = custoItens;
        this.impostoFederal = impostoFederal;
        this.impostoRegional = impostoRegional;
        this.desconto = desconto;
        this.custoConsumidor = custoConsumidor;
        this.efetivado = efetivado;
        this.pais = pais;
        this.regiao = regiao;
        this.data = data;
        this.nomeCliente = nomeCliente;
    }

    public long getId() {
        return id;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public double getCustoItens() {
        return custoItens;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getCustoConsumidor() {
        return custoConsumidor;
    }

    public boolean isEfetivado() {
        return efetivado;
    }

    public void efetiva() {
        efetivado = true;
    }

    public String getPais() {
        return pais;
    }

    public String getRegiao() {
        return regiao;
    }

    public LocalDate getData() {
        return data;
    }

    public double getImpostoFederal() {
        return impostoFederal;
    }

    public double getImpostoRegional() {
        return impostoRegional;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public static OrcamentoDTO fromModel(OrcamentoModel orcamento) {
        List<ItemPedidoDTO> itens = new ArrayList<>(orcamento.getItens().size());
        for (ItemPedidoModel ip : orcamento.getItens()) {
            itens.add(ItemPedidoDTO.fromModel(ip));
        }
        return new OrcamentoDTO(orcamento.getId(), itens, orcamento.getCustoItens(), orcamento.getImpostoFederal(),
                orcamento.getImpostoRegional(), orcamento.getDesconto(), orcamento.getCustoConsumidor(),
                orcamento.isEfetivado(), orcamento.getPais(), orcamento.getRegiao(), orcamento.getData(), orcamento.getNomeCliente());
    }
}
