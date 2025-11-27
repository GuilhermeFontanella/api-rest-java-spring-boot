package med.voll.api.domain.consultas.validacoes;

import med.voll.api.domain.consultas.CadastroConsultaDTO;
import med.voll.api.domain.pacientes.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsultas {
    @Autowired
    PacienteRepository pacienteRepository;

    public void validar(CadastroConsultaDTO dados) {
        var paciente = pacienteRepository.getReferenceById(dados.paciente_id());
        if (!paciente.getAtivo()) {
            throw new ValidacaoException("O paciente está inativo");
        }
    }
}
