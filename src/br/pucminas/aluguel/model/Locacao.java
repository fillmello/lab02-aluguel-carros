package br.pucminas.aluguel.model;

import jakarta.persistence.Entity;

@Entity
public class Locacao extends Contrato {
    private int prazoDias = 30;

    protected Locacao() { }
    public Locacao(int prazoDias) { this.prazoDias = prazoDias; }
    @Override public String descricao() { return "Locação por " + prazoDias + " dias, com devolução prevista"; }
}
