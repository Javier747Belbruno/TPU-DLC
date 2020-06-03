/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Embeddable
public class PosteoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DOCUMENTO")
    private String documento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PALABRA")
    private String palabra;

    public PosteoPK() {
    }

    public PosteoPK(String documento, String palabra) {
        this.documento = documento;
        this.palabra = palabra;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documento != null ? documento.hashCode() : 0);
        hash += (palabra != null ? palabra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PosteoPK)) {
            return false;
        }
        PosteoPK other = (PosteoPK) object;
        if ((this.documento == null && other.documento != null) || (this.documento != null && !this.documento.equals(other.documento))) {
            return false;
        }
        if ((this.palabra == null && other.palabra != null) || (this.palabra != null && !this.palabra.equals(other.palabra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.resources.PosteoPK[ documento=" + documento + ", palabra=" + palabra + " ]";
    }
    
}
