package med.voll.api.domain.consultas.validacoes;

import med.voll.api.domain.consultas.CadastroConsultaDTO;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioConsulta implements ValidadorAgendamentoDeConsultas {
    public void validar(CadastroConsultaDTO dados) {
        var horarioConsulta = dados.data_consulta();
        var horaAgora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(horaAgora, horarioConsulta).toMinutes();
        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("A consulta deve ser agendada com pelo menos 30 minutos de antecedência.");
        }
    }
}
