package med.voll.api.domain.medicos;

public record DadosListagemMedicoDTO(
        Long id,
        String nome,
        String crm,
        String email,
        Especialidade especialidade,
        Boolean ativo
) {
    public DadosListagemMedicoDTO(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getCrm(),
                medico.getEmail(),
                medico.getEspecialidade(),
                medico.getAtivo()
        );
    }
}
