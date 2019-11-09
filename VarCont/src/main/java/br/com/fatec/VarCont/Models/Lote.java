package br.com.fatec.VarCont.Models;
import java.util.Date;
import javax.persistence.*;
public class Lote {
    
    @Id
    long id;
    
    @Column(name = "Data")
    private Date data = new Date();
    /*
    Precisamos fazer um jeito de fazer a data DD/MM/AA HH/MM
    */
    @Column(name = "idProduto")
    long idProduto;
    
    @Column(name = "Quantidade")
    int Quantidade;

    public Lote(long id, long idProduto, int Quantidade) {
        this.id = id;
        this.idProduto = idProduto;
        this.Quantidade = Quantidade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }
    
    
}
