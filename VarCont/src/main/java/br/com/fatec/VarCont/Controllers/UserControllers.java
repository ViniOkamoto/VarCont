
package br.com.fatec.VarCont.Controllers;
import br.com.fatec.VarCont.Models.Usuario;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
@RestController
@RequestMapping("/VarCont")
public class UserControllers {
    
    @Autowired
    private Usuario teste;
    
}
