/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidade;

import edu.pp.beans.Prefeitura;
import ifpb.pp.serviceIT.PrefeituraServiceIT;
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
public class PrefeituraTeste {
    
    public PrefeituraTeste() {
    }
    
    @Mock
    private PrefeituraServiceIT ps;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    
    @Test
    public void testLoginPrefeitura(){
        Prefeitura prefeitura = new Prefeitura("Prefeitura de Santa Helena", "Santa Helena", "PB");
        prefeitura.setEmail("sth@gmail.com");
        prefeitura.setSenha("123");
        Mockito.when(ps.login("sth@gmail.com", "123")).thenReturn(prefeitura);
        
        prefeitura.setSenha("1234");
        assertFalse(ps.login("sth@gmail.com", "123") == null);
        
    }
    
    
    @Test
    public void testPesquisarPorCodigo(){
        Prefeitura prefeitura = new Prefeitura("Prefeitura de Santa Helena", "Santa Helena", "PB");
        prefeitura.setEmail("sth@gmail.com");
        prefeitura.setSenha("123");
        prefeitura.setId(1);
        
        Mockito.when(ps.pesquisarPorCodigo(1)).thenReturn(prefeitura);
        
        assertTrue(ps.pesquisarPorCodigo(2) == null);
        
    }
}
