# Contribuições — Filipe Melo — Sprint 2 (Lab02S02)

## Semana 1

**Contribuição:** Revisei os diagramas de classes e pacotes e conferi a separação entre controller, service, model, view e repository no protótipo.

Ajudei a implementar alteração e cancelamento de pedidos em análise e a atualizar o diagrama de classes.

**Decisões:** Mantivemos a camada service como responsável pelas regras de negócio, evitando colocar validações de status diretamente na view.


O pedido só pode ser alterado ou cancelado enquanto não possuir parecer,
preservando o histórico após a avaliação.
