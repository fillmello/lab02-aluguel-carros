# Histórias de Usuário

| ID | Caso de uso / História | Ator |
| --- | --- | --- |
| 01 | Cadastrar-se no sistema | Visitante |
| 02 | Realizar login | Usuário do sistema |
| 03 | Manter dados do contratante | Cliente |
| 04 | Registrar entidades empregadoras | Cliente |
| 05 | Criar pedido de aluguel | Cliente |
| 06 | Alterar pedido de aluguel | Cliente |
| 07 | Consultar pedido de aluguel | Cliente |
| 08 | Cancelar pedido de aluguel | Cliente |
| 09 | Consultar pedidos recebidos | Agente |
| 10 | Avaliar pedido e registrar parecer | Agente |
| 11 | Decidir sobre pedido aprovado | Cliente |
| 12 | Executar contrato de aluguel | Cliente |
| 13 | Cadastrar automóvel | Agente |
| 14 | Registrar propriedade do automóvel | Agente |
| 15 | Conceder contrato de crédito | Banco |

## HU01 — Cadastrar-se no sistema

Como Ana, visitante, eu quero me cadastrar informando meus dados de identificação, para que eu
possa utilizar o sistema, já que o acesso só é liberado após cadastro prévio.

Critérios de aceitação:
- Exige RG, CPF, nome e endereço para concluir o cadastro.
- Recusa o cadastro se o CPF já estiver registrado no sistema.
- O cadastro define o perfil do usuário, cliente ou agente.
- Sem cadastro, nenhuma funcionalidade do sistema fica acessível.

## HU02 — Realizar login

Como Ana, usuária do sistema, eu quero informar minha senha para validar meu login, para que
apenas usuários autorizados acessem minha conta e meus pedidos.

Critérios de aceitação:
- Senha correta concede acesso ao sistema.
- Senha incorreta bloqueia o acesso e informa o erro.
- Vale para os dois perfis de usuário, cliente e agente.
- O perfil autenticado determina as funcionalidades exibidas.

## HU03 — Manter dados do contratante

Como Ana, cliente, eu quero cadastrar e atualizar meus dados de identificação e minha profissão,
para que os agentes tenham informação correta ao analisar meus pedidos.

Critérios de aceitação:
- Armazena RG, CPF, nome, endereço e profissão do contratante.
- Permite alterar os dados a qualquer momento.
- A alteração não modifica os dados dos pedidos já avaliados.

## HU04 — Registrar entidades empregadoras

Como Ana, cliente, eu quero registrar até 3 entidades empregadoras com o respectivo rendimento
auferido, para que minha capacidade financeira seja avaliada corretamente.

Critérios de aceitação:
- Permite registrar até 3 entidades empregadoras.
- Bloqueia o registro da 4ª entidade empregadora e informa o motivo.
- Cada entidade exige o nome e o rendimento auferido pelo contratante.
- Remover uma empregadora libera a vaga para um novo registro.

## HU05 — Criar pedido de aluguel

Como Ana, cliente, eu quero criar um pedido de aluguel escolhendo o automóvel e a modalidade de
contrato, para que eu possa solicitar o uso do veículo pela internet.

Critérios de aceitação:
- Exige um automóvel do catálogo e uma modalidade, locação, assinatura ou leasing.
- O pedido é vinculado ao cliente autenticado.
- O pedido é criado com a situação "em análise" e encaminhado aos agentes.
- Só permite criar o pedido se os dados do contratante estiverem preenchidos.

## HU06 — Alterar pedido de aluguel

Como Ana, cliente, eu quero alterar um pedido meu que ainda não foi avaliado, para que eu possa
corrigir o automóvel ou a modalidade sem precisar refazer a solicitação.

Critérios de aceitação:
- Permite alterar o automóvel e a modalidade enquanto não houver parecer registrado.
- Bloqueia a alteração assim que o pedido é avaliado por um agente.
- O cliente só altera os próprios pedidos.

## HU07 — Consultar pedido de aluguel

Como Ana, cliente, eu quero consultar meus pedidos e a situação de cada um, para que eu saiba se
já foram avaliados e qual foi o parecer.

