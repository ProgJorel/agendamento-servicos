
package com.agendamento.agendamento2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gerente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    
    @Column(nullable = false, unique = true)
    private String usuario;

    @Column(nullable = false)
    private String senha;
}
