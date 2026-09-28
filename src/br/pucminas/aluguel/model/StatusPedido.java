package br.pucminas.aluguel.model;

public enum StatusPedido {
    EM_ANALISE("Em análise"),
    APROVADO("Aprovado"),
    REPROVADO("Reprovado"),
    CANCELADO("Cancelado"),
    CONTRATADO("Contratado");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}