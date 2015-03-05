/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidade;

import edu.pp.beans.Cidade;
import edu.pp.beans.CidadePK;
import edu.pp.beans.Denuncia;
import edu.pp.beans.EnderecoDenuncia;
import ifpb.pp.serviceIT.DenunciaServiceIT;
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
public class DenunciaTeste {

    public DenunciaTeste() {
    }

    @Mock
    private DenunciaServiceIT ds;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testPesquisarDenuncia(){
        Cidade cidade = new Cidade(new CidadePK("Santa Henela", "PB"));
        Denuncia denuncia = new Denuncia("descricao", new EnderecoDenuncia("Centro", 71, "Pedro Muniz"), cidade);
        denuncia.setId(1);
        
        Mockito.when(ds.pesquisarPorCodigo(1, cidade)).thenReturn(denuncia);
        
        assertTrue(ds.pesquisarPorCodigo(2, cidade) == null);
        
    }
}
