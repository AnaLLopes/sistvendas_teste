package com.bcopstein.sistvendas.dominio.modelos;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class OrcamentoModel {
    private long id;
    private List<ItemPedidoModel> itens;
    private double impostoFederal;
    private double impostoRegional;
    private double desconto;
    private boolean efetivado;
    private String pais;
    private String regiao;
    private LocalDate data;
    private String nomeCliente;

    public OrcamentoModel(long id) {
        this.id = id;
        this.itens = new LinkedList<>();
        this.efetivado = false;
        this.data = LocalDate.now();
    }

    public OrcamentoModel() {
        this.itens = new LinkedList<>();
        this.efetivado = false;
        this.data = LocalDate.now();
    }

    public OrcamentoModel(long id, List<ItemPedidoModel> itens, double impostoFederal, double impostoRegional,
            double desconto, String pais, String regiao, String nomeCliente, boolean efetivado) {
        this.id = id;
        this.itens = itens;
        this.efetivado = efetivado;
        this.data = LocalDate.now();
        this.impostoFederal = impostoFederal;
        this.impostoRegional = impostoRegional;
        this.desconto = desconto;
        this.pais = pais;
        this.regiao = regiao;
        this.nomeCliente = nomeCliente;
    }

    public void addItensPedido(PedidoModel pedido) {
        for (ItemPedidoModel itemPedido : pedido.getItens()) {
            itens.add(itemPedido);
        }
    }

    public List<ItemPedidoModel> getItens() {
        return itens;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCustoItens() {
        return getItens().stream()
                .mapToDouble(it -> it.getProduto().getPrecoUnitario() * it.getQuantidade())
                .sum();
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getCustoConsumidor() {
        return getCustoItens() + getImpostoFederal() + getImpostoRegional() - getDesconto();
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

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public LocalDate getData() {
        return data;
    }

    public double getImpostoFederal() {
        return impostoFederal;
    }

    public void setImpostoFederal(double impostoFederal) {
        this.impostoFederal = impostoFederal;
    }

    public double getImpostoRegional() {
        return impostoRegional;
    }

    public void setImpostoRegional(double impostoRegional) {
        this.impostoRegional = impostoRegional;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
