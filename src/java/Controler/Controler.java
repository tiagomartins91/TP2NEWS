/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import BD.Noticia;
import BD.Subs;
import BD.Topico;
import BD.Userlogin;
import bean.Bean;
import java.util.ArrayList;
import java.util.Date;
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
    String nometopico;
    int idtopico;


    Userlogin createuser = new Userlogin();
    Topico criartopico = new Topico();
    Topico topico2 = new Topico();

    public Topico getTopico2() {
        return topico2;
    }

    public void setTopico2(Topico topico2) {
        this.topico2 = topico2;
    }
    Noticia createnot = new Noticia();
    Noticia vernot = new Noticia();
    Subs subscritor = new Subs();

    List<Userlogin> usernameList = new ArrayList<>();
    List<Topico> topicosList = new ArrayList<>();
    List<Noticia> noticiasList = new ArrayList<>();
    List <Noticia> noticiastopicosList = new ArrayList<>();
    List<Subs> Listsubs = new ArrayList<>();
    List<Noticia> n = new ArrayList<>();

    
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
    
    public String createUserSub() { //CriarSubs
    
        usernameList = login.listUsername();
        createuser.setTipo(2);
       if(checkusercriado() == true)
              return "UserExists.xhtml";
        login.createuser(createuser);
        usernameList = login.listUsername();
        return "index.xhtml";
    }
    
    public boolean checkusercriado(){ //Verificação 
        usernameList = login.listUsername();
  
        for(int i = 0 ;i<usernameList.size();i++){
             
             if(usernameList.get(i).getUsername().equals(createuser.getUsername())) 
                 return true;
        }
        
        return false;
    }

    public String verifylog(){ //verificação de login de Pub
        
        usernameList = login.listUsername();
        
        for(int i = 0 ;i<usernameList.size();i++){
             
             if(usernameList.get(i).getUsername().equals(createuser.getUsername()))
                 if(usernameList.get(i).getPassword().equals(createuser.getPassword()))
                     if(usernameList.get(i).getTipo()==1) 
                     {
                        
                         //createuser = login.getUsernameByName(login.outcome()).get(0);
                          return "MenuPub.xhtml";
                             
                     }
        }
        for(int i = 0 ;i<usernameList.size();i++){
             
             if(usernameList.get(i).getUsername().equals(createuser.getUsername()))
                 if(usernameList.get(i).getPassword().equals(createuser.getPassword()))
                     if(usernameList.get(i).getTipo()==2) 
                     {
                        
                         //createuser = login.getUsernameByName(login.outcome()).get(0);
                          return "MenuSub.xhtml";
                             
                     }
        }
        
        
        
        return "UserNoExists.xhtml";// vai para user no exists
    }
        
    public String createTop() //criar topico
    {
        topicosList = login.gettops(); // devolver os top q temos ate agora
        if(checktop()==true){
            createuser = login.getUsernameByName(login.outcome()).get(0);
            return "TopicoJaExiste.xhtml";
        }
        
        login.createTopico(criartopico);//criar o top na base de dados
        topicosList = login.gettops();//devolver a lista de querys com os tops + o q adicionamos
        
        createuser = login.getUsernameByName(login.outcome()).get(0);
        
        return "MenuPub.xhtml";
        
    }

    public String criarnoticia() //Adicionar Noticia
    {
        
        createuser = login.getUsernameByName(login.outcome()).get(0);
       
        Date data = new Date();
        
        createnot.setIduser(createuser);
        createnot.setDatan(data);
        createnot.setIdtop(criartopico);

        login.createNoticia(createnot);
        noticiasList = login.getnoticias();
        
        return "MenuPub.xhtml"; 
    }

    public List<Noticia> getN() {
        return n;
    }

    public void setN(List<Noticia> n) {
        this.n = n;
    }
    
    
    //IR PARA
    
    public String irparaNoticia(){
        
            createuser = login.getUsernameByName(login.outcome()).get(0);
        
            return "AdicionarNoticia.xhtml";
    }
    
    public String irparaTopico(){
        
            createuser = login.getUsernameByName(login.outcome()).get(0);
        
            return "AdicionarTopico.xhtml";
    }
    
    public String irparaConsultarTopicos(){
        
            createuser = login.getUsernameByName(login.outcome()).get(0);
        
            return "ConsultarTopicos.xhtml";
    }
    
    public String irparaConsultarTodasNoticias(){
        
            createuser = login.getUsernameByName(login.outcome()).get(0);
        
            return "ConsultarTodasNoticias.xhtml";
    }
    
    public String irparaMenuPub(){
        
            createuser = login.getUsernameByName(login.outcome()).get(0);
        
            return "MenuPub.xhtml";
    }
    
    public String irparaMenuSub(){
        
            createuser = login.getUsernameByName(login.outcome()).get(0);
            
            return "MenuSub.xhtml";
    }
    public String irparaMenuSub2(){
        
            createuser = login.getUsernameByName(login.outcome()).get(0);
            
            return "MenuSub.xhtml";
    }
    
    public String atualizar()
    {
        int lastid = 0;
        
        
        createuser = login.getUsernameByName(login.outcome()).get(0);
        
        Listsubs = login.getsubsbyId(createuser); //lista das subscrições do createuser
        for(int i = 0 ;i<Listsubs.size();i++)
        {
                topico2 = login.gettopbyidusersub(Listsubs.get(i));
              
                if(login.getnoticiasTopico(topico2).isEmpty())
                     return "MenuSub.xhtml";
                else
                {
                lastid = login.lastnews(Listsubs.get(i).getUsersubs(),topico2);
                Listsubs.get(i).setLastnews(lastid);
                login.updatesub(Listsubs.get(i),topico2);
                lastid=0;
                }
        }
        
        return "MenuSub.xhtml";
      
    }

    
    public String irparaUltimaNoticiaSub(){
        
            createuser = login.getUsernameByName(login.outcome()).get(0);
            
            return "UltimaNoticiaTopicoSub.xhtml";
    }
    
    public String irparaNoticiasPorLer()
    {
       createuser = login.getUsernameByName(login.outcome()).get(0);
       
               
       return "Registados.xhtml";
    }
    
    public List<Noticia> NoticiasPorler(){
       
        n = login.getnoticiasporler(createuser);
        return n ;

    }
    
    
    
    public String irparaEntreDatasSub(){
        
            createuser = login.getUsernameByName(login.outcome()).get(0);
        
            return "NoticiaEntreDatasSub.xhtml";
    }
    
    public String irparaSubscreverTopico(){
        
            createuser = login.getUsernameByName(login.outcome()).get(0);
        
            return "Subscrevertopico.xhtml";
    }
    
    
    public boolean checktop()
    {
        topicosList = login.gettops();
        for(int i = 0 ;i<topicosList.size();i++)
         {
             
             if(topicosList.get(i).getNometopico().equals(criartopico.getNometopico()))
                 return true;
         }
        
        return false;
    }
    
     public boolean checkSub(Userlogin us, Topico t){
         
        Listsubs = login.getsubs();
        
        for(int i = 0 ;i<Listsubs.size();i++){
             
             if(Listsubs.get(i).getUsersubs().getId()==us.getId() && Listsubs.get(i).getTopsubs().getIdtopico()==t.getIdtopico())
                 return true;
            }
        
        return false;
    }
    
    
    
    public String checkUltimaNoticiaTopico(){

         noticiastopicosList = login.getnoticiasTopico(criartopico);
         
         if(noticiastopicosList.isEmpty()){
             
             return "ErroCheckTopico.xhtml";
         }
         
         
         vernot = (Noticia) noticiastopicosList.get(noticiastopicosList.size()-1);
       
         
                 
        return "UltimaNoticiaTopico.xhtml";
    
    }

    public Subs getSubscritor() {
        return subscritor;
    }

    public void setSubscritor(Subs subscritor) {
        this.subscritor = subscritor;
    }
    
    public String subscrevertopico(){
                
                if(topico2.getIdtopico()==null){
                    
                    createuser = login.getUsernameByName(login.outcome()).get(0);
                    return "ErroSubscreverTop.xhtml";
                }
        
        
                createuser = login.getUsernameByName(login.outcome()).get(0);
                criartopico = login.gettopbyid(topico2).get(0);
                usernameList = login.listUsername();
                topicosList = login.gettops();
                
                
                
                if(checkSub(createuser,criartopico)==true){
                    
                    createuser = login.getUsernameByName(login.outcome()).get(0);
                    return "TopSubRepetido.xhtml";
                }
                
               
                
                subscritor.setUsersubs(createuser);
                subscritor.setTopsubs(criartopico);
                subscritor.setLastnews(0);
                login.subscrevertop(subscritor);
                Listsubs = login.getsubs();
                
        return "TopicoSubscritoSucesso.xhtml";
         
    }
    
    public List<Subs> getallsubs()
    {
        Listsubs = login.getsubs();
        return Listsubs;
    }
    public String checkUltimaNoticiaTopicoSub(){
        
         createuser = login.getUsernameByName(login.outcome()).get(0);
         
         noticiastopicosList = login.getnoticiasTopico(criartopico);
       
         if(noticiastopicosList.isEmpty()){
             
             createuser = login.getUsernameByName(login.outcome()).get(0);
             
             return "ErroCheckTopicoSub.xhtml";
         }
         
         vernot = (Noticia) noticiastopicosList.get(noticiastopicosList.size()-1);
                 
        return "UltimaNoticiaTopicoSub.xhtml";
    
    }
    
   
    
    public List<Noticia> mostrarNoticiaBydate()
    {
        
        n = login.getnoticiasbydate(vernot,createnot,criartopico);
        
        return n;
    }
    
    public String mostrarNoticiaBydateSub(){
       
        createuser = login.getUsernameByName(login.outcome()).get(0);
        
        n = login.getnoticiasbydate(vernot,createnot,criartopico);
        
        return "NoticiaEntreDatasSub.xhtml";
    }
    
   
     
    
    
    public List<Userlogin> getusernameregistados() {
           usernameList = login.listUsername();
        return usernameList;
    }
    
    public List<Noticia> getnoticiasdeautor() {
            noticiasList = login.getnoticias();
            
            
        return login.getnoticiasdeautor(usernameList.get(0));
    }

    public List<Noticia> getNoticiasList() {
        
        noticiasList = login.getnoticias();
        
        return noticiasList;
    }

    public void setNoticiasList(List<Noticia> noticiasList) {
        this.noticiasList = noticiasList;
    }
    
    
    

    public List<Topico> getTopicosList() {
        topicosList = login.gettops();
        return topicosList;
    }

    public void setTopicosList(List<Topico> topicosList) {
        this.topicosList = topicosList;
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

    public Topico getCriartopico() {
        return criartopico;
    }

    public void setCriartopico(Topico criartopico) {
        this.criartopico = criartopico;
    }
    
    public int getIdtopico() {
        return idtopico;
    }

    public void setIdtopico(int idtopico) {
        this.idtopico = idtopico;
    }
    
    
    public Noticia getCreatenot() {
        return createnot;
    }

    public void setCreatenot(Noticia createnot) {
        this.createnot = createnot;
    }

    public Noticia getVernot() {
        return vernot;
    }

    public void setVernot(Noticia vernot) {
        this.vernot = vernot;
    }
    
    
    
    
}
