package br.pucminas.aluguel;

import br.pucminas.aluguel.controller.AluguelController;
import br.pucminas.aluguel.repository.JpaRepository;
import br.pucminas.aluguel.service.AluguelService;
import br.pucminas.aluguel.view.HtmlView;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class Application {
    public static void main(String[] args) throws Exception {
        JpaRepository repository = new JpaRepository();
        AluguelController controller = new AluguelController(new AluguelService(repository), new HtmlView());
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", exchange -> { if (exchange.getRequestURI().getPath().equals("/")) { byte[] body = new HtmlView().landing().getBytes(); exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8"); exchange.sendResponseHeaders(200, body.length); exchange.getResponseBody().write(body); exchange.close(); } else { exchange.sendResponseHeaders(404, -1); exchange.close(); } });
        server.createContext("/cadastro", controller::registrar);
        server.createContext("/login", controller::login);
        server.createContext("/pedidos", controller::pedidos);
        server.createContext("/pedido", controller::pedido);
        server.createContext("/empregadora", controller::empregadora);
        server.createContext("/cliente", controller::gerenciarCliente);
        server.createContext("/pedido/cancelar", controller::cancelar);
        server.createContext("/pedido/alterar", controller::alterar);
        server.createContext("/pedido/decidir", controller::decidir);
        server.createContext("/agente", controller::agente);
        server.createContext("/agente/avaliar", controller::avaliar);
        server.createContext("/automovel", controller::automovel);
        server.createContext("/logout", controller::logout);
        server.start();
        System.out.println("Aluga+ disponível em http://localhost:8080");
    }
}