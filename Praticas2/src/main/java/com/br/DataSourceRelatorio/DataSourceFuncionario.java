/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.DataSourceRelatorio;

import edu.ifpb.beans.Funcionario;
import java.util.Iterator;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Sergio Diniz
 */
public class DataSourceFuncionario implements JRDataSource{
    
    private Iterator<Funcionario> funcionarios;
    private Funcionario cursor;

    public DataSourceFuncionario() {
    }

    public DataSourceFuncionario(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios.iterator();
    }
    
    
    

    @Override
    public boolean next() throws JRException {
         boolean retorno = funcionarios.hasNext();

        if (retorno) {
            cursor = funcionarios.next();
        }
        return retorno;
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Funcionario f = cursor;
        
        
        if("nome".equals(jrf.getName())){
            return f.getNome();
        } else if("cpf".equals(jrf.getName())){
            return f.getCpf();
        } else if("email".equals(jrf.getName())){
            return f.getEmail();
        }
     
        return "";
        
    }
    
}
