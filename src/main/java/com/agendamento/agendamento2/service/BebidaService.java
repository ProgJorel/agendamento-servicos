package com.agendamento.agendamento2.service;

import com.agendamento.agendamento2.exception.ResourceNotFoundException;
import com.agendamento.agendamento2.model.Bebida;
import com.agendamento.agendamento2.repository.BebidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BebidaService {

    private final BebidaRepository bebidaRepository;

    public BebidaService(BebidaRepository bebidaRepository) {
        this.bebidaRepository = bebidaRepository;
    }

    public List<Bebida> listarTodas() {
        return bebidaRepository.findAll();
    }

    public Bebida buscarPorId(Long id) {
        return bebidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bebida com ID " + id + " não encontrada"));
    }

    public Bebida salvar(Bebida bebida) {
        return bebidaRepository.save(bebida);
    }

    public void excluir(Long id) {
        if (!bebidaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Não foi possível excluir: Bebida com ID " + id + " não encontrada");
        }
        bebidaRepository.deleteById(id);
    }
}
