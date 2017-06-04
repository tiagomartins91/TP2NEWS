/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fábio
 */
@Entity
@Table(name = "NOTICIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Noticia.findAll", query = "SELECT n FROM Noticia n")
    , @NamedQuery(name = "Noticia.findByIdnoticia", query = "SELECT n FROM Noticia n WHERE n.idnoticia = :idnoticia")
    , @NamedQuery(name = "Noticia.findByConteudo", query = "SELECT n FROM Noticia n WHERE n.conteudo = :conteudo")
    , @NamedQuery(name = "Noticia.findByDatan", query = "SELECT n FROM Noticia n WHERE n.datan = :datan")})
public class Noticia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDNOTICIA")
    private Integer idnoticia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "CONTEUDO")
    private String conteudo;
    @Column(name = "DATAN")
    @Temporal(TemporalType.DATE)
    private Date datan;
    @JoinColumn(name = "IDTOP", referencedColumnName = "IDTOPICO")
    @ManyToOne
    private Topico idtop;
    @JoinColumn(name = "IDUSER", referencedColumnName = "ID")
    @ManyToOne
    private Userlogin iduser;

    public Noticia() {
    }

    public Noticia(Integer idnoticia) {
        this.idnoticia = idnoticia;
    }

    public Noticia(Integer idnoticia, String conteudo) {
        this.idnoticia = idnoticia;
        this.conteudo = conteudo;
    }

    public Integer getIdnoticia() {
        return idnoticia;
    }

    public void setIdnoticia(Integer idnoticia) {
        this.idnoticia = idnoticia;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getDatan() {
        return datan;
    }

    public void setDatan(Date datan) {
        this.datan = datan;
    }

    public Topico getIdtop() {
        return idtop;
    }

    public void setIdtop(Topico idtop) {
        this.idtop = idtop;
    }

    public Userlogin getIduser() {
        return iduser;
    }

    public void setIduser(Userlogin iduser) {
        this.iduser = iduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnoticia != null ? idnoticia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Noticia)) {
            return false;
        }
        Noticia other = (Noticia) object;
        if ((this.idnoticia == null && other.idnoticia != null) || (this.idnoticia != null && !this.idnoticia.equals(other.idnoticia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BD.Noticia[ idnoticia=" + idnoticia + " ]";
    }
    
}
