
package com.curso.beans;


import com.curso.entity.Usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="usuarioBean")
@SessionScoped
public class UsuarioBean {

    public UsuarioBean(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioBean() {
    }

    private String nome;
    private Boolean autenticado = false;
    private Usuario usuario;

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