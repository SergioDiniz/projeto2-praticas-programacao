/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.serviceIT;

import edu.ifpb.beans.Cidade;
import edu.ifpb.beans.Denuncia;
import edu.ifpb.beans.EstadoDeAcompanhamento;
import java.util.List;

/**
 *
 * @author Sergio Diniz
 */
public interface DenunciaServiceIT {
    
    public List<Denuncia> pesquisarPorCidade(String cidade, String estado);
    
    public Denuncia pesquisarPorCodigo(int codigo, Cidade cidade);
    
    public String atualizarAcompanhamento(int codigo, EstadoDeAcompanhamento acompanhamento, Cidade cidade);
    
}
