package med.voll.api.domain.consultas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medicos.Especialidade;
import med.voll.api.domain.medicos.Medico;
import med.voll.api.domain.pacientes.Paciente;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @JoinColumn(name = "data_consulta")
    private LocalDateTime data_consulta;

    public Consulta(Medico medico, Paciente paciente, Especialidade especialidade, LocalDateTime dataConsulta) {
        this.medico = medico;
        this.paciente = paciente;
        this.especialidade = especialidade;
        this.data_consulta = dataConsulta;
    }

    public Long getPacienteId() { return this.paciente.getId(); }
    public Long getMedicoId() { return this.medico.getId(); }
    public LocalDateTime getDataConsulta() { return this.data_consulta; }
    public Especialidade getEspecialidade() { return this.especialidade; }
    public Long getId() { return this.id; }
}

