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
public class Funcionario extends Pessoa implements Serializable{
    @Column (nullable = false)
    private String nome;
    @Column (nullable = false, unique = true)
    private String cpf;
    
    @ManyToMany(mappedBy = "funcionarios", cascade = CascadeType.ALL)
    private List<Prefeitura> prefeituras;

    public Funcionario() {
    }

    public Funcionario(String nome, String cpf, String email, String senha) {
        super(email, senha);
        this.nome = nome;
        this.cpf = cpf;
        this.prefeituras = new ArrayList<>();
    }

    public Funcionario(String nome, String cpf, String email, String senha, List<Prefeitura> prefeituras) {
        super(email, senha);
        this.nome = nome;
        this.cpf = cpf;
        this.prefeituras = prefeituras;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Prefeitura> getPrefeituras() {
        return prefeituras;
    }

    public void setPrefeituras(List<Prefeitura> prefeituras) {
        this.prefeituras = prefeituras;
    }
    
    
}
