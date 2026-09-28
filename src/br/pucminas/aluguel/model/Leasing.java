package br.pucminas.aluguel.model;

import jakarta.persistence.Entity;

@Entity
public class Leasing extends Contrato {
    private int prazoMeses = 36;
    private double valorResidual = 0;

    protected Leasing() { }
    public Leasing(int prazoMeses, double valorResidual) { this.prazoMeses = prazoMeses; this.valorResidual = valorResidual; }
    @Override public String descricao() { return "Leasing por " + prazoMeses + " meses, valor residual R$ " + valorResidual; }
}
