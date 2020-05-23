
package main.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;


@Embeddable
public class PostingPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_documento")
    private int idDocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_termino")
    private int idTermino;

    public PostingPK() {
    }

    public PostingPK(int idDocumento, int idTermino) {
        this.idDocumento = idDocumento;
        this.idTermino = idTermino;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public int getIdTermino() {
        return idTermino;
    }

    public void setIdTermino(int idTermino) {
        this.idTermino = idTermino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDocumento;
        hash += (int) idTermino;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PostingPK)) {
            return false;
        }
        PostingPK other = (PostingPK) object;
        if (this.idDocumento != other.idDocumento) {
            return false;
        }
        if (this.idTermino != other.idTermino) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.PostingPK[ idDocumento=" + idDocumento + ", idTermino=" + idTermino + " ]";
    }
    
}
