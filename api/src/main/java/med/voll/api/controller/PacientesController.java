package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.pacientes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacientesController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(
            @RequestBody
            @Valid
            DadosCadastroPacienteDTO dados,
            UriComponentsBuilder uriBuilder
    ) {
        var paciente = new Paciente(dados);
        repository.save(new Paciente(dados));
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhePacienteDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPacienteDTO>> listarPacientes(
            @PageableDefault(size = 10, page = 0, sort = "nome")
            Pageable paginacao
    ) {
        var page =  repository.findAllByAtivoTrue(paginacao).map(DadosListagemPacienteDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhePacienteDTO> pacientePorId(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhePacienteDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getCpf(),
                paciente.getAtivo(),
                paciente.getEndereco()
        ));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarPaciente(
            @RequestBody
            @Valid
            DadosAtualizacaoPacienteDTO dados
    ) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarDados(dados);
        return ResponseEntity.ok(new DadosDetalhePacienteDTO(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativarPaciente(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.desativarPaciente();
        return ResponseEntity.noContent().build();
    }
}
