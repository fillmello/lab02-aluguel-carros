package br.pucminas.aluguel.view;

import br.pucminas.aluguel.model.*;
import java.util.List;

public class HtmlView {
    public String page(String title, String content) {
        return "<!doctype html><html lang='pt-BR'><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width,initial-scale=1'><title>Aluga+ | " + title + "</title><style>" + css() + "</style></head><body><header><a class='brand' href='/'>ALUGA<span>+</span></a><span class='tagline'>mobilidade no seu ritmo</span></header><main>" + content + "</main><footer>Protótipo Lab02S03 · arquitetura MVC</footer></body></html>";
    }

    public String landing() {
        return page("Início", "<section class='hero'><div><p class='eyebrow'>SISTEMA DE ALUGUEL DE CARROS</p><h1>Seu próximo caminho começa aqui.</h1><p class='lead'>Escolha um veículo, acompanhe a análise e tenha clareza em cada etapa do seu pedido.</p><a class='button' href='/cadastro'>Criar cadastro</a> <a class='button ghost' href='/login'>Entrar</a></div><div class='hero-mark'>01<br><small>mobilidade simples</small></div></section>");
    }

    public String cadastro(String erro) {
        return page("Cadastro", "<a class='back' href='/'>← início</a><section class='panel narrow'><p class='eyebrow'>PRIMEIRO PASSO</p><h2>Crie sua conta</h2>" + alert(erro) + form("/cadastro", "<label>Nome completo<input name='nome' required></label><label>CPF<input name='cpf' inputmode='numeric' maxlength='14' placeholder='000.000.000-00' required></label><label>RG<input name='rg' inputmode='numeric' maxlength='12' placeholder='00.000.000-0' required></label><label>Endereço<input name='endereco' required></label><label>Profissão<input name='profissao'></label><label>Login<input name='login' required></label><label>Senha<input type='password' name='senha' required></label><button class='button' type='submit'>Cadastrar e continuar</button>") + "<p class='muted'>Já tem uma conta? <a href='/login'>Entrar</a></p></section>");
    }

    public String login(String erro) {
        return page("Login", "<a class='back' href='/'>← início</a><section class='panel narrow'><p class='eyebrow'>ÁREA DO CLIENTE</p><h2>Bem-vindo de volta</h2>" + alert(erro) + form("/login", "<label>Login<input name='login' required></label><label>Senha<input type='password' name='senha' required></label><button class='button' type='submit'>Acessar pedidos</button>") + "<p class='muted'>Ainda não tem conta? <a href='/cadastro'>Cadastre-se</a></p></section>");
    }

    public String dashboard(Cliente cliente, List<Pedido> pedidos, List<Automovel> automoveis, String mensagem) {
        StringBuilder cards = new StringBuilder();
        for (Pedido pedido : pedidos) {
            cards.append("<article class='order'><div><span class='order-id'>PEDIDO #").append(pedido.getNumero()).append("</span><h3>").append(pedido.getAutomovel().descricao()).append("</h3><p>").append(pedido.getModalidade().getDescricao()).append(" · ").append(pedido.getDataCriacao()).append("</p>");
            if (pedido.getParecer() != null) cards.append("<p class='opinion'>Parecer: ").append(pedido.getParecer()).append("</p>");
            if (pedido.getContrato() != null) cards.append("<p class='opinion'>Contrato: ").append(pedido.getContrato().descricao()).append("</p>");
            cards.append("</div><div><span class='status ").append(pedido.getStatus().name().toLowerCase()).append("'>").append(pedido.getStatus().getDescricao()).append("</span>").append(clienteAcoes(pedido, automoveis)).append("</div></article>");
        }
        String historico = cards.length() == 0 ? "<div class='empty'>Você ainda não tem pedidos.</div>" : cards.toString();
        return page("Meus pedidos", "<div class='topline'><div><p class='eyebrow'>PAINEL DO CLIENTE</p><h2>Olá, " + cliente.getNome() + ".</h2><p class='lead'>Acompanhe seus pedidos de aluguel.</p></div><a class='button ghost' href='/logout'>Sair</a></div>" + alert(mensagem) + "<div class='grid'><section><div class='panel'><div class='section-title'><h3>Novo pedido</h3><span>01</span></div>" + form("/pedido", "<label>Automóvel<select name='automovel'>" + options(automoveis) + "</select></label><label>Modalidade<select name='modalidade'><option value='LOCACAO'>Locação</option><option value='ASSINATURA'>Assinatura</option><option value='LEASING'>Leasing</option></select></label><button class='button' type='submit'>Enviar pedido</button>") + "</div><div class='panel employer'><div class='section-title'><h3>Renda</h3><span>" + cliente.getEmpregadoras().size() + "/3</span></div>" + form("/empregadora", "<label>Empregadora<input name='nome' required></label><label>Rendimento<input name='rendimento' type='number' step='0.01' min='0.01' required></label><button class='button ghost' type='submit'>Adicionar</button>") + "</div><div class='panel employer'><div class='section-title'><h3>Meus dados</h3><span>CRUD</span></div>" + form("/cliente", "<label>Nome<input name='nome' value='" + cliente.getNome() + "' required></label><label>RG<input name='rg' required></label><label>Endereço<input name='endereco' required></label><label>Profissão<input name='profissao' value='" + cliente.getProfissao() + "'></label><button class='button ghost' type='submit'>Atualizar dados</button>") + "<form method='post' action='/cliente' onsubmit=\"return confirm('Excluir sua conta e seus pedidos?')\"><input type='hidden' name='_method' value='DELETE'><button class='link-button' type='submit'>Excluir conta</button></form></div></section><section><div class='section-title'><h3>Histórico</h3><span>" + pedidos.size() + " pedidos</span></div>" + historico + "</section></div>");
    }

