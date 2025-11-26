package med.voll.api.domain.pacientes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private  String cpf;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Paciente() {}

    public Paciente(DadosCadastroPacienteDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atualizarDados(DadosAtualizacaoPacienteDTO dados) {
        if (dados.nome() != null) this.nome = dados.nome();
        if (dados.email() != null) this.email = dados.email();
        if (dados.cpf() != null) this.cpf = dados.cpf();
        if (dados.endereco() != null) this.endereco.atualizarInformacoes(dados.endereco());
        if (dados.ativo() != null) this.ativo = dados.ativo();
    }

    public void desativarPaciente() {
        this.ativo = false;
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() { return this.nome; }

    public String getEmail() {
        return this.email;
    }

    public String getCpf() {
        return this.cpf;
    }

    public Boolean getAtivo() { return this.ativo; }
    public Endereco getEndereco() { return this.endereco; }

    public Paciente detalhesPaciente() {
        return this;
    }
}
