/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TiagoMartins
 */
@Entity
@Table(name = "USERLOGIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userlogin.findAll", query = "SELECT u FROM Userlogin u"),
    @NamedQuery(name = "Userlogin.findByUsername", query = "SELECT u FROM Userlogin u WHERE u.username = :username"),
    @NamedQuery(name = "Userlogin.findByPassword", query = "SELECT u FROM Userlogin u WHERE u.password = :password"),
    @NamedQuery(name = "Userlogin.findByTipo", query = "SELECT u FROM Userlogin u WHERE u.tipo = :tipo"),
    @NamedQuery(name = "Userlogin.findById", query = "SELECT u FROM Userlogin u WHERE u.id = :id")})
public class Userlogin implements Serializable {

    @OneToMany(mappedBy = "iduser")
    private Collection<Noticia> noticiaCollection;
    @OneToMany(mappedBy = "usersubs")
    private Collection<Subs> subsCollection;

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private int tipo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    public Userlogin() {
    }

    public Userlogin(Integer id) {
        this.id = id;
    }

    public Userlogin(Integer id, String username, String password, int tipo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tipo = tipo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof Userlogin)) {
            return false;
        }
        Userlogin other = (Userlogin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BD.Userlogin[ id=" + id + " ]";
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
    
}
