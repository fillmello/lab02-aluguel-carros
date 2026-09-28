# Conformidade com o LAB02

## Entregáveis técnicos

| Requisito | Situação | Evidência |
|---|---|---|
| Diagrama de casos de uso em PlantUML | Atendido | `docs/diagramas/casos-de-uso.puml` |
| Histórias de usuário alinhadas | Atendido | `docs/historias-de-usuario.md` |
| Diagrama de classes em PlantUML | Atendido | `docs/diagramas/classes.puml` |
| Diagrama de pacotes MVC | Atendido | `docs/diagramas/pacotes.puml` |
| Diagrama de componentes | Atendido | `docs/diagramas/componentes.puml` |
| Diagrama de implantação | Atendido | `docs/diagramas/implantacao.puml` |
| Sistema web em Java | Atendido | `src/br/pucminas/aluguel/Application.java` |
| Arquitetura MVC | Atendido | `controller`, `service`, `model`, `view` e `repository` |
| Cadastro e autenticação | Atendido | Rotas `/cadastro` e `/login` |
| Criar e consultar pedido | Atendido | Rota `/pedido` e painel `/pedidos` |
| Alterar e cancelar pedido em análise | Atendido | Serviço e rotas `/pedido/alterar` e `/pedido/cancelar` |
| Avaliar pedido como agente | Atendido | Painel `/agente` e rota `/agente/avaliar` |
| Decidir sobre pedido aprovado | Atendido | Rota `/pedido/decidir` |
| Até três empregadoras com rendimento | Atendido no domínio e rota | `Cliente`, `Empregadora` e `/empregadora` |
| Persistência permanente | Pendente | O protótipo usa `InMemoryRepository`; os dados são reiniciados ao encerrar |
| Contratos detalhados por modalidade | Pendente | O status contratado existe, mas os dados específicos de locação, assinatura e leasing ainda precisam ser implementados |

## Evidências externas

O histórico e o volume de commits durante o horário da aula, a imagem do `git log` e a atualização do board do GitHub precisam ser providenciados pela equipe. Eles não podem ser comprovados ou corrigidos apenas no código local.

Os arquivos PNG dos diagramas também devem ser exportados a partir dos fontes PlantUML e versionados quando a ferramenta de renderização estiver disponível.
