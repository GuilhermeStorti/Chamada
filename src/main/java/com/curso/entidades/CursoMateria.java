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
@Table(name = "curso_materia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CursoMateria.findAll", query = "SELECT c FROM CursoMateria c")
    , @NamedQuery(name = "CursoMateria.findById", query = "SELECT c FROM CursoMateria c WHERE c.id = :id")
    , @NamedQuery(name = "CursoMateria.findByIdCurso", query = "SELECT c FROM CursoMateria c WHERE c.idCurso = :idCurso")
    , @NamedQuery(name = "CursoMateria.findByIdMateria", query = "SELECT c FROM CursoMateria c WHERE c.idMateria = :idMateria")})
public class CursoMateria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_curso")
    private int idCurso;
    @Basic(optional = false)
    @Column(name = "id_materia")
    private int idMateria;

    public CursoMateria() {
    }

    public CursoMateria(Integer id) {
        this.id = id;
    }

    public CursoMateria(Integer id, int idCurso, int idMateria) {
        this.id = id;
        this.idCurso = idCurso;
        this.idMateria = idMateria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
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
        if (!(object instanceof CursoMateria)) {
            return false;
        }
        CursoMateria other = (CursoMateria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.curso.entidades.CursoMateria[ id=" + id + " ]";
    }
    
}
