package br.pucminas.aluguel.repository;

import br.pucminas.aluguel.model.*;
import jakarta.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class JpaRepository implements AutoCloseable {
    private final EntityManagerFactory factory;

    public JpaRepository() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("jakarta.persistence.jdbc.url", env("DB_URL", "jdbc:postgresql://localhost:5432/aluguel"));
        properties.put("jakarta.persistence.jdbc.user", env("DB_USER", "aluguel"));
        properties.put("jakarta.persistence.jdbc.password", env("DB_PASSWORD", "aluguel"));
        factory = Persistence.createEntityManagerFactory("aluguelPU", properties);
        seedAutomoveis();
    }

    public boolean existeCpf(String cpf) { return consulta(em -> em.createQuery("select count(c) from Cliente c where lower(c.cpf) = lower(:cpf)", Long.class).setParameter("cpf", cpf).getSingleResult() > 0); }
    public boolean existeLogin(String login) { return consulta(em -> em.createQuery("select count(c) from Cliente c where lower(c.login) = lower(:login)", Long.class).setParameter("login", login).getSingleResult() > 0); }
    public Cliente autenticar(String login, String senha) { return consulta(em -> em.createQuery("select c from Cliente c where c.login = :login and c.senha = :senha", Cliente.class).setParameter("login", login).setParameter("senha", senha).getResultStream().findFirst().orElse(null)); }
    public void salvarCliente(Cliente cliente) { transacao(em -> { em.merge(cliente); return null; }); }
    public List<Automovel> automoveis() { return consulta(em -> em.createQuery("select a from Automovel a order by a.id", Automovel.class).getResultList()); }
    public Automovel automovel(long id) { return consulta(em -> em.find(Automovel.class, id)); }
    public void salvarAutomovel(Automovel automovel) { transacao(em -> { em.persist(automovel); return null; }); }
    public void salvarPedido(Pedido pedido) { transacao(em -> { em.persist(pedido); return null; }); }
    public List<Pedido> pedidosDo(Cliente cliente) { return consulta(em -> em.createQuery("select p from Pedido p where p.cliente.id = :id order by p.numero", Pedido.class).setParameter("id", cliente.getId()).getResultList()); }
    public List<Pedido> pedidosEmAnalise() { return consulta(em -> em.createQuery("select p from Pedido p where p.status = :status order by p.numero", Pedido.class).setParameter("status", StatusPedido.EM_ANALISE).getResultList()); }
    public Pedido pedido(long numero) { return consulta(em -> em.find(Pedido.class, numero)); }
    public void atualizarPedido(Pedido pedido) { transacao(em -> { em.merge(pedido); return null; }); }
    public void excluirCliente(Cliente cliente) { transacao(em -> { Cliente managed = em.find(Cliente.class, cliente.getId()); if (managed != null) em.remove(managed); return null; }); }

    private void seedAutomoveis() {
        if (!automoveis().isEmpty()) return;
        salvarAutomovel(new Automovel("RBT-2A24", 2024, "Toyota", "Corolla"));
        salvarAutomovel(new Automovel("QWE-7B19", 2023, "Volkswagen", "T-Cross"));
        salvarAutomovel(new Automovel("PXY-4C81", 2022, "Chevrolet", "Onix"));
    }
    private <T> T consulta(Function<EntityManager, T> operation) { EntityManager em = factory.createEntityManager(); try { return operation.apply(em); } finally { em.close(); } }
    private <T> T transacao(Function<EntityManager, T> operation) { EntityManager em = factory.createEntityManager(); EntityTransaction tx = em.getTransaction(); try { tx.begin(); T result = operation.apply(em); tx.commit(); return result; } catch (RuntimeException e) { if (tx.isActive()) tx.rollback(); throw e; } finally { em.close(); } }
    private static String env(String key, String fallback) { String value = System.getenv(key); return value == null || value.isBlank() ? fallback : value; }
    @Override public void close() { factory.close(); }
}