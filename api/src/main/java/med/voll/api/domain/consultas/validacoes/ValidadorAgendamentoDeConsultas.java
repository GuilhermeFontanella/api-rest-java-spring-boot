package med.voll.api.domain.consultas.validacoes;

import med.voll.api.domain.consultas.CadastroConsultaDTO;

public interface ValidadorAgendamentoDeConsultas {
    void validar(CadastroConsultaDTO dados);
}
