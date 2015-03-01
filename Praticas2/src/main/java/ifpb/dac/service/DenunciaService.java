/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.service;

import edu.ifpb.beans.Cidade;
import edu.ifpb.beans.Denuncia;
import edu.ifpb.beans.EstadoDeAcompanhamento;
import ifpb.dac.serviceIT.DenunciaServiceIT;
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
@Named(value = "daoDenuncia")
@SessionScoped
@Default
public class DenunciaService implements DenunciaServiceIT, Serializable{

    @PersistenceContext (unitName = "jdbc/praticas2")
    private EntityManager em; 
    @Resource
    private javax.transaction.UserTransaction utx;
    
    @Override
    public List<Denuncia> pesquisarPorCidade(String cidade, String estado){
        Query query =  em.createQuery("SELECT d FROM Denuncia d WHERE d.cidade.CidadePK.nomeCidade = :cidade AND d.cidade.CidadePK.siglaEstado = :estado");
              query.setParameter("cidade", cidade);
              query.setParameter("estado", estado);
        
        List d = query.getResultList();
        
        if(d.size() > 0){
           return d;
        }         
        
        return null;
    }
    
    
    @Override
    public Denuncia pesquisarPorCodigo(int codigo, Cidade cidade){
        
        Query query = em.createQuery("SELECT d FROM Denuncia d WHERE d.id = :codigo AND d.cidade = :cidade");
              query.setParameter("codigo", codigo);
              query.setParameter("cidade", cidade);
        
        List<Denuncia> denuncias = query.getResultList();
              
        if (denuncias.size() > 0){
            return denuncias.get(0);
        }
        
        return null;
        
    }
    
    
    @Override
    public String atualizarAcompanhamento(int codigo, EstadoDeAcompanhamento acompanhamento, Cidade cidade){
        
        try {
            Denuncia d = pesquisarPorCodigo(codigo, cidade);

            d.setEstadoDeAcompanhamento(acompanhamento);
            
            utx.begin();
            em.merge(d);   
            utx.commit();
            
            return "Atualização realizada com sucesso.";
            
        } catch (Exception e) {
        }
        
        
        return "ERRO!";
        
    }    
    
}