    public String agente(List<Pedido> pedidos) {
        return agente(pedidos, List.of(), null);
    }

    public String agente(List<Pedido> pedidos, List<Automovel> automoveis, String mensagem) {
        StringBuilder fila = new StringBuilder("<section class='panel'><div class='section-title'><h2>Fila do agente</h2><span>" + pedidos.size() + " em análise</span></div>");
        if (pedidos.isEmpty()) fila.append("<div class='empty'>Nenhum pedido aguardando análise.</div>");
        for (Pedido pedido : pedidos) {
            fila.append("<article class='order'><div><span class='order-id'>PEDIDO #").append(pedido.getNumero()).append("</span><h3>").append(pedido.getAutomovel().descricao()).append("</h3><p>Cliente: ").append(pedido.getCliente().getNome()).append(" · CPF: ").append(pedido.getCliente().getCpf()).append("</p><p>Profissão: ").append(pedido.getCliente().getProfissao()).append("</p></div>");
            fila.append(form("/agente/avaliar", "<input type='hidden' name='numero' value='" + pedido.getNumero() + "'><label>Justificativa<input name='justificativa' required></label><button class='button' name='resultado' value='aprovar'>Aprovar</button><button class='button ghost' name='resultado' value='reprovar'>Reprovar</button>")).append("</article>");
        }
        String cadastro = form("/automovel", "<label>Placa<input name='placa' required></label><label>Ano<input name='ano' type='number' min='1900' required></label><label>Marca<input name='marca' required></label><label>Modelo<input name='modelo' required></label><button class='button' type='submit'>Cadastrar automóvel</button>");
        return page("Fila do agente", "<p class='eyebrow'>ÁREA DO AGENTE</p><h1>Análise financeira</h1>" + alert(mensagem) + "<section class='panel'><div class='section-title'><h2>Novo automóvel</h2><span>" + automoveis.size() + " cadastrados</span></div>" + cadastro + "</section>" + fila + "</section>");
    }

    private String clienteAcoes(Pedido pedido, List<Automovel> automoveis) {
        if (pedido.getStatus() == StatusPedido.EM_ANALISE) return form("/pedido/alterar", "<input type='hidden' name='numero' value='" + pedido.getNumero() + "'><label>Alterar automóvel<select name='automovel'>" + options(automoveis) + "</select></label><label>Modalidade<select name='modalidade'><option value='LOCACAO'>Locação</option><option value='ASSINATURA'>Assinatura</option><option value='LEASING'>Leasing</option></select></label><button class='button ghost' type='submit'>Salvar alteração</button>") + form("/pedido/cancelar", "<input type='hidden' name='numero' value='" + pedido.getNumero() + "'><button class='link-button' type='submit'>Cancelar pedido</button>");
        if (pedido.getStatus() == StatusPedido.APROVADO) return form("/pedido/decidir", "<input type='hidden' name='numero' value='" + pedido.getNumero() + "'><button class='button' name='decisao' value='aceitar'>Aceitar contrato</button><button class='button ghost' name='decisao' value='recusar'>Recusar</button>");
        return "";
    }

