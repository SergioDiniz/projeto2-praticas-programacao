/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.pp.controlador;

import edu.pp.beans.Cidade;
import edu.pp.beans.CidadePK;
import edu.pp.beans.Denuncia;
import edu.pp.beans.EstadoDeAcompanhamento;
import edu.pp.beans.Funcionario;
import ifpb.pp.serviceIT.CidadeServiceIT;
import ifpb.pp.serviceIT.DAOIT;
import ifpb.pp.serviceIT.DenunciaServiceIT;
import ifpb.pp.serviceIT.FuncionarioServiceIT;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Sergio Diniz
 */
@Named(value = "controladorFuncionario")
@SessionScoped
public class ControladorFuncionario implements Serializable{

    Funcionario funcionario;
    Cidade cidade;
    CidadePK cidadePK;
    Denuncia denuncia;
    EstadoDeAcompanhamento estadoDeAcompanhamento;
    
    
    @Inject
    private DAOIT dao;
    
    @Inject
    private FuncionarioServiceIT fs;
    
    @Inject
    private CidadeServiceIT cs;
    
    @Inject
    private DenunciaServiceIT ds;
    
    public ControladorFuncionario() {
        funcionario = new Funcionario();
        cidade = new Cidade();
        cidadePK = new CidadePK();
        denuncia = new Denuncia();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    
    public String add(){
        dao.salvar(funcionario);
        funcionario = new Funcionario();
        return "/index.jsf?faces-redirect=true";
    }
    
    public String login(){
        this.funcionario = fs.login(funcionario.getEmail(), funcionario.getSenha(), 
                cidadePK.getNomeCidade(), cidadePK.getSiglaEstado());
        
        
        if(funcionario != null){
            
            this.cidade = cs.pesquisarCidade(cidadePK.getNomeCidade(), cidadePK.getSiglaEstado());
            
            return "/sis/ambiente/funcionario/inicio.jsf?faces-redirect=true";
        } else {
            cidadePK = new CidadePK();
            return null;
        }
        
    }
    
    public String atualizar(){
        dao.atualizar(funcionario);
        
        return null;
    }
    
    
    public String atualizarDenuncia(Denuncia d){
        this.denuncia = d;
        return "atualizar.jsf";
    }
    
    public String atualizarSituacaoDaDenuncia(){
        denuncia.setEstadoDeAcompanhamento(estadoDeAcompanhamento);
        
        ds.atualizarAcompanhamento(denuncia.getId(), estadoDeAcompanhamento, cidade);
        
        return null;
    }

    public String logout() {
        this.funcionario = new Funcionario();
        this.cidadePK = new CidadePK();
        return "/index.jsf?faces-redirect=true";
    }    

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public CidadePK getCidadePK() {
        return cidadePK;
    }

    public void setCidadePK(CidadePK cidadePK) {
        this.cidadePK = cidadePK;
    }

    public Denuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(Denuncia denuncia) {
        this.denuncia = denuncia;
    }

    public EstadoDeAcompanhamento getEstadoDeAcompanhamento() {
        return estadoDeAcompanhamento;
    }

    public void setEstadoDeAcompanhamento(EstadoDeAcompanhamento estadoDeAcompanhamento) {
        this.estadoDeAcompanhamento = estadoDeAcompanhamento;
    }
    
    
    
    
    
    
    
    
    
    
}
