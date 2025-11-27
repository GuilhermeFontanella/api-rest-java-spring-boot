package med.voll.api.domain.medicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query(value = """
            select * from medicos m where m.ativo = 1 
            and m.especialidade = :especialidade 
            and m.id not in(
                select c.medico_id from consultas c where c.data_consulta = :dataConsulta
            )
            order by rand() limit 1
            """, nativeQuery = true)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime dataConsulta);
}
