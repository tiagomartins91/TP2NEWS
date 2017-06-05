/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fábio
 */
@Entity
@Table(name = "SUBS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subs.findAll", query = "SELECT s FROM Subs s")
    , @NamedQuery(name = "Subs.findByIdsub", query = "SELECT s FROM Subs s WHERE s.idsub = :idsub")
    , @NamedQuery(name = "Subs.findByLastnews", query = "SELECT s FROM Subs s WHERE s.lastnews = :lastnews")})
public class Subs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSUB")
    private Integer idsub;
    @Column(name = "LASTNEWS")
    private Integer lastnews;
    @JoinColumn(name = "TOPSUBS", referencedColumnName = "IDTOPICO")
    @ManyToOne
    private Topico topsubs;
    @JoinColumn(name = "USERSUBS", referencedColumnName = "ID")
    @ManyToOne
    private Userlogin usersubs;

    public Subs() {
    }

    public Subs(Integer idsub) {
        this.idsub = idsub;
    }

    public Integer getIdsub() {
        return idsub;
    }

    public void setIdsub(Integer idsub) {
        this.idsub = idsub;
    }

    public Integer getLastnews() {
        return lastnews;
    }

    public void setLastnews(Integer lastnews) {
        this.lastnews = lastnews;
    }

    public Topico getTopsubs() {
        return topsubs;
    }

    public void setTopsubs(Topico topsubs) {
        this.topsubs = topsubs;
    }

    public Userlogin getUsersubs() {
        return usersubs;
    }

    public void setUsersubs(Userlogin usersubs) {
        this.usersubs = usersubs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsub != null ? idsub.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subs)) {
            return false;
        }
        Subs other = (Subs) object;
        if ((this.idsub == null && other.idsub != null) || (this.idsub != null && !this.idsub.equals(other.idsub))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BD.Subs[ idsub=" + idsub + " ]";
    }
    
}
