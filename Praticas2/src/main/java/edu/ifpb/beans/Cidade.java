/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Sergiod
 */
@Entity
public class Cidade implements Serializable{

    @EmbeddedId
    private CidadePK CidadePK;
    
    @OneToOne(mappedBy = "cidade")
    private Prefeitura prefeitura;
    @OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL)
    private List<Denuncia> denuncias;
            
    public Cidade() {
    }

    public Cidade(CidadePK CidadePK) {
        this.CidadePK = CidadePK;
        this.denuncias = new ArrayList<>();
    }
    
    public Cidade(CidadePK CidadePK, Prefeitura prefeitura, List<Denuncia> denuncias) {
        this.CidadePK = CidadePK;
        this.prefeitura = prefeitura;
        this.denuncias = denuncias;
    }

    public CidadePK getCidadePK() {
        return CidadePK;
    }

    public void setCidadePK(CidadePK CidadePK) {
        this.CidadePK = CidadePK;
    }

    public Prefeitura getPrefeitura() {
        return prefeitura;
    }

    public void setPrefeitura(Prefeitura prefeitura) {
        this.prefeitura = prefeitura;
    }

    public List<Denuncia> getDenuncias() {
        return denuncias;
    }

    public void setDenuncias(List<Denuncia> denuncias) {
        this.denuncias = denuncias;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.CidadePK);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cidade other = (Cidade) obj;
        if (!Objects.equals(this.CidadePK, other.CidadePK)) {
            return false;
        }
        return true;
    }

    
}
