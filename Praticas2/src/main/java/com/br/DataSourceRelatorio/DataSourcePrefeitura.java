/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.DataSourceRelatorio;

import edu.pp.beans.Prefeitura;
import java.util.Iterator;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Sergio Diniz
 */
public class DataSourcePrefeitura implements JRDataSource {
    
    private Iterator<Prefeitura> prefeituras;
    private Prefeitura cursor;

    public DataSourcePrefeitura() {
    }

    public DataSourcePrefeitura(List<Prefeitura> prefeituras) {
        this.prefeituras = prefeituras.iterator();
    }

    @Override
    public boolean next() throws JRException {
        boolean retorno = prefeituras.hasNext();
        
        if(retorno){
            cursor = prefeituras.next();
        }
        
        return retorno;
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        
        Prefeitura p = cursor;
        
        if("nome".equals(jrf.getName())){
            return p.getNome();
        } else if ("email".equals(jrf.getName())){
            return p.getEmail();
        }
        
        return null;
    }
    
    
    
}
