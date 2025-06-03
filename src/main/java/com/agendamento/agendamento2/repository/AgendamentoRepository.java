package com.agendamento.agendamento2.repository;

import com.agendamento.agendamento2.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
