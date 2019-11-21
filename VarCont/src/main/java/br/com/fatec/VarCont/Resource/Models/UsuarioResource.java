package br.com.fatec.VarCont.Resource.Models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class UsuarioResource {
  // Aqui � onde decidimos o que colocaremos na requisi��o para api.
	
	@JsonProperty("usuario_nome")
    private String nome;
    
    @JsonProperty("usuario_email")
    private String email;
    
    @JsonProperty("usuario_senha")
    private String senha;
    
    @JsonProperty("usuario_administrador")
    private String admin;

    public UsuarioResource(String nome, String email, String senha, String administrador) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.admin = administrador;
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

    public String getAdministrador() {
        return admin;
    }

    public void setAdministrador(String administrador) {
        this.admin = administrador;
    }


	@Override
	public String toString() {
		return "UsuarioResource [nome=" + nome + ", email=" + email + ", senha=" + senha + ", administrador="
				+ admin + "]";
	}
    
}