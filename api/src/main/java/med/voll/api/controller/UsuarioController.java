package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosCadastroUsuarioDTO;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.domain.usuario.UsuarioRespository;
import med.voll.api.usuario.DadosUsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/registra-usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRespository respository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(
            @RequestBody
            @Valid
            DadosCadastroUsuarioDTO dados,
            UriComponentsBuilder uriBuilder
    ) {
        var usuario = new Usuario(dados);
        respository.save(usuario);
        var uri = uriBuilder.path("/registra-usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosUsuarioDTO(usuario));
    }

}
