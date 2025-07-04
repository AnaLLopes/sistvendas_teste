package com.bcopstein.sistvendas.aplicacao.dtos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.bcopstein.sistvendas.dominio.modelos.ItemPedidoModel;
import com.bcopstein.sistvendas.dominio.modelos.PedidoModel;

public class PedidoDTO {
    private long id;
    private List<ItemPedidoDTO> itens = new LinkedList<>();
    private String pais;
    private String regiao;
    private String nomeCliente;

    public PedidoDTO() {
        // Construtor padrão obrigatório para Spring
    }

    public PedidoDTO(long id, List<ItemPedidoDTO> itens, String pais, String regiao, String nomeCliente) {
        this.id = id;
        this.itens = itens;
        this.pais = pais;
        this.regiao = regiao;
        this.nomeCliente = nomeCliente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
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

    public static PedidoDTO fromModel(PedidoModel pedido) {
        List<ItemPedidoDTO> itens = new ArrayList<>(pedido.getItens().size());
        for (ItemPedidoModel ip : pedido.getItens()) {
            itens.add(ItemPedidoDTO.fromModel(ip));
        }
        return new PedidoDTO(pedido.getId(), itens, pedido.getPais(), pedido.getRegiao(), pedido.getNomeCliente());
    }
}
