package com.agendamento.agendamento2.repository;

import com.agendamento.agendamento2.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
