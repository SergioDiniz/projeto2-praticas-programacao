/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidade;

import edu.pp.beans.Funcionario;
import ifpb.pp.serviceIT.FuncionarioServiceIT;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Sergio Diniz
 */
public class FuncionariosTeste {

    public FuncionariosTeste() {
    }
    
    @Mock
    private FuncionarioServiceIT fs;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testFuncionarioLogin(){
        Funcionario funcionario = new Funcionario("Kiko", "5555", "kiko@chaves.com", "123");
        Mockito.when(fs.login("kiko@chaves.com", "123", "Santa Helena", "PB")).thenReturn(funcionario);
    }
    
    @Test
    public void testBuscarPorCPF(){
        Funcionario funcionario = new Funcionario("Kiko", "5555", "kiko@chaves.com", "123");
        Mockito.when(fs.buscarPorCPF("5555")).thenReturn(funcionario);
        
        assertTrue(fs.buscarPorCPF("1111") == null);
    }
    
}
