/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import BD.Topico;
import BD.Userlogin;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TiagoMartins
 */
@Stateless
public class LoginBean {

    @PersistenceContext 
    EntityManager em;
    
    public Userlogin createuser(Userlogin create) { 
        
        
        em.persist(create);
        
        return create;  
    }
    
    public boolean procurarUsername(String username){
        
        if(em.createNamedQuery("Userlogin.findByUserName").setParameter("username", username).getResultList()!=null)
            return false;
        
        return true;
          
    }
    
    
    public Topico createTopico(Topico create){
        
        
        em.persist(create);

        return create;
        
    }
           
    
    
    
    
    
}