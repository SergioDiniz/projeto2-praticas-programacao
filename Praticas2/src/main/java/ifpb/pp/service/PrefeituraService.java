/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.pp.service;

import edu.pp.beans.Funcionario;
import edu.pp.beans.Prefeitura;
import ifpb.pp.serviceIT.PrefeituraServiceIT;
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
@Named(value = "daoPrefeitura")
@SessionScoped
@Default
public class PrefeituraService implements PrefeituraServiceIT, Serializable {

    @PersistenceContext(unitName = "jdbc/praticas2")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    @Override
    public Prefeitura login(String email, String senha) {
        Query query = em.createQuery("SELECT p FROM Prefeitura p WHERE p.email = :email AND p.senha = :senha ");
        query.setParameter("email", email);
        query.setParameter("senha", senha);

        List<Prefeitura> p = query.getResultList();

        if (p.size() > 0) {
            p.get(0).getFuncionarios().size();
            return p.get(0);
        }

        return null;
    }

    @Override
    public Funcionario pesquisarFuncionario(Prefeitura prefeitura, String cpf) {
        Query query = em.createQuery("SELECT f FROM Prefeitura p JOIN p.funcionarios f WHERE p.email = :pEmail AND f.cpf = :fCpf");
        query.setParameter("pEmail", prefeitura.getEmail());
        query.setParameter("fCpf", cpf);

        List<Funcionario> pf = query.getResultList();

        if (pf.size() > 0) {
            pf.get(0).getPrefeituras().size();
            return pf.get(0);
        }

        return null;
    }

    @Override
    public String cadastrarNaPrefeitura(Prefeitura prefeitura, Funcionario funcionario) {
        try {
            prefeitura.getFuncionarios().add(funcionario);
            utx.begin();
            em.merge(prefeitura);
            utx.commit();
            return "Cadastrado com Sucesso";

        } catch (Exception e) {
        }

        return "ERRO!";
    }

    @Override
    public String vincular(Prefeitura prefeitura, Funcionario funcionario) {

        Query query = em.createQuery("SELECT f FROM Prefeitura p JOIN p.funcionarios f WHERE p.email = :pEmail AND f.cpf = :fCpf");
        query.setParameter("pEmail", prefeitura.getEmail());
        query.setParameter("fCpf", funcionario.getCpf());

        List fs = query.getResultList();

        if (fs.size() > 0) {
            return "Funcionario ja esta vinculado na prefeitura!";
        } else {
            cadastrarNaPrefeitura(prefeitura, funcionario);
            return "Vinculado com Sucesso!";
        }

//        return "ERRO!";
    }

    @Override
    public String desvincular(Prefeitura prefeitura, String cpf) {
        System.out.println(prefeitura.getNome() + " " + cpf);

        Funcionario funcionario = pesquisarFuncionario(prefeitura, cpf);

        if (funcionario != null) {

            prefeitura.getFuncionarios().remove(funcionario);
            funcionario.getPrefeituras().remove(prefeitura);

            try {
                utx.begin();
                em.merge(prefeitura);
                em.merge(funcionario);
                utx.commit();
            } catch (Exception e) {
            }

            return "Removido";

        } else {
            return "Funcionario não vinculado com a prefeitura.";
        }

//        return "ERRO!";
    }

    @Override
    public List<Prefeitura> prefeiturasPendentes() {
        Query query = em.createQuery("SELECT p from Prefeitura p WHERE p.ativo = false");
        List<Prefeitura> prefeituras = query.getResultList();

        if (prefeituras.size() > 0) {
            return prefeituras;
        }

        return null;
    }

    @Override
    public Prefeitura pesquisarPorCodigo(int codigo) {
        Query query = em.createQuery("SELECT p from Prefeitura p WHERE p.id = :codigo");
        query.setParameter("codigo", codigo);

        List<Prefeitura> prefeituras = query.getResultList();

        if (prefeituras.size() > 0) {
            prefeituras.get(0).getFuncionarios().size();
            return prefeituras.get(0);
        }

        return null;
    }

    @Override
    public String atualizarSituacao(Prefeitura prefeitura, boolean situacao) {

        try {

            prefeitura.setAtivo(situacao);
            utx.begin();
            em.merge(prefeitura);
            utx.commit();

            return "Atualizado com Sucesso!";

        } catch (Exception e) {
        }

        return "ERRO!";
    }

    @Override
    public String excluir(Prefeitura prefeitura) {
        try {
            prefeitura.setFuncionarios(null);
            prefeitura.setCidade(null);
            utx.begin();
            em.remove(em.merge(prefeitura));
            utx.commit();
            return "Excluido com sucesso!";

        } catch (Exception e) {
        }

        return "ERRO!";
    }

    @Override
    public List<Prefeitura> todasPrefeituras() {
        Query query = em.createQuery("SELECT p FROM Prefeitura p WHERE p.ativo = true");

        List<Prefeitura> prefeituras = query.getResultList();

        if (prefeituras.size() > 0) {
            return prefeituras;
        }

        return null;
    }

    @Override
    public Prefeitura atualizarObjPrefeitura(Prefeitura prefeitura) {
        try {
            utx.begin();
            em.refresh(em.merge(prefeitura));
            utx.commit();
        } catch (Exception e) {
        }
        
        

        return prefeitura;
    }

    @Override
    public List<Funcionario> funcionarios(Prefeitura prefeitura) {
        Query query = em.createQuery("SELECT f FROM Prefeitura p JOIN p.funcionarios f WHERE p.id = :id");
        query.setParameter("id", prefeitura.getId());

        return query.getResultList();

    }

}
