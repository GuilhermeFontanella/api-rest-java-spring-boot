package med.voll.api.usuario;

import med.voll.api.domain.usuario.Usuario;

public record DadosUsuarioDTO(
        Long id,
        String login
) {
    public DadosUsuarioDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getUsername()
        );
    }
}
