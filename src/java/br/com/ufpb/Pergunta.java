/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufpb;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author t1061605
 */
@Entity
@Table(name = "pergunta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pergunta.findAll", query = "SELECT p FROM Pergunta p"),
    @NamedQuery(name = "Pergunta.findById", query = "SELECT p FROM Pergunta p WHERE p.id = :id"),
    @NamedQuery(name = "Pergunta.findByResposta", query = "SELECT p FROM Pergunta p WHERE p.resposta = :resposta")})
public class Pergunta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "PERGUNTA")
    private String pergunta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESPOSTA")
    private double resposta;

    public Pergunta() {
    }

    public Pergunta(Integer id) {
        this.id = id;
    }

    public Pergunta(Integer id, String pergunta, double resposta) {
        this.id = id;
        this.pergunta = pergunta;
        this.resposta = resposta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public double getResposta() {
        return resposta;
    }

    public void setResposta(double resposta) {
        this.resposta = resposta;
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
        if (!(object instanceof Pergunta)) {
            return false;
        }
        Pergunta other = (Pergunta) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "Pergunta{" + "id=" + id + ", pergunta=" + pergunta + ", resposta=" + resposta + '}';
    }

}
