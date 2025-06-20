package com.bcopstein.sistvendas.dominio.modelos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PedidoModel {
    private long id;
    private List<ItemPedidoModel> itens = new LinkedList<>();
    private String pais;
    private String regiao;
    private String nomeCliente;

    public PedidoModel(long id, List<ItemPedidoModel> itens, String pais, String regiao, String nomeCliente) {
        this.id = id;
        this.itens = itens;
        this.pais = pais;
        this.regiao = regiao;
        this.nomeCliente = nomeCliente;
    }

    public long getId() {
        return id;
    }

    public List<ItemPedidoModel> getItens() {
        return new ArrayList<>(itens);
    }

    public void addItem(ItemPedidoModel item){
        itens.add(item);
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

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