Critérios de aceitação:
- Lista apenas os pedidos do cliente autenticado.
- Exibe situação, modalidade e automóvel de cada pedido.
- Exibe o parecer do agente, com justificativa, quando o pedido já foi avaliado.

## HU08 — Cancelar pedido de aluguel

Como Ana, cliente, eu quero cancelar um pedido meu que ainda não foi avaliado, para que eu não
fique preso a uma solicitação da qual desisti.

Critérios de aceitação:
- Permite cancelar apenas pedidos sem parecer registrado.
- O pedido cancelado deixa de aparecer na fila de análise dos agentes.
- O pedido cancelado permanece consultável pelo cliente, com a situação "cancelado".

## HU09 — Consultar pedidos recebidos

Como Carlos, agente, eu quero consultar os pedidos encaminhados para análise, para que eu possa
organizar minha fila de avaliação.

Critérios de aceitação:
- Lista somente os pedidos com situação "em análise".
- Exibe os dados de identificação do contratante, sua profissão, suas entidades empregadoras e os
  respectivos rendimentos.
- Exibe o automóvel solicitado e a modalidade pretendida.
- Pedidos cancelados ou já avaliados não aparecem na lista.

## HU10 — Avaliar pedido e registrar parecer

Como Carlos, agente, eu quero analisar o pedido do ponto de vista financeiro e registrar meu
parecer, para que o cliente saiba se a solicitação foi aprovada.

Critérios de aceitação:
- O parecer registra a data, o resultado, aprovado ou reprovado, e a justificativa.
- Um pedido avaliado deixa de ser alterável ou cancelável pelo cliente.
- Parecer positivo encaminha o pedido para a consideração do cliente.
- Parecer negativo encerra o pedido, mantendo a justificativa consultável.

## HU11 — Decidir sobre pedido aprovado

Como Ana, cliente, eu quero decidir se aceito um pedido que recebeu parecer positivo, para que o
aluguel só avance para o contrato quando eu confirmar.

Critérios de aceitação:
- A decisão só é oferecida em pedidos com parecer positivo.
- Aceitar o pedido encaminha o aluguel para a execução do contrato.
- Recusar o pedido encerra a solicitação sem gerar contrato.

## HU12 — Executar contrato de aluguel

Como Ana, cliente, eu quero que o contrato da modalidade escolhida seja gerado após minha
confirmação, para que o aluguel do veículo passe a valer formalmente.

Critérios de aceitação:
- Gera um contrato de locação, assinatura ou leasing conforme a modalidade do pedido.
- A locação registra o prazo determinado e a data de devolução do veículo.
- A assinatura registra a mensalidade e o dia de vencimento.
- O leasing exige um contrato de crédito associado, concedido por um banco agente.
- O pedido passa a constar com a situação "contratado".

## HU13 — Cadastrar automóvel

Como Carlos, agente, eu quero cadastrar os automóveis disponíveis, com placa, ano, marca e modelo,
para que os clientes possam solicitá-los em seus pedidos de aluguel.

Critérios de aceitação:
- Exige placa, ano, marca e modelo.
- Recusa o cadastro se algum desses dados não for informado.
- Recusa o cadastro se a placa já constar no sistema.
- Após o cadastro, o automóvel fica disponível para novos pedidos.

## HU14 — Registrar propriedade do automóvel

Como Carlos, agente, eu quero registrar a quem pertence o automóvel, cliente, empresa ou banco,
para que a titularidade fique correta conforme a modalidade contratada.

Critérios de aceitação:
- Todo automóvel tem exatamente um proprietário registrado.
- O proprietário pode ser um cliente, uma empresa ou um banco.
- Permite alterar o proprietário quando a modalidade contratada transferir a titularidade.

## HU15 — Conceder contrato de crédito

Como Daniela, analista de um banco agente, eu quero conceder o contrato de crédito associado a um
leasing, para que o aluguel de longo prazo seja viabilizado financeiramente.

Critérios de aceitação:
- Aplica-se apenas a contratos na modalidade leasing.
- Registra o valor financiado, a taxa de juros e o número de parcelas.
- O contrato de crédito fica vinculado ao leasing e ao banco concedente.
- Sem contrato de crédito concedido, o leasing não é efetivado.
