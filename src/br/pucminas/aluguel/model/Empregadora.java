package br.pucminas.aluguel.model;

import jakarta.persistence.*;

@Entity
public class Empregadora {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private double rendimento;
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	protected Empregadora() { }
	public Empregadora(String nome, double rendimento) { this.nome = nome; this.rendimento = rendimento; }
	public String nome() { return nome; }
	public double rendimento() { return rendimento; }
	public void vincular(Cliente cliente) { this.cliente = cliente; }
}