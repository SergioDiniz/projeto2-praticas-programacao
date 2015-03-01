/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.service;

import ifpb.dac.serviceIT.DAOIT;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sergio Diniz
 */
@Named(value = "dao")
@SessionScoped
@Default
public class DAO implements DAOIT, Serializable{

    @PersistenceContext (unitName = "jdbc/praticas2")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    @Override
    public void salvar(Object object) {
        
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
        }
    }

    @Override
    public void atualizar(Object object) {
        try {
            utx.begin();
            em.merge(object);
            utx.commit();
        } catch (Exception e) {
        }
    }

    @Override
    public void excluir(Object object) {
        try {
            utx.begin();
            em.remove(object);
            utx.commit();
        } catch (Exception e) {
        }
    }
    
}
