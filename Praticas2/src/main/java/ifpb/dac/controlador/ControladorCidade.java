/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.controlador;

import edu.ifpb.beans.Cidade;
import edu.ifpb.beans.CidadePK;
import ifpb.dac.serviceIT.DAOIT;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Sergio Diniz
 */
@Named(value = "controladorCidade")
@SessionScoped
public class ControladorCidade implements Serializable{
    
    CidadePK cidadePK;
    
    Cidade cidade;
    
    @Inject
    private DAOIT dao;

    public ControladorCidade() {
        cidadePK = new CidadePK();
        cidade = new Cidade();
    }

    
    public String add(){
        cidade.setCidadePK(cidadePK);
        dao.salvar(cidade);
        cidade = new Cidade();
        cidadePK = new CidadePK();
        return "/index.jsf?faces-redirect=true";
    }
    
    
    
    
    
    
    
    
    
    
    public CidadePK getCidadePK() {
        return cidadePK;
    }

    public void setCidadePK(CidadePK cidadePK) {
        this.cidadePK = cidadePK;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    
    
}
