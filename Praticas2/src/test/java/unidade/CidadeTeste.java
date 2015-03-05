/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidade;

import edu.pp.beans.Cidade;
import edu.pp.beans.CidadePK;
import ifpb.pp.serviceIT.CidadeServiceIT;
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
public class CidadeTeste {

    public CidadeTeste() {
    }

    @Mock
    private CidadeServiceIT cs;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    
    @Test
    public void testPesquisarCidade(){
        Cidade cidade = new Cidade(new CidadePK("Santa Helena", "PB"));
        Mockito.when(cs.pesquisarCidade("Santa Helena", "PB")).thenReturn(cidade);
        
        cidade.getCidadePK().setNomeCidade("Cajazeiras");
        assertFalse(cs.pesquisarCidade("Santa Helena", "PB") == null);
    }
    
}
