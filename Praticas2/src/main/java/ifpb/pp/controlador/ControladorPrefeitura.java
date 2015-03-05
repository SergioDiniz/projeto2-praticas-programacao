/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.pp.controlador;

import com.br.relatorio.ControladorRelatorioIT;
import edu.pp.beans.Cidade;
import edu.pp.beans.CidadePK;
import edu.pp.beans.Funcionario;
import edu.pp.beans.Prefeitura;
import ifpb.pp.serviceIT.CidadeServiceIT;
import ifpb.pp.serviceIT.DAOIT;
import ifpb.pp.serviceIT.FuncionarioServiceIT;
import ifpb.pp.serviceIT.PrefeituraServiceIT;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Sergio Diniz
 */
@Named(value = "controladorPrefeitura")
@SessionScoped
public class ControladorPrefeitura implements Serializable{
    
    Cidade cidade;
    CidadePK  cidadePK;
    Prefeitura prefeitura;
    Funcionario funcionario;
    boolean funcionarioCadastrado;
    boolean funcionarioVinculado;
    boolean funcionarioNovo;
    String CPFPesquisaF;

    
    
    @Inject
    private DAOIT dao;
    
    @Inject
    private CidadeServiceIT cs;
    
    @Inject
    private PrefeituraServiceIT ps;
    
    @Inject
    private FuncionarioServiceIT fs;
    
    @Inject
    private ControladorRelatorioIT cr;

    
    public ControladorPrefeitura() {
        cidade = new Cidade();
        cidadePK = new CidadePK();
        prefeitura = new Prefeitura();
        funcionarioCadastrado = false;
        funcionarioNovo = false;
        funcionarioVinculado = false;
        CPFPesquisaF = "";
        funcionario = new Funcionario();
    }
    
    public String add(){


        try {
            cidade.setCidadePK(cidadePK);
            dao.salvar(cidade);
            cidade = cs.pesquisarCidade(cidadePK.getNomeCidade(), cidadePK.getSiglaEstado());
        } catch (Exception e) {
        }
        
        
        prefeitura.setCidade(cidade);
        
        dao.atualizar(prefeitura);
        
        cidade = new Cidade();
        cidadePK = new CidadePK();
        prefeitura = new Prefeitura();        
        
        return "/index.jsf?faces-redirect=true";
    }

    
    public String login(){
        this.prefeitura = ps.login(prefeitura.getEmail(), prefeitura.getSenha());
        
        if(this.prefeitura != null){
            
            if(prefeitura.isAtivo() == true){
                return "/sis/ambiente/prefeitura/inicio.jsf?faces-redirect=true";
            }
            
            return null;
        } else {
            this.prefeitura = new Prefeitura();
            
            return null;
        }
        
    }
    
    public String atualizar(){
        dao.atualizar(prefeitura);
        return null;
    }
    
    public String excluirConta(){
        
        ps.excluir(prefeitura);
        
        logout();
        
        return null;
    }
    
    
    public String logout() {
        this.cidade = new Cidade();
        this.cidadePK = new CidadePK();
        this.prefeitura = new Prefeitura();
        this.funcionarioCadastrado = false;
        this.funcionarioNovo = false;
        this.funcionarioVinculado = false;
        this.CPFPesquisaF = "";
        this.funcionario = new Funcionario();
        return "/index.jsf?faces-redirect=true";
    }    
    
    
    
    public String desvincularFuncionario(Funcionario f){
        ps.desvincular(this.prefeitura, f.getCpf());
        this.prefeitura.getFuncionarios().remove(f);
        return null;
    }
    
    
    public List<Funcionario> funcionarios(){
        return ps.funcionarios(this.prefeitura);
    }
    

    public void pesquisarCPFFuncionario(){
        
        Funcionario f = fs.buscarPorCPF(CPFPesquisaF);
        
        if(f != null){
            funcionarioNovo = false;
            funcionarioCadastrado = true;
        } else{
            funcionarioNovo = true;
            funcionarioCadastrado = false;
        }
        
        
        
    }
    
    public List<Funcionario> funcionarioPesquisaCPF(){
        List<Funcionario> fus = new ArrayList<>();
        fus.add(fs.buscarPorCPF(CPFPesquisaF));
        return fus;
    }
    
    
    public String vincularFuncionario(){
        Funcionario f = fs.buscarPorCPF(CPFPesquisaF);
        ps.vincular(prefeitura, f);
//        prefeitura.getFuncionarios().add(f);
        return "funcionarios.jsf";
    }
    
    
    
    public String cadastrarNovoFuncionario(){
        funcionario.setCpf(CPFPesquisaF);
        dao.salvar(funcionario);
        funcionario = fs.buscarPorCPF(CPFPesquisaF);
        ps.cadastrarNaPrefeitura(prefeitura, funcionario);
        
//        prefeitura.getFuncionarios().add(funcionario);
        
        funcionario = new Funcionario();
        CPFPesquisaF = "";
        return "funcionarios.jsf";
    }
    
    
    public void downloadRealatorio() throws IOException{
        cr.downloadFuncionario(prefeitura.getFuncionarios());
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

    public Prefeitura getPrefeitura() {
        return prefeitura;
    }

    public void setPrefeitura(Prefeitura prefeitura) {
        this.prefeitura = prefeitura;
    }

    public boolean isFuncionarioCadastrado() {
        return funcionarioCadastrado;
    }

    public void setFuncionarioCadastrado(boolean funcionarioCadastrado) {
        this.funcionarioCadastrado = funcionarioCadastrado;
    }

    public boolean isFuncionarioVinculado() {
        return funcionarioVinculado;
    }

    public void setFuncionarioVinculado(boolean funcionarioVinculado) {
        this.funcionarioVinculado = funcionarioVinculado;
    }

    public boolean isFuncionarioNovo() {
        return funcionarioNovo;
    }

    public void setFuncionarioNovo(boolean funcionarioNovo) {
        this.funcionarioNovo = funcionarioNovo;
    }

    public String getCPFPesquisaF() {
        return CPFPesquisaF;
    }

    public void setCPFPesquisaF(String CPFPesquisaF) {
        this.CPFPesquisaF = CPFPesquisaF;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    
    
    
    
    
    
    
}
