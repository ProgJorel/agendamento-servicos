package com.agendamento.agendamento2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;
    private LocalTime horario;
    private String tipoAtendimento;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "bebida_id")
    private Bebida bebida;
}
