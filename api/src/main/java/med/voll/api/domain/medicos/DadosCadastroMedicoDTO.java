package med.voll.api.domain.medicos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.EnderecoDTO;

public record DadosCadastroMedicoDTO(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @Pattern(regexp = "\\d{4,6}")
        @NotNull
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        EnderecoDTO endereco,
        @NotNull
        @Pattern(regexp = "\\d{10}")
        String telefone,
        Boolean ativo
) {}
