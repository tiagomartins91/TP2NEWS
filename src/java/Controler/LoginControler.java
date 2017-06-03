/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import BD.Userlogin;
import bean.LoginBean;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author TiagoMartins
 */


@Named (value = "loginControler")
@RequestScoped

public class LoginControler {
    
    @EJB
    LoginBean login;
    
    String username;
    String password;
    
    Userlogin createuser = new Userlogin();
    
    
    
    public String createUserPub() {
        
            
            createuser.setUsername(username);
            createuser.setPassword(password);
            createuser.setTipo(1);
            login.createuser(createuser);
            
        
        return "index.xhtml";
    }
    
    public String createUserSub() {
    
        createuser.setUsername(username);
        createuser.setPassword(password);
        createuser.setTipo(2);
        login.createuser(createuser);
        
        return "index.xhtml";
    }
    
  
    

    public List<Userlogin> getLoginUsers(){
        
        List userlogin = login.listUsername();
        return userlogin;
        
    }
    

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Userlogin getCreateuser() {
        return createuser;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreateuser(Userlogin createuser) {
        this.createuser = createuser;
    }
    
    
}
