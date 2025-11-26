package med.voll.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String cidade,
        String numero,
        String complemento,
        @NotBlank
        String uf
) {
        public EnderecoDTO(Endereco endereco) {
                this(
                        endereco.getLogradouro(),
                        endereco.getBairro(),
                        endereco.getCep(),
                        endereco.getCidade(),
                        endereco.getNumero(),
                        endereco.getComplemento(),
                        endereco.getUf()
                );
        }
}
