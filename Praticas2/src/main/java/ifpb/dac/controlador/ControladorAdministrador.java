/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.controlador;

import com.br.relatorio.ControladorRelatorioIT;
import edu.ifpb.beans.Administrador;
import edu.ifpb.beans.Prefeitura;
import ifpb.dac.serviceIT.AdministradorServiceIT;
import ifpb.dac.serviceIT.DAOIT;
import ifpb.dac.serviceIT.PrefeituraServiceIT;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Sergio Diniz
 */
@Named(value = "controladorAdministrador")
@SessionScoped
public class ControladorAdministrador implements Serializable{

    Administrador administrador;
    
    @Inject
    private AdministradorServiceIT ad;
    
    @Inject
    private DAOIT dao;
    
    @Inject
    private PrefeituraServiceIT ps;
    
    @Inject
    private ControladorRelatorioIT cr;
    
    public ControladorAdministrador() {
        this.administrador = new Administrador();
    }
    
    
    public String login(){
        this.administrador = ad.login(administrador.getEmail(), administrador.getSenha());
        
        if(administrador != null){
            return "/sis/ambiente/admin/inicio.jsf?faces-redirect=true";
        } else{
            this.administrador = new Administrador();
            return null;
        }
        
    }
    
    
    public String atualizar(){
        dao.atualizar(this.administrador);
        return null;
    }
    
    
    public List<Prefeitura> prefeiturasPendentes(){
        return ps.prefeiturasPendentes();
    }
    
    public List<Prefeitura> prefeiturasAtivas(){
        return ps.todasPrefeituras();
    }    
    
    public String atualizarSituacao(Prefeitura p, String s){
        boolean situacao;
        System.out.println(s);
        
        if("inativo".compareToIgnoreCase(s) == 0){
            situacao = false;
        }else{
            situacao = true;
        }
        
        ps.atualizarSituacao(p, situacao);
        return null;
    }
    
    public String excluirPrefeitura(Prefeitura p){
        ps.excluir(p);
        return null;
    }
    
    
    public String logout() {
        this.administrador = new Administrador();
        return "/index.jsf?faces-redirect=true";
    }    

    
    
    public void downloadRealatorio() throws IOException{
        cr.downloadPrefeitura(prefeiturasAtivas());
    }
    
    
    
    
    
    
    
    
    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
    

    
    
    
    
    
    
    
    
    
    
    
    
    
}
