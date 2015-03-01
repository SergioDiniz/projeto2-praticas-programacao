/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.service;

import edu.ifpb.beans.Cidade;
import edu.ifpb.beans.CidadePK;
import ifpb.dac.serviceIT.CidadeServiceIT;
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
@Named(value = "daoCidade")
@SessionScoped
@Default
public class CidadeService implements CidadeServiceIT, Serializable {

    @PersistenceContext(unitName = "jdbc/praticas2")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    @Override
    public Cidade pesquisarCidade(String nome, String estado) {
        try {
            CidadePK cidadePK = new CidadePK(nome, estado);
            Cidade cidade = new Cidade();
            utx.begin();
            cidade = em.find(Cidade.class, cidadePK);
            utx.commit();
            cidade.getDenuncias().size();
            return cidade;
        } catch (Exception e) {
        }

        return null;
    }

    @Override
    public Cidade atualizarObjCidade(Cidade cidade) {

        try {
            utx.begin();
            em.refresh(em.merge(cidade));
            utx.commit();
        } catch (Exception e) {
        }

        return cidade;
    }

}
