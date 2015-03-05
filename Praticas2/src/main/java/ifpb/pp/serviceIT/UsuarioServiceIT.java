/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.pp.serviceIT;

import edu.pp.beans.Denuncia;
import edu.pp.beans.Usuario;
import java.util.List;

/**
 *
 * @author Sergio Diniz
 */
public interface UsuarioServiceIT {
    
    public Usuario login(String email, String senha);
    
    public String novaDenuncia(Usuario usuario, String cidade, String estado, String rua, 
                                String numeroS, String bairro, String descricao);
    
    public List<Denuncia> minhasDenuncias(String email);
    
    public String excluir(Usuario usuario);
    
}
