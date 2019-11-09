package br.com.fatec.VarCont.Models;
import javax.persistence.*;
public class Produto {
    
    @Id
    long idP;
    
    @Column(name = "nomeprod")
    String nomeprod;
    
    @Column(name = "ValorCompra")
    String ValorCompra;
    
    @Column(name = "ValorVenda")
    String ValorVenda;

    public Produto(long idP, String nomeprod, String ValorCompra, String ValorVenda) {
        this.idP = idP;
        this.nomeprod = nomeprod;
        this.ValorCompra = ValorCompra;
        this.ValorVenda = ValorVenda;
    }

    public long getIdP() {
        return idP;
    }

    public void setIdP(long idP) {
        this.idP = idP;
    }

    public String getNomeprod() {
        return nomeprod;
    }

    public void setNomeprod(String nomeprod) {
        this.nomeprod = nomeprod;
    }

    public String getValorCompra() {
        return ValorCompra;
    }

    public void setValorCompra(String ValorCompra) {
        this.ValorCompra = ValorCompra;
    }

    public String getValorVenda() {
        return ValorVenda;
    }

    public void setValorVenda(String ValorVenda) {
        this.ValorVenda = ValorVenda;
    }

    
}