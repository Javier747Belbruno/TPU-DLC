/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Javier
 */
@Entity
@Table(name = "palabraxdocumento")
@NamedQueries({
    @NamedQuery(name = "Posteo.findAll", query = "SELECT p FROM Posteo p"),
    @NamedQuery(name = "Posteo.findByDocumento", query = "SELECT p FROM Posteo p WHERE p.posteoPK.documento = :documento"),
    @NamedQuery(name = "Posteo.findByPalabra", query = "SELECT p FROM Posteo p WHERE p.posteoPK.palabra = :palabra"),
    @NamedQuery(name = "Posteo.findByFrecuencia", query = "SELECT p FROM Posteo p WHERE p.frecuencia = :frecuencia"),
    @NamedQuery(name = "Posteo.countByTermino", query = "SELECT COUNT(p) FROM Posteo p WHERE p.posteoPK.palabra = :palabra"),
    @NamedQuery(name = "Posteo.findByTerminoOrderByFrecuencia", 
            query = "SELECT p FROM Posteo p WHERE p.posteoPK.palabra = :palabra ORDER BY p.frecuencia DESC"),
    @NamedQuery(name = "Posteo.countDocuments", query = "SELECT COUNT(distinct p.posteoPK.documento) FROM Posteo p "),
@NamedQuery(name = "Posteo.getTerms", query = "SELECT p.posteoPK.palabra as PALABRA,count(p.posteoPK.palabra) as FRECUENCIA FROM Posteo p group by p.posteoPK.palabra ")

})
public class Posteo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PosteoPK posteoPK;
    @Column(name = "FRECUENCIA")
    private Integer frecuencia;

    public Posteo() {
    }

    public Posteo(PosteoPK posteoPK) {
        this.posteoPK = posteoPK;
    }

    public Posteo(String documento, String palabra) {
        this.posteoPK = new PosteoPK(documento, palabra);
    }
   
    public Posteo(PosteoPK posteoPK, int frecuencia) {
        this.posteoPK = posteoPK;
        this.frecuencia = frecuencia;
    }

    public PosteoPK getPosteoPK() {
        return posteoPK;
    }

    public void setPosteoPK(PosteoPK posteoPK) {
        this.posteoPK = posteoPK;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (posteoPK != null ? posteoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Posteo)) {
            return false;
        }
        Posteo other = (Posteo) object;
        if ((this.posteoPK == null && other.posteoPK != null) || (this.posteoPK != null && !this.posteoPK.equals(other.posteoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.resources.Posteo[ posteoPK=" + posteoPK + " ]";
    }
    
}
