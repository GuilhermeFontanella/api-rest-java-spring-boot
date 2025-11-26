package med.voll.api.domain.pacientes;

import med.voll.api.domain.endereco.Endereco;

public record DadosDetalhePacienteDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        Boolean ativo,
        Endereco endereco
) {
    public DadosDetalhePacienteDTO(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getCpf(),
                paciente.getAtivo(),
                paciente.getEndereco()

        );
    }
}
