package med.voll.api.domain.consultas.validacoes;

import med.voll.api.domain.consultas.CadastroConsultaDTO;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsultas {
    public void validar(CadastroConsultaDTO dados) {
        var dataConsulta = dados.data_consulta();
        var isDomingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAberturaDaClinica = dataConsulta.getHour() < 7;
        var aposFechamentoDaClinica = dataConsulta.getHour() > 18;
        if (antesAberturaDaClinica || aposFechamentoDaClinica || isDomingo) {
            throw new ValidacaoException("Horário inválido. A consulta deve ser marcada entre 07:00 e 18:00, de Seg a Sáb");
        }
    }
}
