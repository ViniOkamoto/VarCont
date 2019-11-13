/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.VarCont.Resource.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author OkamotoPc
 */
public class UsuarioResource {

    @JsonProperty("usuario_id")
    private long id;
    
    @JsonProperty("usuario_nome")
    private String nome;
    
    @JsonProperty("usuario_email")
    private String email;
    
    @JsonProperty("usuario_senha")
    private String senha;
    
    @JsonProperty("usuario_admin")
    private boolean administrador;

    public UsuarioResource(long id, String nome, String email, String senha, boolean administrador) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.administrador = administrador;
    }

   
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
    
}
