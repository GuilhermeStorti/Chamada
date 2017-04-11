package com.curso.beans.Aluno;

import com.curso.entity.Aluno;
import com.curso.utils.JpaUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by guilherme on 06/04/17.
 */
@ViewScoped
@ManagedBean(name = "gerenciarAlunosBean")
public class GerenciarAlunosBean {

    @PostConstruct
    public void init() {
        this.tipoBusca = 'A';
        this.exibir = false;
        this.editar = false;
        carregarLista();
    }

    private List<Aluno> alunos;
    private Aluno aluno;
    private char tipoBusca;
    private boolean exibir;
    private boolean editar;

    public void carregarLista(){
        if(this.tipoBusca == 'T'){
            EntityManager manager = JpaUtil.getManager();
            alunos = manager.createQuery("from Aluno", Aluno.class).getResultList();
            JpaUtil.closeManager(manager);
        }else{
            EntityManager manager = JpaUtil.getManager();
            alunos = manager.createNamedQuery("Aluno.findByStatus", Aluno.class)
                    .setParameter("status", tipoBusca)
                    .getResultList();
            JpaUtil.closeManager(manager);
        }
    }

    public void salvar(){
        EntityManager manager = JpaUtil.getManager();
        manager.getTransaction().begin();
        Aluno antigo = manager.find(Aluno.class, aluno.getId());
        antigo = aluno;
        manager.merge(antigo);
        manager.getTransaction().commit();
        JpaUtil.closeManager(manager);
        carregarLista();
        fecharCd();
    }

    public void exibirCd(Aluno aluno){
        this.aluno = aluno;
        this.exibir = true;
    }

    public void editarCd(Aluno aluno){
        this.aluno = aluno;
        this.exibir = true;
        this.editar = true;
    }

    public void fecharCd(){
        this.aluno = new Aluno();
        this.exibir = false;
        this.editar = false;
    }

    public char getTipoBusca() {
        return tipoBusca;
    }

    public void setTipoBusca(char tipoBusca) {
        this.tipoBusca = tipoBusca;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public boolean isExibir() {
        return exibir;
    }

    public void setExibir(boolean exibir) {
        this.exibir = exibir;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }
}