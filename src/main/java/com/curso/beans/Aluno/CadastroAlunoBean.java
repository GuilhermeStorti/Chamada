package com.curso.beans.Aluno;

import com.curso.entity.*;
import com.curso.utils.JpaUtil;
import com.curso.utils.PerfilAcesso;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by guilherme on 06/04/17.
 */
@ViewScoped
@ManagedBean(name = "cadastroAlunoBean")
public class CadastroAlunoBean {

    @PostConstruct
    public void init() {
        manager = JpaUtil.getManager();
        manager.getTransaction().begin();
        aluno = new Aluno();
        aluno.setIdContato(new Contato());
        aluno.setIdEndereco(new Endereco());
        aluno.setIdUsuario(new Usuario());
    }

    private EntityManager manager;
    private Aluno aluno;

    public void limpar(){
        aluno = new Aluno();
        aluno.setIdContato(new Contato());
        aluno.setIdEndereco(new Endereco());
        aluno.setIdUsuario(new Usuario());
    }

    public void salvar(){
        if(verificaUser()){
            try {
                aluno.setIdContato(manager.merge(aluno.getIdContato()));
                aluno.setIdEndereco(manager.merge(aluno.getIdEndereco()));
                Usuario usuario = aluno.getIdUsuario();
                usuario.setIdPerfil(buscaPerfilAluno());
                aluno.setIdUsuario(manager.merge(usuario));
                manager.merge(aluno);
                manager.getTransaction().commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aluno salvo com sucesso!", ""));
                limpar();
            }catch (Exception ex){
                manager.getTransaction().rollback();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!", ex.getMessage()));
            }
        }else{
            manager.getTransaction().rollback();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario já existe!!", ""));
        }
    }

    private boolean verificaUser(){
        List<Usuario> lista = manager.createNamedQuery("Usuario.findByLogin")
                .setParameter("login", aluno.getIdUsuario().getLogin()).getResultList();

        if(lista == null || lista.size() == 0)
            return true;

        return false;
    }

    private Perfil buscaPerfilAluno(){
        try{
            return manager.createNamedQuery("Perfil.findById", Perfil.class)
                    .setParameter("id", PerfilAcesso.ALUNO.getValue())
                    .getSingleResult();
        }catch (Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao buscar Perfil.", ex.getMessage()));
            return null;
        }

    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
