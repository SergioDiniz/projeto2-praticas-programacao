/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidade;

import edu.pp.beans.Administrador;
import ifpb.pp.serviceIT.AdministradorServiceIT;
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
public class AdministradorTeste {
    
    public AdministradorTeste() {
    }
    
    @Mock
    private AdministradorServiceIT as;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    
    @Test
    public void testLoginAdministrador(){
        
        Administrador administrador = new Administrador("admin", "admin");
        Mockito.when(as.login("admin", "admin")).thenReturn(administrador);
        
        administrador.setSenha("123");
        assertFalse(as.login("admin", "admin") == null);
        
    }
    
    
}
