
package br.com.fatec.VarCont.Controllers;
import br.com.fatec.VarCont.DataSource.Models.Lote;
import br.com.fatec.VarCont.DataSource.Models.Produto;
import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Repository.LoteRepository;
import br.com.fatec.VarCont.Repository.ProdutoRepository;

import org.springframework.web.bind.annotation.*;

import java.util.Date;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
@Controller
@RequestMapping("/VarCont")
public class UserControllers {
	
	@Autowired
	LoteRepository LoteRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
    
	@RequestMapping(value = "/adicionarLote", method = RequestMethod.GET)
	public String adicionarLote(Model model) {
		Produto p = produtoRepository.getByNome("maça");
		Lote lote = new Lote();
		lote.setProduto(p);
		lote.setQuantidade(12);
		lote.setData(new Date());
		LoteRepository.save(lote);
		model.addAttribute("lote", lote);
		return "teste";
	}
    
}
