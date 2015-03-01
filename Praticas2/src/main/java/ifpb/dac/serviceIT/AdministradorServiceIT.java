/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.serviceIT;

import edu.ifpb.beans.Administrador;

/**
 *
 * @author Sergio Diniz
 */
public interface AdministradorServiceIT {
    
    public Administrador login(String email, String senha);
    
}
