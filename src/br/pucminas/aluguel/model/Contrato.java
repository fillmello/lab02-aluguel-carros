package br.pucminas.aluguel.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_contrato")
public abstract class Contrato {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataInicio = LocalDate.now();

    protected Contrato() { }
    public abstract String descricao();
}
