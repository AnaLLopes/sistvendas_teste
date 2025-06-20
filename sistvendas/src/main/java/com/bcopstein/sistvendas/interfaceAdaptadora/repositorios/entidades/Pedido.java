package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.bcopstein.sistvendas.dominio.modelos.ItemPedidoModel;
import com.bcopstein.sistvendas.dominio.modelos.PedidoModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<ItemPedido> itens = new ArrayList<>();
    
    private String pais;
    private String regiao;
    private String nomeCliente;

    public Pedido(long id, List<ItemPedido> itens, String pais, String regiao) {
        this.id = id;
        if (itens != null) {
            this.itens.addAll(itens);
        }
        this.pais = pais;
        this.regiao = regiao;
    }

    public Pedido() {
    } // Construtor padrão para JPA

    public long getId() {
        return this.id;
    }

    public List<ItemPedido> getItens() {
        return new ArrayList<>(itens);
    }

    public String getPais() {
        return this.pais;
    }

    public String getRegiao() {
        return this.regiao;
    }

    public void addItem(ItemPedido item) {
        itens.add(item);
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public PedidoModel toPedidoModel() {
        List<ItemPedidoModel> itensModel = new ArrayList<>();
        for (ItemPedido item : getItens()) {
            itensModel.add(new ItemPedidoModel(item.getProduto().toProdutoModel(), item.getQuantidade()));
        }
        return new PedidoModel(getId(), itensModel, getPais(), getRegiao(), getNomeCliente());
    }
}
