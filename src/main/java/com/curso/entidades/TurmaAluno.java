/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guilherme
 */
@Entity
@Table(name = "turma_aluno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TurmaAluno.findAll", query = "SELECT t FROM TurmaAluno t")
    , @NamedQuery(name = "TurmaAluno.findById", query = "SELECT t FROM TurmaAluno t WHERE t.id = :id")
    , @NamedQuery(name = "TurmaAluno.findByIdTurma", query = "SELECT t FROM TurmaAluno t WHERE t.idTurma = :idTurma")
    , @NamedQuery(name = "TurmaAluno.findByIdAluno", query = "SELECT t FROM TurmaAluno t WHERE t.idAluno = :idAluno")})
public class TurmaAluno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_turma")
    private int idTurma;
    @Basic(optional = false)
    @Column(name = "id_aluno")
    private int idAluno;

    public TurmaAluno() {
    }

    public TurmaAluno(Integer id) {
        this.id = id;
    }

    public TurmaAluno(Integer id, int idTurma, int idAluno) {
        this.id = id;
        this.idTurma = idTurma;
        this.idAluno = idAluno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TurmaAluno)) {
            return false;
        }
        TurmaAluno other = (TurmaAluno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.curso.entidades.TurmaAluno[ id=" + id + " ]";
    }
    
}
