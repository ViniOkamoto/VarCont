package br.com.fatec.VarCont.DataSource.Models;

import java.io.Serializable;

import javax.persistence.*;
@Entity
@Table(name = "tbl_usuario")
public class Usuario implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 5818703528229340023L;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "usuario_id")        
long id;

@Column(name = "usuario_nome")
String nome;

@Column(name = "usuario_email")
String email;

@Column(name = "usuario_senha")
String senha;
    
@Column(name = "usuario_administrador")
boolean admin;


public Usuario(){
    
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}