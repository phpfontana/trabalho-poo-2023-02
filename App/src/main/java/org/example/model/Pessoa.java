package org.example.model;

import org.example.anotations.Column;
import org.example.anotations.Table;

import java.util.UUID;
@Table(name = "pessoa")
public class Pessoa {
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @Column(name = "tipo")
    private String tipo;

    public Pessoa() {
    }

    public Pessoa(UUID id, String nome, String cpfCnpj, String tipo) {
        this.id = id;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.tipo = tipo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    // Constructors, getters, and setters
}