package br.com.fatec.VarCont.Models;
import java.util.Date;
import javax.persistence.*;
public class Venda {
    
    @Id
    long id;

    @Column(name = "Data")
    Date data = new Date();
    /*
    Precisamos fazer um jeito de fazer a data DD/MM/AA HH/MM
    */
    @Column (name = "IdUsuario")
    long IdUsuario;
    
    @Column (name = "IdProduto")
    long IdProduto;
    
    @Column (name = "Quantidade")
    int Quantidade;

    public Venda(long id, long IdUsuario, long IdProduto, int Quantidade) {
        this.id = id;
        this.IdUsuario = IdUsuario;
        this.IdProduto = IdProduto;
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

    public long getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(long IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public long getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(long IdProduto) {
        this.IdProduto = IdProduto;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }
    
    
    
    
}
