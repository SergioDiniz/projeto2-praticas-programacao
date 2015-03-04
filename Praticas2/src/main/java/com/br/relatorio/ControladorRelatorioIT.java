/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.relatorio;

import edu.ifpb.beans.Funcionario;
import edu.ifpb.beans.Prefeitura;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Sergio Diniz
 */
public interface ControladorRelatorioIT {
    public void downloadFuncionario(List<Funcionario> funcionarios) throws IOException;
    
    public void downloadPrefeitura(List<Prefeitura> prefeituras) throws IOException;
}
