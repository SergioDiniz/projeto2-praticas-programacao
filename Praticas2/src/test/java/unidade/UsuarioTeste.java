/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidade;

import edu.pp.beans.Usuario;
import ifpb.pp.serviceIT.UsuarioServiceIT;
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
public class UsuarioTeste {
    
    public UsuarioTeste() {
    }
    
    @Mock
    private UsuarioServiceIT us;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoginUsuario() {
        edu.pp.beans.Usuario usuario = new edu.pp.beans.Usuario(null, "SD", "sergio@gmail.com", "123");
        Mockito.when(us.login("sergio@gmail.com", "123")).thenReturn(usuario);

        usuario.setSenha("1234");
        assertFalse(us.login("sergio@gmail.com", "123") == null);
    }
    
    
    @Test
    public void testExcluirUsuario(){
        Usuario usuario = new Usuario();
        Mockito.when(us.excluir(usuario)).thenReturn("ok");
    }
    
    
}
