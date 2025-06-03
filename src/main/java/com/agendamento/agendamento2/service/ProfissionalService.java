package com.agendamento.agendamento2.service;

import com.agendamento.agendamento2.exception.ResourceNotFoundException;
import com.agendamento.agendamento2.model.Profissional;
import com.agendamento.agendamento2.repository.ProfissionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    public ProfissionalService(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

    public List<Profissional> listarTodos() {
        return profissionalRepository.findAll();
    }

    public Profissional buscarPorId(Long id) {
        return profissionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional com ID " + id + " não encontrado"));
    }

    public Profissional salvar(Profissional profissional) {
        return profissionalRepository.save(profissional);
    }

    public void excluir(Long id) {
        if (!profissionalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Não foi possível excluir: Profissional com ID " + id + " não encontrado");
        }
        profissionalRepository.deleteById(id);
    }
}
