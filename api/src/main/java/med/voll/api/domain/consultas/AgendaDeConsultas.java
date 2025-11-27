package med.voll.api.domain.consultas;

import med.voll.api.domain.consultas.validacoes.ValidadorAgendamentoDeConsultas;
import med.voll.api.domain.medicos.Medico;
import med.voll.api.domain.medicos.MedicoRepository;
import med.voll.api.domain.pacientes.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorAgendamentoDeConsultas> validadores;
    public DetalhesConsultaDTO agendar(CadastroConsultaDTO dados) {
        if (!pacienteRepository.existsById(dados.paciente_id())) {
            throw new ValidacaoException("Paciente não encontrado.");
        }
        if (dados.medico_id() != null && !medicoRepository.existsById(dados.medico_id())) {
            throw new ValidacaoException("Médico não encontrado.");
        }
        var medico = escolheMedico(dados);
        var paciente = pacienteRepository.getReferenceById(dados.paciente_id());
        var consulta = new Consulta(medico, paciente, dados.especialidade(), dados.data_consulta());

        validadores.forEach(v -> v.validar(dados));

        consultaRepository.save(consulta);
        return new DetalhesConsultaDTO(consulta);
    }

    private Medico escolheMedico(CadastroConsultaDTO dados) {
        if (dados.medico_id() != null) {
            return medicoRepository.getReferenceById(dados.medico_id());
        }
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data_consulta());


    }
}
