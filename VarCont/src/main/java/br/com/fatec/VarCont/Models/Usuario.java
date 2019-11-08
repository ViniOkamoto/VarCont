package br.com.fatec.VarCont.Models;

import javax.persistence.*;
public class Usuario {

@Id
long id;
@Column(name = "nome")
String nome;

@Column(name = "Email")
String Email;

@Column(name = "Senha")
String Senha;

    public Usuario(int id, String nome, String Email, String Senha) {
        this.id = id;
        this.nome = nome;
        this.Email = Email;
        this.Senha = Senha;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }


}