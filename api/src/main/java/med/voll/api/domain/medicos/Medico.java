package med.voll.api.domain.medicos;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private String telefone;
    private Boolean ativo = true;

    public Medico() {}

    public Medico(DadosCadastroMedicoDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
        this.telefone = dados.telefone();
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public String getCrm() {
        return this.crm;
    }

    public Especialidade getEspecialidade() {
        return this.especialidade;
    }

    public Boolean getAtivo() { return this.ativo; }

    public Endereco getEndereco() { return this.endereco; }

    public void atualizarDados(DadosAtualizacaoMedicoDTO dados) {
        System.out.println(dados);
        if (dados.nome() != null) this.nome = dados.nome();
        if (dados.email() != null) this.email = dados.email();
        if (dados.crm() != null) this.crm = dados.crm();
        if (dados.especialidade() != null) this.especialidade = dados.especialidade();
        if (dados.endereco() != null) this.endereco.atualizarInformacoes(dados.endereco());
        if (dados.ativo() != null) this.ativo = dados.ativo();
    }

    public void desativarMedico() {
        this.ativo = false;
    }
}
