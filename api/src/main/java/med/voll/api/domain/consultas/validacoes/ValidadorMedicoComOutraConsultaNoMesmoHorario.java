package med.voll.api.domain.consultas.validacoes;

import med.voll.api.domain.consultas.CadastroConsultaDTO;
import med.voll.api.domain.consultas.ConsultaRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsultas {
    @Autowired
    ConsultaRepository consultaRepository;
    public void validar(CadastroConsultaDTO dados) {
        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndDataConsulta(dados.medico_id(), dados.data_consulta());
        if (medicoPossuiOutraConsultaNoMesmoHorario != null && medicoPossuiOutraConsultaNoMesmoHorario > 0) {
            throw new ValidacaoException("O médico já possui consulta nesse horário");
        }
    }
}
