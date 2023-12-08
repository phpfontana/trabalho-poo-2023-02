package org.example.model;

import org.example.anotations.Column;
import org.example.anotations.Table;

import java.util.UUID;
@Table(name = "pessoa")
public class Pessoa {
    @Column(name = "id")
    private UUID id;
    private String nome;
    private String cpfCnpj;
    private char tipo;

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

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
    // Constructors, getters, and setters
}