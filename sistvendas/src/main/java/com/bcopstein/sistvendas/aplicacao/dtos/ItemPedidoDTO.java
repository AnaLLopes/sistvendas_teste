package com.bcopstein.sistvendas.aplicacao.dtos;

import com.bcopstein.sistvendas.dominio.modelos.ItemPedidoModel;

public class ItemPedidoDTO {
    private long idProduto;
    private int qtdade;

    public ItemPedidoDTO() {
        // Construtor padrão necessário para desserialização
    }

    public ItemPedidoDTO(long idProduto, int qtdade) {
        this.idProduto = idProduto;
        this.qtdade = qtdade;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public int getQtdade() {
        return qtdade;
    }

    public void setQtdade(int qtdade) {
        this.qtdade = qtdade;
    }

    @Override
    public String toString() {
        return "ItemPedidoDTO [idProduto=" + idProduto + ", qtdade=" + qtdade + "]";
    }

    public static ItemPedidoDTO fromModel(ItemPedidoModel item) {
        return new ItemPedidoDTO(item.getProduto().getId(), item.getQuantidade());
    }
}