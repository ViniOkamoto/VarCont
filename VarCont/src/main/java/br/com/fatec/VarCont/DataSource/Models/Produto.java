package br.com.fatec.VarCont.DataSource.Models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_produto")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prod_id")
    private long idProduto;
    
    @Column(name = "prod_nome")
    private String nomeProd;
    
    @Column(name = "prod_valor_compra")
    private double valorCompra;
    
    @Column(name = "prod_valor_venda")
    private double valorVenda;
    
    
    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeprod() {
        return nomeProd;
    }

    public void setNomeprod(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
    	this.valorVenda = valorVenda;
    }
 
}
