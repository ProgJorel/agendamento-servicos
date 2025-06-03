package com.agendamento.agendamento2.controller;

import com.agendamento.agendamento2.model.Bebida;
import com.agendamento.agendamento2.service.BebidaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bebidas")
public class BebidaController {

    private final BebidaService service;

    public BebidaController(BebidaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Bebida> listar() {
        return service.listarTodas();
    }
}
