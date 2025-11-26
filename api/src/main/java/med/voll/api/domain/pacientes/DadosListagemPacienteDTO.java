package med.voll.api.domain.pacientes;

public record DadosListagemPacienteDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        Boolean ativo
) {
    public DadosListagemPacienteDTO(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getCpf(),
                paciente.getAtivo()
        );
    }
}
