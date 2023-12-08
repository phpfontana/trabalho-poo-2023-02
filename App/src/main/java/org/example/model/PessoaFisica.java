package org.example.model;

import org.example.anotations.Column;
import org.example.anotations.Table;

import java.util.UUID;
import java.sql.Date;


@Table(name = "pessoa_fisica")
public class PessoaFisica {

    @Column(name = "id")
    private UUID id;


    @Column(name = "rg")
    private String rg;


    public PessoaFisica() {
    }

    public PessoaFisica(UUID id, String rg) {
        this.id = id;
        this.rg = rg;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

}
