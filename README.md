# Sistema de Aluguel de Carros

Projeto II da disciplina **Laboratório de Desenvolvimento de Software** —
Engenharia de Software, PUC Minas, Campus Lourdes (2026/2).

## Descrição do sistema

Empresa do ramo de aluguel de carros deseja permitir que seus clientes efetuem,
cancelem e modifiquem pedidos de aluguel pela internet. O sistema só pode ser
utilizado após cadastro prévio.

- **Clientes** criam, alteram, consultam e cancelam seus próprios pedidos
  enquanto estes ainda não tiverem sido avaliados.
- **Agentes** (empresas e bancos) analisam os pedidos do ponto de vista
  financeiro e registram o respectivo parecer.
- Em caso de parecer positivo, o pedido volta para a consideração do cliente,
  que decide se o aluguel avança para a execução do contrato.
- Do contratante armazenam-se RG, CPF, nome, endereço, profissão e até **3
  entidades empregadoras**, cada uma com o respectivo rendimento.
- O aluguel ocorre em três modalidades: **locação** (prazo determinado, com
  devolução), **assinatura** (uso recorrente mediante mensalidade) e **leasing**
  (longo prazo associado a contrato de crédito concedido por um banco agente).
  Conforme a modalidade, o automóvel pode ser propriedade do cliente, da empresa
  ou do banco.
- Dos automóveis registram-se **placa, ano, marca e modelo**.

Arquitetura: servidor central ligado aos computadores locais dos clientes e aos
agentes pela internet, subdividido em dois subsistemas — gestão de pedidos e
contratos, e construção dinâmica das páginas web. A implementação será em
**Java, com arquitetura MVC**.

## Artefatos

| Artefato | Arquivo |
|---|---|
| Diagrama de Casos de Uso | [`docs/diagramas/casos-de-uso.puml`](docs/diagramas/casos-de-uso.puml) · [`.png`](docs/diagramas/casos-de-uso.png) |
| Diagrama de Classes | [`docs/diagramas/classes.puml`](docs/diagramas/classes.puml) · [`.png`](docs/diagramas/classes.png) |
| Diagrama de Pacotes (Visão Lógica) | [`docs/diagramas/pacotes.puml`](docs/diagramas/pacotes.puml) · [`.png`](docs/diagramas/pacotes.png) |
| Diagrama de Componentes | [`docs/diagramas/componentes.puml`](docs/diagramas/componentes.puml) |
| Diagrama de Implantação | [`docs/diagramas/implantacao.puml`](docs/diagramas/implantacao.puml) |
| Histórias de Usuário | [`docs/historias-de-usuario.md`](docs/historias-de-usuario.md) |
| Contribuições semanais | [`docs/contribuicoes/`](docs/contribuicoes/) |
| Uso de Inteligência Artificial | [`docs/uso-de-ia.md`](docs/uso-de-ia.md) |

> Os arquivos `.png` devem ser exportados a partir dos `.puml` e versionados
> junto aos fontes — o `.puml` sozinho não é visualizável no GitHub.

## Estrutura do repositório

```
lab02-aluguel-carros/
|-- README.md
|-- .gitignore
|-- docs/
|   |-- diagramas/
|   |   |-- casos-de-uso.puml / .png
|   |   |-- classes.puml / .png
|   |   |-- pacotes.puml / .png
|   |-- historias-de-usuario.md
|   |-- uso-de-ia.md
|   |-- contribuicoes/
|       |-- integrante-1/sprint1.md
|       |-- integrante-2/sprint1.md
|       |-- integrante-3/sprint1.md
|-- src/            (a partir da Sprint 2)
```

## Sprints

| Sprint | Entregáveis | Pontos |
|---|---|---|
| **Lab02S01** | Diagrama de Casos de Uso, Histórias do Usuário, Diagrama de Classes, Diagrama de Pacotes (Visão Lógica) + histórico de commits durante a aula | 6,0 |
| Lab02S02 | _a definir_ | — |
| Lab02S03 | Protótipo em Java/MVC, repositório atualizado com todas as versões dos modelos UML | — |

## Equipe

| Integrante | GitHub | Pasta de contribuições |
|---|---|---|
| _Nome 1_ | `@usuario1` | `docs/contribuicoes/integrante-1/` |
| _Nome 2_ | `@usuario2` | `docs/contribuicoes/integrante-2/` |
| _Nome 3_ | `@usuario3` | `docs/contribuicoes/integrante-3/` |

## Como visualizar os diagramas

Abra os arquivos `.puml` em uma das ferramentas:
[PlantUML Online](https://plantuml.online/) ·
[PlantText](https://www.planttext.com/) ·
extensão PlantUML do VS Code.

## Repositório

`https://github.com/<usuario-ou-organizacao>/lab02-aluguel-carros`

## Executar o protótipo

Requer JDK 21 ou superior, Maven e Docker Desktop.

```bash
docker compose up -d postgres
mvn clean package
mvn exec:java
```

Acesse <http://localhost:8080>. O protótipo permite cadastrar e autenticar um cliente, registrar até três empregadoras, cadastrar automóveis, consultar o catálogo, criar, alterar e cancelar pedidos, visualizar o status e decidir sobre pedidos aprovados. A fila do agente está disponível em <http://localhost:8080/agente>, com cadastro de automóvel, aprovação/reprovação e justificativa. Os dados são persistidos no PostgreSQL executado pelo Docker.

O protótipo usa JPA com Hibernate e cria/atualiza as tabelas automaticamente (`hibernate.hbm2ddl.auto=update`). O volume Docker `aluguel_postgres_data` preserva os dados mesmo após reiniciar a aplicação. Ao aceitar um pedido aprovado, o sistema gera e persiste um contrato de locação, assinatura ou leasing com seus dados específicos. Controle de usuários agentes e segurança de produção permanecem como evoluções futuras.

CPF e RG são normalizados antes da persistência e exibidos com formatação amigável no painel e na fila do agente.

## Organização da implementação

- `model`: entidades e enums do domínio.
- `repository`: acesso transacional ao PostgreSQL por JPA.
- `service`: regras de cadastro e pedidos.
- `controller`: rotas HTTP e sessão.
- `view`: páginas HTML/CSS renderizadas pelo servidor.
