/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.beans;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Sergiod
 */
@Entity
public class Administrador extends Pessoa implements Serializable{

    public Administrador() {
    }

    public Administrador(String email, String senha) {
        super(email, senha);
    }
    
    
    
    
}
