/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import BD.Noticia;
import BD.Topico;
import BD.Userlogin;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author TiagoMartins
 */
@Stateless
public class Bean {

    @PersistenceContext 
    EntityManager em;
    
    public String user;
    
    //LOGIN
    public Userlogin createuser(Userlogin create) { 
        
        
        em.persist(create);
        
        return create;  
    }
    
    
     public Topico createTopico(Topico create) { 
        
        
        em.persist(create);
        
        return create;  
    }
    public List<Topico> gettops()
    {
       return em.createNamedQuery("Topico.findAll").getResultList();
    }
     
    public List<Noticia> getnoticias()
    {
       return em.createNamedQuery("Noticia.findAll").getResultList();
    }
    
    public List<Noticia> getnoticiasdeautor(Userlogin i)
    {
        Query query = em.createQuery( "Select n FROM Noticia n WHERE n.iduser.id = ?1" );
        query.setParameter(1,i.getId() );
      // vai buscar as noticias publicadas pelo autor i ! já bomba
       return query.getResultList();
    }
     
    
    public List<Userlogin> getUsernameByName(String nome) {
        
        return em.createNamedQuery("Username.findByNome").setParameter("username", nome).getResultList();
    }
    
    public List<Userlogin> listUsername(){
        
       return em.createNamedQuery("Userlogin.findAll").getResultList();
          
      
    }
 
    
   
}
