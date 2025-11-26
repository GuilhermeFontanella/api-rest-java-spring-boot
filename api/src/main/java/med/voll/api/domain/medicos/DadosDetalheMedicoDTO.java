package med.voll.api.domain.medicos;

import med.voll.api.domain.endereco.Endereco;

public record DadosDetalheMedicoDTO(
        Long id,
        String nome,
        String crm,
        String email,
        Especialidade especialidade,
        Boolean ativo,
        Endereco endereco
) {
    public DadosDetalheMedicoDTO(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getCrm(),
                medico.getEmail(),
                medico.getEspecialidade(),
                medico.getAtivo(),
                medico.getEndereco()
        );
    }
}
