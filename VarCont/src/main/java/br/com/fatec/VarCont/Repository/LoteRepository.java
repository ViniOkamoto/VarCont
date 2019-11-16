package br.com.fatec.VarCont.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fatec.VarCont.DataSource.Models.Lote;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Long>{
	
}
