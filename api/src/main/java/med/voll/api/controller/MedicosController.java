package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medicos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicosController {
    @Autowired
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(
            @RequestBody
            @Valid
            DadosCadastroMedicoDTO dados,
            UriComponentsBuilder uriBuilder
    ) {
        var medico = new Medico(dados);
        repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalheMedicoDTO((medico)));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicoDTO>> listarMedicos(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicoDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalheMedicoDTO> detalheMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalheMedicoDTO(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarMedico(
            @RequestBody
            @Valid
            DadosAtualizacaoMedicoDTO dados
    ) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarDados(dados);
        return ResponseEntity.ok(new DadosDetalheMedicoDTO(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativerMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.desativarMedico();

        return ResponseEntity.noContent().build();
    }
}
