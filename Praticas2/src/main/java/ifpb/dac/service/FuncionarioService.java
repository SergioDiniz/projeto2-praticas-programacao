/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.service;

import edu.ifpb.beans.Funcionario;
import ifpb.dac.serviceIT.FuncionarioServiceIT;
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
@Named(value = "daoFuncionario")
@SessionScoped
@Default
public class FuncionarioService implements FuncionarioServiceIT, Serializable {

    @PersistenceContext(unitName = "jdbc/praticas2")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    @Override
    public Funcionario login(String email, String senha, String cidade, String estado) {

        Query query = em.createQuery("SELECT f FROM Funcionario f JOIN f.prefeituras fp WHERE f.email = :email "
                + "AND f.senha = :senha AND fp.cidade.CidadePK.nomeCidade = :cidade "
                + "AND fp.cidade.CidadePK.siglaEstado = :estado");

        query.setParameter("email", email);
        query.setParameter("senha", senha);
        query.setParameter("cidade", cidade);
        query.setParameter("estado", estado);

        List<Funcionario> f = query.getResultList();

        if (f.size() > 0) {
            f.get(0).getPrefeituras().size();
            return f.get(0);
        }

        return null;
    }

    @Override
    public Funcionario buscarPorCPF(String cpf) {

        Query query = em.createQuery("SELECT f FROM Funcionario f WHERE f.cpf = :cpf");
        query.setParameter("cpf", cpf);

        List<Funcionario> f = query.getResultList();

        if (f.size() > 0) {
            f.get(0).getPrefeituras().size();
            return f.get(0);
        }

        return null;

    }

    @Override
    public String excluir(Funcionario funcionario) {

        try {
            funcionario.setPrefeituras(null);
            utx.begin();
            em.remove(em.merge(funcionario));
            utx.commit();

            return "Excluido com Sucesso.";
        } catch (Exception e) {
        }

        return "ERRO!";

    }
}
