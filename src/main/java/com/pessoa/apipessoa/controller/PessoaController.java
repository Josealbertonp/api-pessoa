package com.pessoa.apipessoa.controller;

import com.pessoa.apipessoa.entity.Pessoa;
import com.pessoa.apipessoa.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@Tag(name = "Pessoas", description = "API para gerenciamento de cadastro de pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    @Operation(summary = "Listar todas as pessoas", description = "Retorna uma lista completa de todas as pessoas cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de pessoas retornada com sucesso")
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        return pessoaService.listarPessoas();
    }

    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastrar nova pessoa", description = "Cria um novo registro de pessoa no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pessoa cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<Pessoa> cadastrar(@RequestBody Pessoa pessoa) {
        return pessoaService.cadastrar(pessoa);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Excluir pessoa", description = "Remove uma pessoa do sistema pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pessoa excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    public ResponseEntity<Object> deletarPessoa(
            @Parameter(description = "ID da pessoa a ser excluída", example = "1")
            @PathVariable Long id) {
        return pessoaService.deletarPessoa(id);
    }

    @PutMapping("/alterar/{id}")
    @Operation(summary = "Atualizar pessoa", description = "Atualiza os dados de uma pessoa existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<Pessoa> alterarPessoa(
            @Parameter(description = "ID da pessoa a ser atualizada", example = "1")
            @PathVariable Long id,
            @RequestBody Pessoa pessoa) {
        return pessoaService.alterarPessoa(id, pessoa);
    }

    @GetMapping("/localizar/{id}")
    @Operation(summary = "Buscar pessoa por ID", description = "Retorna os dados de uma pessoa específica")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    public ResponseEntity<Pessoa> buscarPessoaPorId(
            @Parameter(description = "ID da pessoa a ser localizada", example = "1")
            @PathVariable Long id) {
        return pessoaService.buscarPessoaPorId(id);
    }
}