package br.pucminas.aluguel.service;

import br.pucminas.aluguel.model.*;
import br.pucminas.aluguel.repository.JpaRepository;
import java.util.List;
import br.pucminas.aluguel.util.Formatadores;

public class AluguelService {
    private final JpaRepository repository;
    public AluguelService(JpaRepository repository) { this.repository = repository; }

    public Cliente cadastrar(String login, String senha, String rg, String cpf, String nome, String endereco, String profissao) {
        cpf = Formatadores.cpfNumerico(cpf);
        if (cpf.length() != 11) throw new IllegalArgumentException("CPF deve conter 11 dígitos.");
        if (repository.existeCpf(cpf)) throw new IllegalArgumentException("CPF já cadastrado.");
        if (repository.existeLogin(login)) throw new IllegalArgumentException("Login já cadastrado.");
        Cliente cliente = new Cliente(login, senha, rg, cpf, nome, endereco, profissao);
        repository.salvarCliente(cliente);
        return cliente;
    }
    public Cliente autenticar(String login, String senha) { return repository.autenticar(login, senha); }
    public void adicionarEmpregadora(Cliente cliente, String nome, double rendimento) {
        if (nome == null || nome.isBlank() || rendimento <= 0) throw new IllegalArgumentException("Informe nome e rendimento válidos.");
        cliente.adicionarEmpregadora(new Empregadora(nome, rendimento));
        repository.salvarCliente(cliente);
    }
    public void atualizarCliente(Cliente cliente, String rg, String nome, String endereco, String profissao) {
        cliente.atualizarDados(Formatadores.digitos(rg), nome, endereco, profissao);
        repository.salvarCliente(cliente);
    }
    public void excluirCliente(Cliente cliente) { repository.excluirCliente(cliente); }
    public Pedido criarPedido(Cliente cliente, long automovelId, Modalidade modalidade) {
        if (!cliente.dadosPreenchidos()) throw new IllegalArgumentException("Complete os dados do contratante antes de criar um pedido.");
        Automovel automovel = repository.automovel(automovelId);
        if (automovel == null) throw new IllegalArgumentException("Automóvel inválido.");
        Pedido pedido = new Pedido(cliente, automovel, modalidade);
        repository.salvarPedido(pedido);
        return pedido;
    }
    public List<Pedido> pedidosDo(Cliente cliente) { return repository.pedidosDo(cliente); }
    public List<Pedido> pedidosEmAnalise() { return repository.pedidosEmAnalise(); }
    public void cancelar(Cliente cliente, long numero) { Pedido pedido = localizar(cliente, numero); pedido.cancelar(); repository.atualizarPedido(pedido); }
    public void alterar(Cliente cliente, long numero, long automovelId, Modalidade modalidade) {
        Automovel automovel = repository.automovel(automovelId);
        if (automovel == null) throw new IllegalArgumentException("Automóvel inválido.");
        Pedido pedido = localizar(cliente, numero);
        pedido.alterar(automovel, modalidade);
        repository.atualizarPedido(pedido);
    }
    public void avaliar(long numero, boolean aprovado, String justificativa) {
        Pedido pedido = repository.pedido(numero);
        if (pedido == null) throw new IllegalArgumentException("Pedido não encontrado.");
        pedido.avaliar(aprovado, justificativa);
        repository.atualizarPedido(pedido);
    }
    public void decidir(Cliente cliente, long numero, boolean aceita) { Pedido pedido = localizar(cliente, numero); pedido.decidir(aceita, aceita ? contratoPara(pedido.getModalidade()) : null); repository.atualizarPedido(pedido); }
    private Contrato contratoPara(Modalidade modalidade) {
        return switch (modalidade) {
            case LOCACAO -> new Locacao(30);
            case ASSINATURA -> new Assinatura(1499.90, 10);
            case LEASING -> new Leasing(36, 0, new ContratoCredito(60000, 1.2, 36, new Banco("001", "Banco agente")));
        };
    }
    private Pedido localizar(Cliente cliente, long numero) {
        Pedido pedido = repository.pedido(numero);
        if (pedido == null || pedido.getCliente().getId() != cliente.getId()) throw new IllegalArgumentException("Pedido não encontrado.");
        return pedido;
    }
    public List<Automovel> automoveis() { return repository.automoveis(); }
    public Automovel cadastrarAutomovel(String placa, int ano, String marca, String modelo) {
        if (placa == null || placa.isBlank() || marca == null || marca.isBlank() || modelo == null || modelo.isBlank() || ano <= 0) throw new IllegalArgumentException("Placa, ano, marca e modelo são obrigatórios.");
        if (repository.automoveis().stream().anyMatch(a -> a.placa().equalsIgnoreCase(placa))) throw new IllegalArgumentException("A placa informada já está cadastrada.");
        Automovel automovel = new Automovel(placa.toUpperCase(), ano, marca, modelo);
        repository.salvarAutomovel(automovel);
        return automovel;
    }
}
