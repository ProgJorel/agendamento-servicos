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
