
package br.com.fatec.VarCont.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Repository.UsuarioRepository;
import java.util.List;

@RestController
@RequestMapping(value = "/varcont")
public class UserControllers {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping(path = "/admin/caixa")
	public List<Usuario> buscarUsuario() {
		return usuarioRepository.findAll();
	}
        
        @GetMapping(path = "/admin/caixa/{id}")
         public ResponseEntity<Optional<Usuario>> buscarUsuarioId(
            @PathVariable(name = "id", required = true)Long id){
         return ResponseEntity
                 .ok(usuarioRepository.findById(id));
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

