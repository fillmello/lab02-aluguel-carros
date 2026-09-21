# Nota de Transparência sobre Uso de Inteligência Artificial

Em conformidade com a política de uso responsável de Inteligência Artificial da
disciplina **Laboratório de Desenvolvimento de Software (PUC Minas, Campus
Lourdes)**, este documento registra onde e como ferramentas de IA foram usadas
neste projeto.

Este documento deve ser **atualizado a cada sprint**, acrescentando um novo bloco
`## Sprint N` sem apagar os anteriores.

## Ferramenta utilizada

| Item | Valor |
|---|---|
| Ferramenta | Claude (Claude Code), da empresa Anthropic |
| Modelo | Claude Opus |
| Forma de uso | Assistência na estruturação do repositório, geração inicial dos diagramas em PlantUML, redação das histórias de usuário e revisão de texto |

## Sprint 1 (Lab02S01)

### O que foi feito com apoio de IA
- Estruturação inicial de pastas do repositório (`docs/diagramas/`,
  `docs/historias-de-usuario.md`, `docs/contribuicoes/`).
- Geração da primeira versão do **Diagrama de Casos de Uso** em PlantUML
  (`docs/diagramas/casos-de-uso.puml`), a partir da descrição do sistema do
  enunciado.
- Geração da primeira versão do **Diagrama de Classes**
  (`docs/diagramas/classes.puml`), incluindo a hierarquia de contratos
  (Locação / Assinatura / Leasing) e a relação do leasing com o contrato de crédito.
- Geração da primeira versão do **Diagrama de Pacotes**
  (`docs/diagramas/pacotes.puml`), refletindo a arquitetura MVC e os dois
  subsistemas descritos no enunciado.
- Redação inicial das **Histórias de Usuário** no formato
  `Como <ator>, eu quero <ação>, para que <benefício>`, com critérios de aceitação.
- Revisão de redação do `README.md`.

### O que foi feito sem apoio de IA
- Revisão, correção e validação de todo o conteúdo gerado, com ajustes de
  modelagem discutidos em grupo.
- Registro das contribuições semanais individuais.

### Revisão humana
Todo o material produzido com apoio de IA foi lido, revisado e ajustado pelos
integrantes do grupo antes de ser commitado. Cada integrante é capaz de explicar
oralmente as decisões de modelagem dos artefatos sob sua responsabilidade.

### Prompts representativos utilizados
- "Com base nesta descrição de sistema de aluguel de carros, identifique os atores
  e proponha os casos de uso em PlantUML."
- "Escreva histórias de usuário no formato INVEST para os casos de uso acima."
- "Proponha um diagrama de classes coerente com os casos de uso, contemplando as
  modalidades locação, assinatura e leasing."
- "Proponha um diagrama de pacotes em arquitetura MVC para este sistema."

## Sprint 2 (Lab02S02)

_A preencher._

## Sprint 3 (Lab02S03)

_A preencher._
