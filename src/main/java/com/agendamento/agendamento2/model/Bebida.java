package com.agendamento.agendamento2.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Bebida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
}
