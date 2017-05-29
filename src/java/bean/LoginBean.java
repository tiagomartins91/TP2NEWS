/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import BD.Userlogin;
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
}