    private String options(List<Automovel> automoveis) {
        StringBuilder result = new StringBuilder();
        for (Automovel automovel : automoveis) result.append("<option value='").append(automovel.id()).append("'>").append(automovel.descricao()).append("</option>");
        return result.toString();
    }

    private String form(String action, String fields) { return "<form method='post' action='" + action + "'>" + fields + "</form>"; }
    private String alert(String erro) { return erro == null || erro.isBlank() ? "" : "<div class='alert'>" + erro + "</div>"; }

    private String css() {
        return """
        :root{--ink:#18211f;--muted:#687470;--paper:#f4f1ea;--lime:#c9df54;--line:#d9ddd4;--red:#ba5b4c}*{box-sizing:border-box}body{margin:0;background:var(--paper);color:var(--ink);font:16px Georgia,serif}header{padding:28px 7vw;border-bottom:1px solid var(--line);display:flex;align-items:center;gap:18px}.brand{font:bold 25px Arial,sans-serif;color:var(--ink);text-decoration:none;letter-spacing:1px}.brand span{color:#789100}.tagline,.muted{color:var(--muted);font:13px Arial,sans-serif}main{max-width:1120px;margin:auto;padding:56px 7vw}.hero{min-height:520px;display:flex;align-items:center;justify-content:space-between}.eyebrow{font:11px Arial,sans-serif;letter-spacing:2px;color:#789100;font-weight:bold}.hero h1{font-size:clamp(42px,7vw,82px);line-height:.95;max-width:700px;margin:24px 0}.lead{color:var(--muted);font:18px/1.5 Arial,sans-serif;max-width:570px}.hero-mark{font:bold 190px Arial,sans-serif;color:var(--lime);line-height:.7;text-align:right}.hero-mark small{font:12px Arial,sans-serif;color:var(--ink);letter-spacing:2px}.button{display:inline-block;border:0;background:var(--ink);color:white;padding:14px 22px;margin-top:18px;text-decoration:none;font:bold 13px Arial,sans-serif;cursor:pointer}.button:hover{background:#52605b}.button.ghost{background:transparent;color:var(--ink);border:1px solid var(--ink)}.back{color:var(--muted);font:13px Arial,sans-serif;text-decoration:none}.panel{background:white;border:1px solid var(--line);padding:28px}.narrow{max-width:510px;margin:28px auto}.panel h2{font-size:42px;margin:12px 0 30px}.panel h3,.section-title h3{font-size:21px;margin:0}.panel label{display:block;font:12px Arial,sans-serif;color:var(--muted);margin:16px 0}.panel input,.panel select{display:block;width:100%;border:0;border-bottom:1px solid var(--line);padding:11px 0;background:transparent;color:var(--ink);font:16px Georgia,serif}.alert{background:#fff0e9;border-left:3px solid var(--red);padding:13px;margin:14px 0;font:13px Arial,sans-serif}.topline,.section-title{display:flex;justify-content:space-between;align-items:center}.topline{margin-bottom:44px}.topline h2{font-size:48px;margin:12px 0}.topline .button{margin:0}.grid{display:grid;grid-template-columns:330px 1fr;gap:38px}.section-title{border-bottom:1px solid var(--line);padding-bottom:14px;margin-bottom:18px}.section-title span,.order-id{font:11px Arial,sans-serif;color:var(--muted);letter-spacing:1px}.order{display:flex;justify-content:space-between;gap:20px;border-bottom:1px solid var(--line);padding:22px 0}.order h3{font-size:19px;margin:9px 0}.order p{color:var(--muted);font:13px Arial,sans-serif;margin:0}.status{height:max-content;padding:7px 10px;font:bold 11px Arial,sans-serif;white-space:nowrap}.em_analise{background:#f0e8bf}.aprovado,.contratado{background:#dbe9a2}.reprovado,.cancelado{background:#f4d7cf}.opinion{margin-top:10px!important;color:var(--ink)!important}.empty{padding:30px 0;color:var(--muted);font:14px Arial,sans-serif}footer{text-align:center;color:var(--muted);font:11px Arial,sans-serif;padding:35px}@media(max-width:700px){header{padding:22px 6vw}.tagline,.hero-mark{display:none}main{padding:38px 6vw}.hero{min-height:560px}.hero h1{font-size:54px}.grid{grid-template-columns:1fr}.topline h2{font-size:36px}}
        """;
    }
}
