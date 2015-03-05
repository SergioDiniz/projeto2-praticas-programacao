/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.pp.service;

import edu.pp.beans.Cidade;
import edu.pp.beans.EnderecoDenuncia;
import edu.pp.beans.CidadePK;
import edu.pp.beans.Usuario;
import edu.pp.beans.Denuncia;
import ifpb.pp.serviceIT.UsuarioServiceIT;
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
@Named(value = "daoUsuario")
@SessionScoped
@Default
public class UsuarioService implements UsuarioServiceIT, Serializable{
    
    @PersistenceContext (unitName = "jdbc/praticas2")
    private EntityManager em;  
    @Resource
    private javax.transaction.UserTransaction utx;

    public UsuarioService() {
    }
    
    
    @Override
    public Usuario login(String email, String senha){
        
        Query query =  em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha ");
              query.setParameter("email", email);
              query.setParameter("senha", senha);
        
        List<Usuario> u = query.getResultList();
        
        if(u.size() > 0){
            u.get(0).getDenuncias().size();
           return u.get(0);
        } 
        
        return null;
        
    }
    
    
    @Override
    public String novaDenuncia(Usuario usuario, String cidade, String estado, String rua, 
                                String numeroS, String bairro, String descricao){
        int numero = Integer.parseInt(numeroS);
        
        
        try {
            
            EnderecoDenuncia ed = new EnderecoDenuncia(bairro, numero, rua);
            CidadePK cpk = new CidadePK(cidade, estado);
            Cidade c = new Cidade(cpk);
            Denuncia d = new Denuncia(descricao, ed, c);
            
            usuario.novaDenuncia(d);
            
            utx.begin();
            em.persist(ed);
            em.persist(d);
            em.merge(usuario);
            utx.commit();
            
            
            
            
            return "ok";
        } catch (Exception e) {
        }
        
        
        return "ERRO";
    }
    
    
    @Override
    public List<Denuncia> minhasDenuncias(String email){
        
        Query query = em.createQuery("SELECT d from Usuario u JOIN u.denuncias d WHERE u.email = :email");
              query.setParameter("email", email);
        
        List<Denuncia> d = query.getResultList();
        
        if(d.size() > 0){
            return d;
        }
        
        return null;
        
    }
    
    public String excluir(Usuario usuario){
        
        try {
            usuario.setDenuncias(null);
            utx.begin();
            em.remove(em.merge(usuario));
            utx.commit();
            return "Removido com sucesso!";
        } catch (Exception e) {
        }

        
        
        return "ERRO!";
    }    
    
    
}
