package br.com.fatec.VarCont.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fatec.VarCont.DataSource.Models.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {	
	@Query(value = "SELECT * FROM tbl_venda  WHERE lote_id = :id", nativeQuery = true)
	public Optional<Venda> findByLoteId(@Param("id") Long id);
}
