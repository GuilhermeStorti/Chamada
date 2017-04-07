package com.curso.utils;

/**
 * Created by guilherme on 07/04/17.
 */
public enum PerfilAcesso {
    ALUNO(1, "Aluno"),
    PROFESSOR(2, "Professor"),
    GESTOR(3, "Gestor");

    private int value;

    private String message;

    PerfilAcesso(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public static PerfilAcesso fromValue(int value ) {
        for ( PerfilAcesso s : PerfilAcesso.values() ) {
            if ( s.getValue() == value ) {
                return s;
            }
        }
        return null;
    }

    public String getMessage() {
        return message;
    }

    public int getValue() {
        return value;
    }
}
