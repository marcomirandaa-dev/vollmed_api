package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Anotação JPA que indica que essa classe pode ser embutida em outra entidade
@Embeddable
// Gera automaticamente os métodos getter para todos os atributos da classe
@Getter
// Gera um construtor sem argumentos (necessário para o JPA e útil para frameworks)
@NoArgsConstructor
// Gera um construtor com todos os argumentos (útil para inicializar objetos com todos os campos)
@AllArgsConstructor
public class Endereco {

    // Atributo que representa o logradouro do endereço (ex: nome da rua)
    private String logradouro;

    // Atributo que representa o bairro do endereço
    private String bairro;

    // Atributo que representa o CEP (Código de Endereçamento Postal)
    private String cep;

    // Atributo que representa o número do endereço
    private String numero;

    // Atributo que representa o complemento do endereço (informação adicional, ex: apartamento)
    private String complemento;

    // Atributo que representa a cidade do endereço
    private String cidade;

    // Atributo que representa a unidade federativa (UF) do endereço (ex: estado)
    private String uf;

// Construtor que recebe um objeto do tipo DadosEndereco e inicializa os atributos da classe Endereco
    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.uf = dados.uf();
        this.cidade = dados.cidade();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
    }

    public void atualizarInformacoes(DadosEndereco dados) {
        if(dados.logradouro() != null){
            this.logradouro = dados.logradouro();
        }
        if(dados.bairro() != null){
            this.bairro = dados.bairro();
        }
        if(dados.cep() != null){
            this.cep = dados.cep();
        }
        if(dados.uf() != null){
            this.uf = dados.uf();
        }
        if(dados.cidade() != null){
            this.cidade = dados.cidade();
        }
        if(dados.numero() != null){
            this.numero = dados.numero();
        }
        if(dados.complemento() != null){
            this.complemento = dados.complemento();
        }

    }
}