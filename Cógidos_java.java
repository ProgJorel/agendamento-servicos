__________________________________________________________________________________________
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
__________________________________________________________________________________________
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
__________________________________________________________________________________________
package com.agendamento.agendamento2.controller;

import com.agendamento.agendamento2.model.Cliente;
import com.agendamento.agendamento2.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cliente> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Cliente salvar(@RequestBody Cliente cliente) {
        return service.salvar(cliente);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
__________________________________________________________________________________________
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
__________________________________________________________________________________________

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
__________________________________________________________________________________________

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
__________________________________________________________________________________________
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
__________________________________________________________________________________________
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
__________________________________________________________________________________________
package com.agendamento.agendamento2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = "Valor inválido para o campo: " + ex.getName();
        return ResponseEntity.badRequest().body(Map.of("erro", message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralError(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(Map.of("erro", "Erro interno no servidor", "detalhes", ex.getMessage()));
    }
}
__________________________________________________________________________________________
package com.agendamento.agendamento2.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
__________________________________________________________________________________________
package com.agendamento.agendamento2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;
    private LocalTime horario;
    private String tipoAtendimento;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "bebida_id")
    private Bebida bebida;
}
__________________________________________________________________________________________
package com.agendamento.agendamento2.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Bebida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
}
__________________________________________________________________________________________
package com.agendamento.agendamento2.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private String email;
}
__________________________________________________________________________________________

package com.agendamento.agendamento2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gerente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String usuario;

    @Column(nullable = false)
    private String senha;
}
__________________________________________________________________________________________
package com.agendamento.agendamento2.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "profissional")
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String telefone;

    @Column(length = 11)
    private String cpf;

    @Column(name = "data_cadastro", nullable = true)
    private LocalDateTime dataCadastro = LocalDateTime.now();

    // ✅ Construtor padrão
    public Profissional() {}

    // ✅ Construtor com argumentos
    public Profissional(String nome, String telefone, String cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataCadastro = LocalDateTime.now();
    }

    // ✅ Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
__________________________________________________________________________________________
package com.agendamento.agendamento2.repository;

import com.agendamento.agendamento2.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
__________________________________________________________________________________________
package com.agendamento.agendamento2.repository;

import com.agendamento.agendamento2.model.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BebidaRepository extends JpaRepository<Bebida, Long> {
}
__________________________________________________________________________________________
package com.agendamento.agendamento2.repository;

import com.agendamento.agendamento2.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
__________________________________________________________________________________________

package com.agendamento.agendamento2.repository;

import com.agendamento.agendamento2.model.Gerente;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    Optional<Gerente> findByUsuarioAndSenha(String usuario, String senha);
}
__________________________________________________________________________________________
package com.agendamento.agendamento2.repository;

import com.agendamento.agendamento2.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
}
__________________________________________________________________________________________
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
__________________________________________________________________________________________
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
__________________________________________________________________________________________
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
__________________________________________________________________________________________
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
__________________________________________________________________________________________
_