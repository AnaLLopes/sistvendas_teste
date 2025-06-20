package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.bcopstein.sistvendas.dominio.modelos.ItemPedidoModel;
import com.bcopstein.sistvendas.dominio.modelos.OrcamentoModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
public class Orcamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "orcamento_id")
    private List<ItemPedido> itens = new ArrayList<>();

    private double impostoFederal;
    private double impostoRegional;
    private double desconto;
    private boolean efetivado;
    private String pais;
    private String regiao;
    private LocalDate data;
    private String nomeCliente;

    public Orcamento(long id, List<ItemPedido> itens, boolean efetivado) {
        this.id = id;
        if (itens != null) {
            this.itens.addAll(itens);
        }
        this.efetivado = efetivado;
    }

    public Orcamento() {
    }

    public void addItensPedido(Pedido pedido) {
        for (ItemPedido itemPedido : pedido.getItens()) {
            itens.add(itemPedido);
        }
    }

    public List<ItemPedido> getItens() {
        return this.itens;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getDesconto() {
        return this.desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public boolean isEfetivado() {
        return this.efetivado;
    }

    public void efetiva() {
        this.efetivado = true;
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

    public OrcamentoModel toOrcamentoModel() {
        List<ItemPedido> itens = getItens();
        List<ItemPedidoModel> itensModel = new LinkedList<>();
        for (ItemPedido item : itens) {
            itensModel.add(item.toItemPedidoModel());
        }
        OrcamentoModel orcamentoModel = new OrcamentoModel(getId(), itensModel, getImpostoFederal(),
                getImpostoRegional(), getDesconto(), getPais(), getRegiao(), getNomeCliente(), isEfetivado());
        return orcamentoModel;
    }
}
