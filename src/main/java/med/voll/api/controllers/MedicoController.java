package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

// Define esta classe como um controlador REST do Spring, que gerencia requisições HTTP e retorna dados no formato JSON
@RestController
// Mapeia requisições que iniciam com "medicos" para os métodos dentro deste controller
@RequestMapping("medicos")
public class MedicoController {

    // Injeção automática de dependência do repositório de médicos
    @Autowired
    private MedicoRepository repository; // O repositório faz a interface com o banco de dados para a entidade Medico

    // Mapeia requisições HTTP POST para este método
    @PostMapping
    @Transactional // Garante que as operações do método sejam executadas dentro de uma transação do banco de dados
    // O método recebe dados no corpo da requisição, os valida e salva um novo médico no banco de dados
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        // Cria uma instância de Medico com os dados fornecidos e persiste no banco de dados
        repository.save(new Medico(dados));
    }

    // Mapeia requisições HTTP GET para listar médicos
    @GetMapping
    // O método retorna uma página de médicos ativos, com paginação configurável e ordenação por nome
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        // Busca todos os médicos ativos (campo 'ativo' true) e os retorna mapeados para DadosListagemMedico
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    // Mapeia requisições HTTP PUT para atualizar um médico
    @PutMapping
    @Transactional
    // O método atualiza os dados de um médico existente com base nas informações recebidas
    public void atualizar(@RequestBody @Valid DadosAtualizaMedico dados){
        // Obtém uma referência ao médico pelo ID e atualiza suas informações com os novos dados fornecidos
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    // Mapeia requisições HTTP DELETE para excluir um médico pelo ID
    @DeleteMapping("/{id}")
    @Transactional
    // O método exclui (exclusão lógica) um médico com base no ID fornecido
    public void excluir(@PathVariable Long id){
        // Obtém o médico pelo ID e chama o método excluir (tornar inativo)
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}
