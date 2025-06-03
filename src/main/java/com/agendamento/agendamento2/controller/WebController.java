package com.agendamento.agendamento2.controller;

import com.agendamento.agendamento2.model.Agendamento;
import com.agendamento.agendamento2.service.AgendamentoService;
import com.agendamento.agendamento2.service.BebidaService;
import com.agendamento.agendamento2.service.ClienteService;
import com.agendamento.agendamento2.service.ProfissionalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {

    private final AgendamentoService agendamentoService;
    private final ClienteService clienteService;
    private final ProfissionalService profissionalService;
    private final BebidaService bebidaService;

    public WebController(AgendamentoService agendamentoService, ClienteService clienteService,
                         ProfissionalService profissionalService, BebidaService bebidaService) {
        this.agendamentoService = agendamentoService;
        this.clienteService = clienteService;
        this.profissionalService = profissionalService;
        this.bebidaService = bebidaService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/agendamentos")
    public String listarAgendamentos(Model model) {
        model.addAttribute("agendamentos", agendamentoService.listarTodos());
        return "agendamentos";
    }

    @GetMapping("/agendamentos/novo")
    public String novoAgendamentoForm(Model model) {
        model.addAttribute("agendamento", new Agendamento());
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("profissionais", profissionalService.listarTodos());
        model.addAttribute("bebidas", bebidaService.listarTodas());
        return "novoAgendamento";
    }

    @PostMapping("/agendamentos")
public String salvarAgendamento(@ModelAttribute Agendamento agendamento, RedirectAttributes redirectAttributes) {
    agendamentoService.salvar(agendamento);
    redirectAttributes.addFlashAttribute("mensagemSucesso", "Agendamento realizado com sucesso.");
    return "redirect:/agendamentos";
}

} 