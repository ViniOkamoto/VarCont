package br.com.fatec.VarCont.DataSource.Models;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_lote")
public class Lote {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lote_id")
    private long id;
    
    @Column(name = "lote_data")
    private Date data;
    /*
    Precisamos fazer um jeito de fazer a data DD/MM/AA HH/MM
    */
	@ManyToOne
        @JoinColumn(name = "prod_id", nullable = true)
    private Produto produto;
    
    @Column(name = "quantidade")
    int quantidade;


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

    public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
}
