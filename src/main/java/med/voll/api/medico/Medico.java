package med.voll.api.medico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

// Define a tabela no banco de dados associada à classe Medico
@Table(name = "medicos")
// Declara que esta classe é uma entidade JPA, ou seja, ela será mapeada para uma tabela no banco de dados
@Entity(name = "Medico")
// Gera os getters automaticamente para todos os atributos da classe
@Getter
// Gera um construtor sem argumentos (necessário para o JPA)
@NoArgsConstructor
// Gera um construtor com todos os argumentos (útil para inicializar objetos completos)
@AllArgsConstructor
// Gera métodos equals() e hashCode() baseados no campo 'id'
@EqualsAndHashCode(of = "id")
public class Medico {

    // Define o campo 'id' como chave primária da tabela
    @Id
    // Define que o valor de 'id' será gerado automaticamente pelo banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único do médico (chave primária)

    // Nome do médico
    private String nome;

    // Email do médico
    private String email;

    // Telefone do médico
    private String telefone;

    // CRM (número de registro profissional) do médico
    private String crm;

    // Enum que representa a especialidade médica, armazenada como string no banco de dados
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade; // Especialidade do médico

    // Indica que o objeto 'endereco' será embutido na tabela de medicos, reutilizando o modelo Endereco
    @Embedded
    private Endereco endereco; // Endereço do médico

    // Atributo que indica se o médico está ativo ou não (exclusão lógica)
    private Boolean ativo;


    // Construtor que inicializa um médico a partir de dados de cadastro
    public Medico(DadosCadastroMedico dados) {
        this.ativo = true; // Define o médico como ativo ao ser criado
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco()); // Inicializa o endereço a partir dos dados recebidos
        this.especialidade = dados.especialidade(); // Define a especialidade médica
    }

    // Método para atualizar as informações do médico com base nos novos dados fornecidos
    public void atualizarInformacoes(@Valid DadosAtualizaMedico dados) {
        if (dados.nome() != null){
            this.nome = dados.nome(); // Atualiza o nome se fornecido
        }
        if (dados.telefone() != null){
            this.telefone = dados.telefone(); // Atualiza o telefone se fornecido
        }
        if (dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco()); // Atualiza o endereço se fornecido
        }
    }

    // Método de exclusão lógica (em vez de deletar o registro, apenas marca como inativo)
    public void excluir() {
        this.ativo = false; // Marca o médico como inativo
    }
}
