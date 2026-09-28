package br.pucminas.aluguel.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Automovel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placa;
    private int ano;
    private String marca;
    private String modelo;

    protected Automovel() { }
    public Automovel(String placa, int ano, String marca, String modelo) { this.placa = placa; this.ano = ano; this.marca = marca; this.modelo = modelo; }
    public long id() { return id == null ? 0 : id; }
    public String placa() { return placa; }
    public int ano() { return ano; }
    public String marca() { return marca; }
    public String modelo() { return modelo; }
    public String descricao() {
        return marca + " " + modelo + " (" + ano + ") - " + placa;
    }
}