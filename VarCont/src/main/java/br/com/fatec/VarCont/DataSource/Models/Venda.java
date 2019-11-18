package br.com.fatec.VarCont.DataSource.Models;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "tbl_venda")
public class Venda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ven_id")
    long id;

    @Column(name = "ven_data")
    private Date data;
    /*
    Precisamos fazer um jeito de fazer a data DD/MM/AA HH/MM
    */
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario idUsuario;
    
    @ManyToOne
    @JoinColumn (name = "lote_id", referencedColumnName = "lote_id")
    private Lote idLote;
    
    @Column (name = "quantidade")
    private int quantidade;

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

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Lote getIdLote() {
		return idLote;
	}

	public void setIdLote(Lote idLote) {
		this.idLote = idLote;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
    
}
