package br.pucminas.aluguel.model;

import jakarta.persistence.Entity;

@Entity
public class Leasing extends Contrato {
    private int prazoMeses = 36;
    private double valorResidual = 0;
    @jakarta.persistence.OneToOne(cascade = jakarta.persistence.CascadeType.ALL, optional = false)
    private ContratoCredito contratoCredito;

    protected Leasing() { }
    public Leasing(int prazoMeses, double valorResidual, ContratoCredito contratoCredito) { this.prazoMeses = prazoMeses; this.valorResidual = valorResidual; this.contratoCredito = contratoCredito; }
    @Override public String descricao() { return "Leasing por " + prazoMeses + " meses, valor residual R$ " + valorResidual + ". " + contratoCredito.descricao(); }
}
