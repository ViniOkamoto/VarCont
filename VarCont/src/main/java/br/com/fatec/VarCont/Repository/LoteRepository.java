package br.com.fatec.VarCont.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fatec.VarCont.DataSource.Models.Lote;

public interface LoteRepository extends JpaRepository<Lote, Long>{
	
}