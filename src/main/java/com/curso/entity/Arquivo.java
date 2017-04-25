/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guilherme
 */
@Entity
@Table(name = "arquivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arquivo.findAll", query = "SELECT a FROM Arquivo a")
    , @NamedQuery(name = "Arquivo.findById", query = "SELECT a FROM Arquivo a WHERE a.id = :id")
    , @NamedQuery(name = "Arquivo.findByDescricao", query = "SELECT a FROM Arquivo a WHERE a.descricao = :descricao")})
public class Arquivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Lob
    @Column(name = "dados")
    private byte[] dados;
    @JoinColumn(name = "id_usuario_upload", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario idUsuarioUpload;
    @JoinColumn(name = "id_turma", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Turma idTurma;

    public Arquivo() {
    }

    public Arquivo(Integer id) {
        this.id = id;
    }

    public Arquivo(Integer id, String descricao, byte[] dados) {
        this.id = id;
        this.descricao = descricao;
        this.dados = dados;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getDados() {
        return dados;
    }

    public void setDados(byte[] dados) {
        this.dados = dados;
    }

    public Usuario getIdUsuarioUpload() {
        return idUsuarioUpload;
    }

    public void setIdUsuarioUpload(Usuario idUsuarioUpload) {
        this.idUsuarioUpload = idUsuarioUpload;
    }

    public Turma getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Turma idTurma) {
        this.idTurma = idTurma;
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
        if (!(object instanceof Arquivo)) {
            return false;
        }
        Arquivo other = (Arquivo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.curso.entity.Arquivo[ id=" + id + " ]";
    }
    
}
