package med.voll.api.domain.medicos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.EnderecoDTO;

public record DadosAtualizacaoMedicoDTO(
        @NotNull
        Long id,
        String nome,
        String email,
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        Especialidade especialidade,
        EnderecoDTO endereco,
        @Pattern(regexp = "\\d{10}")
        String telefone,

        Boolean ativo
) {}
