package br.pucminas.aluguel.model;

@Entity
public class Assinatura extends Contrato {
    private double mensalidade = 1499.90;
    private int diaVencimento = 10;

    protected Assinatura() { }
    public Assinatura(double mensalidade, int diaVencimento) { this.mensalidade = mensalidade; this.diaVencimento = diaVencimento; }
    @Override public String descricao() { return "Assinatura de R$ " + mensalidade + ", vencimento dia " + diaVencimento; }
}
