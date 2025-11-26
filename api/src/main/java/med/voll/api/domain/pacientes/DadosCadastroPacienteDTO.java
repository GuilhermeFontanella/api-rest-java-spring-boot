package med.voll.api.domain.pacientes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.EnderecoDTO;

public record DadosCadastroPacienteDTO(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @Pattern(regexp = "\\d{10}")
        String telefone,
        @NotBlank
        String cpf,
        @NotNull
        @Valid
        EnderecoDTO endereco,
        Boolean ativo
) {}
