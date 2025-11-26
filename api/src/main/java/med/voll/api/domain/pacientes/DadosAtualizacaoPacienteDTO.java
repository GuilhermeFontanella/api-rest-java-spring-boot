package med.voll.api.domain.pacientes;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.EnderecoDTO;

public record DadosAtualizacaoPacienteDTO(
        @NotNull
        Long id,
        String nome,
        String email,
        String cpf,
        EnderecoDTO endereco,
        @Pattern(regexp = "\\d{10}")
        String telefone,

        Boolean ativo
) {}
