package br.pucminas.aluguel.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import br.pucminas.aluguel.util.Formatadores;

@Entity
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String login;
    @Column(nullable = false)
    private String senha;
    private String rg;
    private String cpf;
    private String nome;
    private String endereco;
    private String profissao;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private final List<Empregadora> empregadoras = new ArrayList<>();

    protected Cliente() { }
    public Cliente(String login, String senha, String rg, String cpf, String nome, String endereco, String profissao) {
        this.login = login;
        this.senha = senha;
        this.rg = rg;
        this.cpf = Formatadores.cpfNumerico(cpf);
        this.nome = nome;
        this.endereco = endereco;
        this.profissao = profissao;
    }

    public boolean autenticar(String senha) { return this.senha.equals(senha); }
    public void adicionarEmpregadora(Empregadora empregadora) {
        if (empregadoras.size() >= 3) throw new IllegalArgumentException("É permitido cadastrar até 3 empregadoras.");
        empregadora.vincular(this);
        empregadoras.add(empregadora);
    }
    public boolean dadosPreenchidos() { return !rg.isBlank() && !cpf.isBlank() && !nome.isBlank() && !endereco.isBlank(); }
    public void atualizarDados(String rg, String nome, String endereco, String profissao) {
        if (rg == null || rg.isBlank() || nome == null || nome.isBlank() || endereco == null || endereco.isBlank()) throw new IllegalArgumentException("RG, nome e endereço são obrigatórios.");
        this.rg = Formatadores.digitos(rg);
        this.nome = nome;
        this.endereco = endereco;
        this.profissao = profissao == null ? "" : profissao;
    }
    public long getId() { return id == null ? 0 : id; }
    public String getLogin() { return login; }
    public String getNome() { return nome; }
    public String getCpf() { return Formatadores.cpf(cpf); }
    public String getRg() { return Formatadores.rg(rg); }
    public String getProfissao() { return profissao; }
    public List<Empregadora> getEmpregadoras() { return List.copyOf(empregadoras); }
}