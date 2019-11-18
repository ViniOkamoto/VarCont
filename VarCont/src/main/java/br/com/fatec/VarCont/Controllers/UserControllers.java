package br.com.fatec.VarCont.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Repository.UsuarioRepository;
import br.com.fatec.VarCont.Resource.Models.UsuarioResource;
import br.com.fatec.VarCont.exceptions.UsuarioNotFoundException;
import br.com.fatec.VarCont.services.UsuarioService;
import br.com.fatec.VarCont.services.UsuarioConversor;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/admin")
public class UserControllers {

	@Autowired
	UsuarioConversor serviceConversor;

	@Autowired
	private UsuarioService serviceCaixa;

	@Autowired
	private UsuarioRepository usuarioRepository;


	@GetMapping(path = "/caixa")
	public List<Usuario> buscarCaixa() {
		return serviceCaixa.buscarUsuario();
	}

	@GetMapping("caixa/{id}")
	public Usuario buscarCaixaId(@PathVariable(name = "id", required = true) Long id) throws UsuarioNotFoundException {
		return serviceCaixa.buscarId(id);
	}

	@PostMapping("caixa/criar")
	public void criarCaixa(@Valid @RequestBody UsuarioResource usuarioResource) {

		serviceCaixa.cadastrarUsuario(usuarioResource);
	}

	@DeleteMapping("caixa/{id}")
	public void deleteCaixa(@PathVariable(name = "id", required = true) Long id) throws UsuarioNotFoundException {
		serviceCaixa.deletarId(id);
	}

	@PutMapping("caixa/{id}")
	public ResponseEntity<Usuario> alterarCaixa(@PathVariable Long id, @RequestBody Usuario usuario) {

		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		if (!usuarioOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}else {

		usuario.setId(id);
		usuarioRepository.save(usuario);

		return ResponseEntity.noContent().build();
		}
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
