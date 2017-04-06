
package com.curso.beans;

import com.curso.entidades.Usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="usuarioBean")
@SessionScoped
public class UsuarioBean {

    private String nome;
    private Boolean autenticado = false;
    private Usuario usuario;

    public Boolean autenticar(String nome, String senha) {
        this.nome = nome;
        return autenticado = (nome != null && senha != null);
    }

    public Boolean isAutenticado() {
        return autenticado;
    }

    public String getNome() {
        return nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}