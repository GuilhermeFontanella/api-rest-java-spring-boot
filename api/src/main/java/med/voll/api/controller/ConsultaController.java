package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consultas.AgendaDeConsultas;
import med.voll.api.domain.consultas.CadastroConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    AgendaDeConsultas agendaDeConsultas;
    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(
            @RequestBody
            @Valid
            CadastroConsultaDTO dados,
            UriComponentsBuilder uriBuilder
    ) {
        try {
            System.out.println(dados);
            var consultaAgendada = agendaDeConsultas.agendar(dados);
            var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(consultaAgendada.id()).toUri();
            return ResponseEntity.created(uri).body(consultaAgendada);
        } catch (Exception error) {
            return ResponseEntity.internalServerError().body(error);
        }
    }

}
