
package com.agendamento.agendamento2.repository;

import com.agendamento.agendamento2.model.Gerente;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    Optional<Gerente> findByUsuarioAndSenha(String usuario, String senha);
}