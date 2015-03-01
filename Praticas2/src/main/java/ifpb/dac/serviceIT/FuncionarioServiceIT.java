/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.serviceIT;

import edu.ifpb.beans.Funcionario;

/**
 *
 * @author Sergio Diniz
 */
public interface FuncionarioServiceIT {
    
    public Funcionario login(String email, String senha, String cidade, String estado);
    
    public Funcionario buscarPorCPF(String cpf);
    
    public String excluir(Funcionario funcionario);
    
}
