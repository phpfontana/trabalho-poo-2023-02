package org.example.enumModel;

public enum TipoPessoa {
    PESSOAJURIDICA("j"), PESSOAFISICA("f");

    private final String value;

    TipoPessoa(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}