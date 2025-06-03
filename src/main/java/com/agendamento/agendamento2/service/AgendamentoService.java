package com.agendamento.agendamento2.service;

import com.agendamento.agendamento2.exception.ResourceNotFoundException;
import com.agendamento.agendamento2.model.Agendamento;
import com.agendamento.agendamento2.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    
    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    public Agendamento buscarPorId(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento com ID " + id + " não encontrado"));
    }

   
    public Agendamento salvar(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public void excluir(Long id) {
        if (!agendamentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Não foi possível excluir: Agendamento com ID " + id + " não encontrado");
        }
        agendamentoRepository.deleteById(id);
    }
}
