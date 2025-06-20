package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades;

import java.io.Serializable;

import com.bcopstein.sistvendas.dominio.modelos.ProdutoModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
// @Table(name = "produto")
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descricao;
    private double precoUnitario;

    protected Produto() {
    }

    public Produto(long id, String descricao, double precoUnitario) {
        this.id = id;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
    }

    public long getId() {
        return this.id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public double getPrecoUnitario() {
        return this.precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @Override
    public String toString() {
        return "{" +
                " codigo='" + getId() + "'" +
                ", descricao='" + getDescricao() + "'" +
                ", precoUnitario='" + getPrecoUnitario() + "'" +
                "}";
    }

    public static Produto fromProdutoModel(ProdutoModel produtoModel) {
        return new Produto(produtoModel.getId(), produtoModel.getDescricao(), produtoModel.getPrecoUnitario());
    }

    public ProdutoModel toProdutoModel() {
        return new ProdutoModel(getId(), getDescricao(), getPrecoUnitario());
    }
}
