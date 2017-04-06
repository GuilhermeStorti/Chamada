package com.curso.beans;

import com.curso.entidades.Autenticador;
import com.curso.entidades.Usuario;
import com.curso.utils.JpaUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

/**
 *
 * @author guilherme
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {


    @PostConstruct
    private void init(){
        this.autenticador = new Autenticador();
        usuario = new Usuario();
    }
    
    private Usuario usuario;
    private Autenticador autenticador;

    public String entrar() {
            EntityManager manager = JpaUtil.getManager();

            Usuario usuarioBuscado = manager.createNamedQuery("Usuario.findByLogin", Usuario.class)
                    .setParameter("login", usuario.getLogin())
                    .getSingleResult();
            JpaUtil.closeManager(manager);
            return "Home?faces-redirect=true";
    }

    public void limpar(){
        System.out.println("teste");
        usuario.setLogin("");
        usuario.setSenha("");
    }

    public String logout() {
        autenticador.setLogado(false);
        HttpSession session =
                (HttpSession)FacesContext.getCurrentInstance()
                        .getExternalContext().getSession(true);
        if(session != null) {
            session.invalidate();
        }
        return "Login?faces-redirect=true";
    }

    public Autenticador getAutenticador() {
        return autenticador;
    }

    public void setAutenticador(Autenticador autenticador) {
        this.autenticador = autenticador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
