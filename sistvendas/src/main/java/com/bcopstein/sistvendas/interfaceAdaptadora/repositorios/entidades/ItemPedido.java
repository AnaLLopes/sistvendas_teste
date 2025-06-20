package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades;

import java.io.Serializable;

import com.bcopstein.sistvendas.dominio.modelos.ItemPedidoModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemPedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantidade;
    
    @ManyToOne
    private Produto produto;
    
    public ItemPedido(Long id, Produto produto, int quantidade) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    protected ItemPedido() {}

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ItemPedidoModel toItemPedidoModel(ItemPedido itemPedido) {
        return new ItemPedidoModel(this.produto.toProdutoModel(), this.quantidade);
    }

    public static ItemPedido fromItemPedidoModel(ItemPedidoModel itemPedidoModel) {
        var produto = Produto.fromProdutoModel(itemPedidoModel.getProduto());
        return new ItemPedido(itemPedidoModel.getId(), produto, itemPedidoModel.getQuantidade());
    }

    public ItemPedidoModel toItemPedidoModel() {
        ItemPedidoModel itemPedidoModel = new ItemPedidoModel(getProduto().toProdutoModel(), getQuantidade());
        itemPedidoModel.setId(id);
        return itemPedidoModel;
    }

    @Override
    public String toString() {
        return "ItemPedido [produto=" + this.produto + ", quantidade=" + this.quantidade + "]";
    }
}
