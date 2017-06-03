/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import BD.Noticia;
import BD.Topico;
import BD.Userlogin;
import bean.Bean;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author TiagoMartins
 */


@Named (value = "Controler")
@RequestScoped

public class Controler {
    
    @EJB
    Bean login;
    
    String username;
    String password;
    
    Userlogin createuser = new Userlogin();
    Topico createtop = new Topico();
    Noticia createnot = new Noticia();
    
    List<Userlogin> usernameList = new ArrayList<>();
    List<Topico> topicosList = new ArrayList<>();
    List<Noticia> noticiasList = new ArrayList<>();
    
    public String verifylog()
    {
        usernameList = login.listUsername();
        for(int i = 0 ;i<usernameList.size();i++)
         {
             
             if(usernameList.get(i).getUsername().equals(createuser.getUsername()))
                 if(usernameList.get(i).getPassword().equals(createuser.getPassword()))
                        return "Registados.xhtml";//se o login existir vai para registados
         }
        return "index.xhtml";// se nao vai para index
    }
    public String createTop()
    {
        topicosList = login.gettops(); // devolver os top q temos ate agora
        if(checktop()==true)
        {
            return "Registados.xhtml";
        }
        login.createuser(createtop);//criar o top na base de dados
        topicosList = login.gettops();//devolver a lista de querys com os tops + o q adicionamos
        return "index.xhtml";
        
    }
    
    public boolean checktop()
    {
        topicosList = login.gettops();
        for(int i = 0 ;i<topicosList.size();i++)
         {
             
             if(topicosList.get(i).getNometopico().equals(createtop.getNometopico()))
                 return true;
         }
        
        return false;
    }
    public String createUserPub() { //criar Publisher
        
          usernameList = login.listUsername();
          createuser.setTipo(1);
          if(checkusercriado() == true)
              return "UserExists.xhtml";//indicar que já existe
                                        // eu usava isso para verificar , quandoo existia ia para pagina diferente
          
          login.createuser(createuser);
          usernameList = login.listUsername();
        return "index.xhtml";
    }
    public boolean checkusercriado()
    {
        usernameList = login.listUsername();
        for(int i = 0 ;i<usernameList.size();i++)
         {
             
             if(usernameList.get(i).getUsername().equals(createuser.getUsername()))
                 return true;
         }
        
        return false;
    }
    public List<Userlogin> getusernameregistados() {
           usernameList = login.listUsername();
        return usernameList;
    }
     public List<Noticia> getnoticiasdeautor() {
            noticiasList = login.getnoticias();
            
            
        return login.getnoticiasdeautor(usernameList.get(0));
    }
    
     public List<Topico> gettopscriados() {
           topicosList = login.gettops();
        return topicosList;
    }
    public String createUserSub() { //CriarSubs
    
        usernameList = login.listUsername();
        createuser.setTipo(2);
       if(checkusercriado() == true)
              return "UserExists.xhtml";
        login.createuser(createuser);
        usernameList = login.listUsername();
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
