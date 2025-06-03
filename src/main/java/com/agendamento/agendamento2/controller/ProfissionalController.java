package com.agendamento.agendamento2.controller;

import com.agendamento.agendamento2.model.Profissional;
import com.agendamento.agendamento2.service.ProfissionalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {

    private final ProfissionalService service;

    public ProfissionalController(ProfissionalService service) {
        this.service = service;
    }

    @GetMapping
    public List<Profissional> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Profissional salvar(@RequestBody Profissional profissional) {
        return service.salvar(profissional);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
