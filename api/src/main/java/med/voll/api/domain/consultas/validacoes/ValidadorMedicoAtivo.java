package med.voll.api.domain.consultas.validacoes;

import med.voll.api.domain.consultas.CadastroConsultaDTO;
import med.voll.api.domain.medicos.MedicoRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsultas {
    @Autowired
    MedicoRepository medicoRepository;
    public void validar(CadastroConsultaDTO dados) {
        if (dados.medico_id() == null) {
            return;
        }
        var medico = medicoRepository.getReferenceById(dados.medico_id());
        if (!medico.getAtivo()) {
            throw new ValidacaoException("O médico selecionado não está ativo");
        }
    }
}
