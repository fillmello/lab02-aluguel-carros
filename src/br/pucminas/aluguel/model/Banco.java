package br.pucminas.aluguel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Banco {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoBanco;
    private String razaoSocial;

    protected Banco() { }
    public Banco(String codigoBanco, String razaoSocial) { this.codigoBanco = codigoBanco; this.razaoSocial = razaoSocial; }
    public String getRazaoSocial() { return razaoSocial; }
}
