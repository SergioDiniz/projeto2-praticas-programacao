package com.br.relatorio;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sergio Diniz
 */
@Named
@ApplicationScoped
public class ConfiguraRelatorio {
    
    @Produces
    public FacesContext criarContext(){
        return FacesContext.getCurrentInstance();
    }
    
    @Produces
    public ExternalContext criarEContext(){
        return criarContext().getExternalContext();
    }
    
        
    
}
