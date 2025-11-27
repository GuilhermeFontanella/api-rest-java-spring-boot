package med.voll.api.domain.consultas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medicos.Especialidade;

import java.time.LocalDateTime;

public record CadastroConsultaDTO(
        @NotNull
        Long paciente_id,
        Long medico_id,
        Especialidade especialidade,
        @NotNull
        LocalDateTime data_consulta
) {}
