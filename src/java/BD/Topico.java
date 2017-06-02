/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TiagoMartins
 */
@Entity
@Table(name = "TOPICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Topico.findAll", query = "SELECT t FROM Topico t"),
    @NamedQuery(name = "Topico.findByIdtopico", query = "SELECT t FROM Topico t WHERE t.idtopico = :idtopico"),
    @NamedQuery(name = "Topico.findByNometopico", query = "SELECT t FROM Topico t WHERE t.nometopico = :nometopico"),
    @NamedQuery(name = "Topico.findByDatan", query = "SELECT t FROM Topico t WHERE t.datan = :datan")})
public class Topico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTOPICO")
    private Integer idtopico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMETOPICO")
    private String nometopico;
    @Column(name = "DATAN")
    @Temporal(TemporalType.DATE)
    private Date datan;
    @OneToMany(mappedBy = "idtop")
    private Collection<Noticia> noticiaCollection;
    @OneToMany(mappedBy = "topsubs")
    private Collection<Subs> subsCollection;

    public Topico() {
    }

    public Topico(Integer idtopico) {
        this.idtopico = idtopico;
    }

    public Topico(Integer idtopico, String nometopico) {
        this.idtopico = idtopico;
        this.nometopico = nometopico;
    }

    public Integer getIdtopico() {
        return idtopico;
    }

    public void setIdtopico(Integer idtopico) {
        this.idtopico = idtopico;
    }

    public String getNometopico() {
        return nometopico;
    }

    public void setNometopico(String nometopico) {
        this.nometopico = nometopico;
    }

    public Date getDatan() {
        return datan;
    }

    public void setDatan(Date datan) {
        this.datan = datan;
    }

    @XmlTransient
    public Collection<Noticia> getNoticiaCollection() {
        return noticiaCollection;
    }

    public void setNoticiaCollection(Collection<Noticia> noticiaCollection) {
        this.noticiaCollection = noticiaCollection;
    }

    @XmlTransient
    public Collection<Subs> getSubsCollection() {
        return subsCollection;
    }

    public void setSubsCollection(Collection<Subs> subsCollection) {
        this.subsCollection = subsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtopico != null ? idtopico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Topico)) {
            return false;
        }
        Topico other = (Topico) object;
        if ((this.idtopico == null && other.idtopico != null) || (this.idtopico != null && !this.idtopico.equals(other.idtopico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BD.Topico[ idtopico=" + idtopico + " ]";
    }
    
}
