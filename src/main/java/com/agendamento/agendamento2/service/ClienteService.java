package com.agendamento.agendamento2.service;

import com.agendamento.agendamento2.exception.ResourceNotFoundException;
import com.agendamento.agendamento2.model.Cliente;
import com.agendamento.agendamento2.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente com ID " + id + " não encontrado"));
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void excluir(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Não foi possível excluir: Cliente com ID " + id + " não encontrado");
        }
        clienteRepository.deleteById(id);
    }
}
