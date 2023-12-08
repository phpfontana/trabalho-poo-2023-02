package org.example.model;

import org.example.anotations.Column;
import org.example.anotations.Table;

import java.util.UUID;

@Table(name = "advogado")
public class Advogado {
    @Column(name = "id")
    private UUID id;

    @Column(name = "numero_oab")
    private String numeroOAB;

    @Column(name = "estado_oab")
    private String estadoOAB;

    public Advogado() {
    }

    public Advogado(UUID id, String numeroOAB, String estadoOAB) {
        this.id = id;
        this.numeroOAB = numeroOAB;
        this.estadoOAB = estadoOAB;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumeroOAB() {
        return numeroOAB;
    }

    public void setNumeroOAB(String numeroOAB) {
        this.numeroOAB = numeroOAB;
    }

    public String getEstadoOAB() {
        return estadoOAB;
    }

    public void setEstadoOAB(String estadoOAB) {
        this.estadoOAB = estadoOAB;
    }
}

