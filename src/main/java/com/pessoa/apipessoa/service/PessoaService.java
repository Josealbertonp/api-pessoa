package com.pessoa.apipessoa.service;

import com.pessoa.apipessoa.entity.Pessoa;
import com.pessoa.apipessoa.exception.NotFoundException;
import com.pessoa.apipessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(pessoa.getId() != null) {
            pessoa.setId(null);
        }
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        return ResponseEntity.status(201).body(pessoaSalva);
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