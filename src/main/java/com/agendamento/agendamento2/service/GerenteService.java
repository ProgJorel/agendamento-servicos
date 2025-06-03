package com.agendamento.agendamento2.service;

import com.agendamento.agendamento2.model.Gerente;
import com.agendamento.agendamento2.repository.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    public boolean autenticar(String usuario, String senha) {
        return gerenteRepository.findByUsuarioAndSenha(usuario, senha).isPresent();
    }
}
