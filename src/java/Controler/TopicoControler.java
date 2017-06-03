/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import BD.Topico;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author TiagoMartins
 */

@Named (value = "TopicoControler")
@RequestScoped

public class TopicoControler {
   
    @EJB
    TopicoControler topico;
    
    String nometopico;
    Date date = new Date();
    
    Topico tp = new Topico();
    
    
    
    
    
}
