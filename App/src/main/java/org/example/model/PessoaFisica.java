package org.example.model;

import org.example.anotations.Column;
import org.example.anotations.Table;

import java.util.UUID;
import java.sql.Date;


@Table(name = "pessoa_fisica")
public class PessoaFisica {

    @Column(name = "id")
    private UUID id;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Column(name = "rg")
    private String rg;

    @Column(name = "sexo")
    private String sexo;

    public PessoaFisica() {
    }

    public PessoaFisica(UUID id, Date dataNascimento, String rg, String sexo) {
        this.id = id;
        this.dataNascimento = dataNascimento;
        this.rg = rg;
        this.sexo = sexo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
