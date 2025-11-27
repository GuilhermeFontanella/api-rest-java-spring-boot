package med.voll.api.domain.consultas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    @Query(value = """
            SELECT COUNT(*) > 0
            FROM consultas
            WHERE medico_id = :medicoId
            AND data_consulta = :dataConsulta
            """, nativeQuery = true)
    Long existsByMedicoIdAndDataConsulta(Long medicoId, LocalDateTime dataConsulta);
}
