package br.pucminas.aluguel.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
    @ManyToOne(optional = false)
    private Cliente cliente;
    @ManyToOne(optional = false)
    private Automovel automovel;
    @Enumerated(EnumType.STRING)
    private Modalidade modalidade;
    private LocalDate dataCriacao = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.EM_ANALISE;
    private String parecer;
    @OneToOne(cascade = CascadeType.ALL)
    private Contrato contrato;

    protected Pedido() { }
    public Pedido(Cliente cliente, Automovel automovel, Modalidade modalidade) {
        this.cliente = cliente;
        this.automovel = automovel;
        this.modalidade = modalidade;
    }
    public boolean podeSerAlterado() { return status == StatusPedido.EM_ANALISE; }
    public void alterar(Automovel automovel, Modalidade modalidade) {
        if (!podeSerAlterado()) throw new IllegalStateException("Apenas pedidos em análise podem ser alterados.");
        this.automovel = automovel;
        this.modalidade = modalidade;
    }
    public void avaliar(boolean aprovado, String justificativa) {
        if (!podeSerAlterado()) throw new IllegalStateException("Este pedido já foi avaliado.");
        status = aprovado ? StatusPedido.APROVADO : StatusPedido.REPROVADO;
        parecer = justificativa;
    }
    public void cancelar() {
        if (!podeSerAlterado()) throw new IllegalStateException("Apenas pedidos em análise podem ser cancelados.");
        status = StatusPedido.CANCELADO;
    }
    public void decidir(boolean aceita) {
        decidir(aceita, null);
    }
    public void decidir(boolean aceita, Contrato contrato) {
        if (status != StatusPedido.APROVADO) throw new IllegalStateException("A decisão só pode ocorrer após um parecer aprovado.");
        if (aceita) {
            if (contrato == null) throw new IllegalArgumentException("O contrato da modalidade é obrigatório.");
            this.contrato = contrato;
            status = StatusPedido.CONTRATADO;
        }
        else status = StatusPedido.CANCELADO;
    }
    public long getNumero() { return numero == null ? 0 : numero; }
    public Cliente getCliente() { return cliente; }
    public Automovel getAutomovel() { return automovel; }
    public Modalidade getModalidade() { return modalidade; }
    public LocalDate getDataCriacao() { return dataCriacao; }
    public StatusPedido getStatus() { return status; }
    public String getParecer() { return parecer; }
    public Contrato getContrato() { return contrato; }
}