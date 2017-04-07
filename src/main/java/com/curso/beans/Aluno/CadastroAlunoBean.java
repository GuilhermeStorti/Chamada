package com.curso.beans.Aluno;

import com.curso.entity.Aluno;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by guilherme on 06/04/17.
 */
@ViewScoped
@ManagedBean(name = "cadastroAlunoBean")
public class CadastroAlunoBean {

    @PostConstruct
    public void init() {
        aluno = new Aluno();
    }

    private Aluno aluno;

    public void limpar(){
        aluno = new Aluno();
    }

    public void salvar(){

    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
