package br.com.fatec.VarCont.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Repository.UsuarioRepository;
import br.com.fatec.VarCont.Resource.Models.UsuarioResource;
import br.com.fatec.VarCont.services.BuscarCaixasService;
import br.com.fatec.VarCont.services.CadastroCaixaService;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/admin")
public class UserControllers {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BuscarCaixasService serviceBuscar;

	@Autowired
	private CadastroCaixaService serviceCadastro;

	@GetMapping(path = "/caixa")
	public List<Usuario> buscarCaixa() {
		return serviceBuscar.buscarCaixa();
	}

	@GetMapping(path = "/caixa/{id}")
	public ResponseEntity<Optional<Usuario>> buscarCaixaId(@PathVariable(name = "id", required = true) Long id) {
		return ResponseEntity.ok(usuarioRepository.findById(id));
	}

	@PostMapping(path = "/caixa/criar")
	public void criarCaixa(@RequestBody UsuarioResource usuario) {

		serviceCadastro.cadastrar(usuario);
	}

	@DeleteMapping(path = "/caixa/delete/{id}")
	public void deleteCaixa(@PathVariable(name = "id", required = true) Long id) {
		usuarioRepository.deleteById(id);
	}
//	@RequestMapping(value = "/adicionarLote", method = RequestMethod.GET)
//	public String adicionarLote(Model model) {
//                Produto p = new Produto();
//                p.setNomeprod("maça");
//                p.setValorCompra(2.00);
//                p.setValorVenda(4.00);
//                produtoRepository.save(p);
//    //Produto p = produtoRepository.getByNome("maça");
//		Lote lote = new Lote();
//		lote.setProduto(p);
//		lote.setQuantidade(12);
//		lote.setData(new Date());
//		loteRepository.save(lote);
//		model.addAttribute("lote", lote);
//		return "teste";
//	}

}
