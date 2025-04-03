package com.pessoa.apipessoa.service;

import com.pessoa.apipessoa.entity.Pessoa;
import com.pessoa.apipessoa.exception.CpfJaCadastradoException;
import com.pessoa.apipessoa.exception.NotFoundException;
import com.pessoa.apipessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public ResponseEntity<List<Pessoa>> listarPessoas() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        if(pessoas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pessoas);
    }

    public ResponseEntity<Pessoa> cadastrar(Pessoa pessoa) {
        if (pessoaRepository.findByCpf(pessoa.getCpf()) != null) {
            throw new CpfJaCadastradoException("CPF já cadastrado");
        }

        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // Usa a requisição atual
                .path("/{id}") // Adiciona o ID do recurso
                .buildAndExpand(pessoaSalva.getId()) // Expande o ID na URI
                .toUri(); // Converte para um objeto URI

        // Retorna 201 Created com a URI do recurso criado
        return ResponseEntity.created(location).body(pessoaSalva);
    }


    public ResponseEntity<Object> deletarPessoa(Long id) {
        return pessoaRepository.findById(id)
                .map(pessoa -> {
                    pessoaRepository.delete(pessoa);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
    }


    public ResponseEntity<Pessoa> alterarPessoa(Long id, Pessoa pessoa) {
        return pessoaRepository.findById(id)
                .map(pessoaExistente -> {
                    pessoa.setId(id);
                    Pessoa pessoaAtualizada = pessoaRepository.save(pessoa);
                    return ResponseEntity.ok(pessoaAtualizada);
                })
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
    }

    public ResponseEntity<Pessoa> buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
    }
}