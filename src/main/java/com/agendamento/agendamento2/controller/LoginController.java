
package com.agendamento.agendamento2.controller;

import com.agendamento.agendamento2.service.GerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    
    @Autowired
    private GerenteService gerenteService;

    @GetMapping("/gerente/login")
    public String exibirLogin() {
        return "login-gerente";
    }

    @PostMapping("/gerente/login")
    public String processarLogin(@RequestParam String usuario, @RequestParam String senha, Model model) {
        if (gerenteService.autenticar(usuario, senha)) {
            return "redirect:/gerente/menu";
        } else {
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return "login-gerente";
        }
    }

    /*@GetMapping("/gerente/menu")
    public String menuGerente() {
        return "menu-gerente";
    }*/
}
