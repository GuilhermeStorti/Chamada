package com.curso.entity;

/**
 * Created by guilherme on 21/11/16.
 */
public class Autenticador {

    private Usuario usuario;
    private boolean logado;

    public Autenticador() {
    }

    public Autenticador(Usuario usuario, boolean logado) {
        this.usuario = usuario;
        this.logado = logado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }
}
