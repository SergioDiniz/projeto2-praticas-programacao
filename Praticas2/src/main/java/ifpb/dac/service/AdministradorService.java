/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.service;

import edu.ifpb.beans.Administrador;
import ifpb.dac.serviceIT.AdministradorServiceIT;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Sergio Diniz
 */
@Named(value = "daoAdmin")
@SessionScoped
@Default
public class AdministradorService implements AdministradorServiceIT, Serializable{

    @PersistenceContext (unitName = "jdbc/praticas2")
    private EntityManager em;        
    @Resource
    private javax.transaction.UserTransaction utx;
    
    @Override
    public Administrador login(String email, String senha) {
        Query query =  em.createQuery("SELECT a FROM Administrador a WHERE a.email = :email AND a.senha = :senha ");
              query.setParameter("email", email);
              query.setParameter("senha", senha);
        
        List<Administrador> a = query.getResultList();
        
        if(a.size() > 0){
           return a.get(0);
        } 
        
        return null;        
    }
    
}
