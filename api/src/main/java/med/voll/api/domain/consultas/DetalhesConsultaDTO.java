package med.voll.api.domain.consultas;

import med.voll.api.domain.medicos.Especialidade;

import java.time.LocalDateTime;

public record DetalhesConsultaDTO(
        Long id,
        Long pacienteId,
        Long medicoId,
        Especialidade especialidade,
        LocalDateTime dataConsulta
) {
    public DetalhesConsultaDTO(Consulta consulta) {
        this(
                consulta.getId(),
                consulta.getPacienteId(),
                consulta.getMedicoId(),
                consulta.getEspecialidade(),
                consulta.getDataConsulta()
        );
    }
}
