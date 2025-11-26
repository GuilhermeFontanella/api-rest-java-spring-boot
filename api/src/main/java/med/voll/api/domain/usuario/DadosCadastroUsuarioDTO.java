package med.voll.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuarioDTO(
        @NotBlank
        String login,
        @NotBlank
        String senha
) {}
