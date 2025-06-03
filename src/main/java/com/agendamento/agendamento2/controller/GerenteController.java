package com.agendamento.agendamento2.controller;

import com.agendamento.agendamento2.model.Cliente;
import com.agendamento.agendamento2.model.Profissional;
import com.agendamento.agendamento2.service.ClienteService;
import com.agendamento.agendamento2.service.ProfissionalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/gerente")
public class GerenteController {

    private final ClienteService clienteService;
    private final ProfissionalService profissionalService;

    public GerenteController(ClienteService clienteService, ProfissionalService profissionalService) {
        this.clienteService = clienteService;
        this.profissionalService = profissionalService;
    }

    @GetMapping("/menu")
    public String menu() {
        return "menu-gerente";
    }

   

    // Cliente
    @GetMapping("/cadastrar-cliente")
    public String formCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastro-cliente";
    }

    @PostMapping("/salvar-cliente")
    public String salvarCliente(@ModelAttribute Cliente cliente, RedirectAttributes ra) {
        clienteService.salvar(cliente);
        ra.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
        return "redirect:/gerente/lista-clientes";
    }

    @GetMapping("/lista-clientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "lista-clientes";
    }

    // Profissional
    @GetMapping("/cadastrar-profissional")
    public String formProfissional(Model model) {
        model.addAttribute("profissional", new Profissional());
        return "cadastro-profissional";
    }

    @PostMapping("/salvar-profissional")
    public String salvarProfissional(@ModelAttribute Profissional profissional, RedirectAttributes ra) {
        profissionalService.salvar(profissional);
        ra.addFlashAttribute("mensagem", "Profissional cadastrado com sucesso!");
        return "redirect:/gerente/lista-profissionais";
    }

    @GetMapping("/lista-profissionais")
    public String listarProfissionais(Model model) {
        model.addAttribute("profissionais", profissionalService.listarTodos());
        return "lista-profissionais";
    }
}
