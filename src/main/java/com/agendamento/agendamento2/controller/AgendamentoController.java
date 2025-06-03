package com.agendamento.agendamento2.controller;

import com.agendamento.agendamento2.model.Agendamento;
import com.agendamento.agendamento2.model.Bebida;
import com.agendamento.agendamento2.repository.BebidaRepository;
import com.agendamento.agendamento2.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

   
    private final AgendamentoService service;

    @Autowired
    private BebidaRepository bebidaRepository;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Agendamento> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Agendamento salvar(@RequestBody Agendamento agendamento) {
        if (agendamento.getBebida() != null && agendamento.getBebida().getId() != null) {
            Bebida bebida = bebidaRepository.findById(agendamento.getBebida().getId())
                .orElseThrow(() -> new RuntimeException("Bebida não encontrada"));
            agendamento.setBebida(bebida);
        }
        return service.salvar(agendamento);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
