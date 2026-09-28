package br.pucminas.aluguel.model;

import jakarta.persistence.*;

@Entity
public class ContratoCredito {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double valorFinanciado;
    private double taxaJuros;
    private int numeroParcelas;
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Banco banco;

    protected ContratoCredito() { }
    public ContratoCredito(double valorFinanciado, double taxaJuros, int numeroParcelas, Banco banco) {
        this.valorFinanciado = valorFinanciado;
        this.taxaJuros = taxaJuros;
        this.numeroParcelas = numeroParcelas;
        this.banco = banco;
    }
    public String descricao() { return "Crédito de R$ " + valorFinanciado + " em " + numeroParcelas + " parcelas pelo banco " + banco.getRazaoSocial(); }
}
