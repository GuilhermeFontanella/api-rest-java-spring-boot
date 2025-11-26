package med.voll.api.domain.endereco;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Embeddable
@Getter
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String uf;
    private String cidade;
    private String numero;
    private String complemento;

    public Endereco() {}
    public Endereco(EnderecoDTO dto) {
        this.logradouro = dto.logradouro();
        this.bairro = dto.bairro();
        this.cep = dto.cep();
        this.cidade = dto.cidade();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
        this.uf = dto.uf();
    }

    public void atualizarInformacoes(EnderecoDTO endereco) {
        if (endereco.logradouro() != null) this.logradouro = endereco.logradouro();
        if (endereco.bairro() != null) this.bairro = endereco.bairro();
        if (endereco.cep() != null) this.cep = endereco.cep();
        if (endereco.numero() != null) this.numero = endereco.numero();
        if (endereco.uf() != null) this.uf = endereco.uf();
        if (endereco.complemento() != null) this.complemento = endereco.complemento();
    }

    public String getLogradouro() { return this.logradouro; }
    public String getBairro() { return this.bairro; }
    public String getCep() { return this.cep; }
    public String getCidade() { return this.cidade; }
    public String getNumero() { return this.numero; }
    public String getComplemento() { return this.complemento; }
    public String getUf() { return this.uf; };
}
