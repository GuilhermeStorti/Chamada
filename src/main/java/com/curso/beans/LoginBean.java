package com.curso.beans;

import com.curso.entity.Autenticador;
import com.curso.entity.Usuario;
import com.curso.utils.JpaUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
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
    private UsuarioBean usuarioBean;

    public String login() {

        try {
            EntityManager manager = JpaUtil.getManager();

            Usuario usuarioBuscado = null;
            try{
                usuarioBuscado= manager.createNamedQuery("Usuario.findByLogin", Usuario.class)
                        .setParameter("login", usuario.getLogin())
                        .getSingleResult();
            }catch (Exception e){

            }
            JpaUtil.closeManager(manager);
            if (usuarioBuscado != null &&
                    usuarioBuscado.getSenha().equals(usuario.getSenha())) {
                usuario = usuarioBuscado;
                autenticador.setLogado(true);
                autenticador.setUsuario(usuarioBuscado);
                return "Home?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(
                                FacesMessage.SEVERITY_WARN, null, "Login ou senha inválido!"));
                usuario = new Usuario();
                return null;
            }
        } catch (Exception e){
            FacesContext.getCurrentInstance()
                    .addMessage("Erro", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        }
        return null;
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
