package com.pessoa.apipessoa.controller;

import com.pessoa.apipessoa.entity.Pessoa;
import com.pessoa.apipessoa.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private PessoaController pessoaController;

    private Pessoa criarPessoaValida() {
        return new Pessoa(1L, "Fulano", "12345678901", LocalDate.of(1990, 1, 1), "fulano@email.com");
    }

    @Test
    void listarPessoas_DeveChamarServiceERetornarOk() {
        Pessoa pessoa = criarPessoaValida();
        when(pessoaService.listarPessoas()).thenReturn(ResponseEntity.ok(List.of(pessoa)));

        ResponseEntity<List<Pessoa>> resposta = pessoaController.listarPessoas();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(1, resposta.getBody().size());
        verify(pessoaService, times(1)).listarPessoas();
    }

    @Test
    void cadastrar_DeveRetornarCreated() {
        Pessoa pessoa = criarPessoaValida();
        when(pessoaService.cadastrar(pessoa)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(pessoa));

            ResponseEntity<String> resposta = (ResponseEntity<String>) pessoaController.cadastrar(pessoa);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(pessoa, resposta.getBody());
    }

    @Test
    void deletarPessoa_DeveRetornarNoContent() {
        Long id = 1L;
        when(pessoaService.deletarPessoa(id)).thenReturn(ResponseEntity.noContent().build());

        ResponseEntity<Object> resposta = pessoaController.deletarPessoa(id);

        assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
    }

    @Test
    void alterarPessoa_DeveRetornarOk() {
        Long id = 1L;
        Pessoa pessoa = criarPessoaValida();
        when(pessoaService.alterarPessoa(id, pessoa)).thenReturn(ResponseEntity.ok(pessoa));

        ResponseEntity<Pessoa> resposta = pessoaController.alterarPessoa(id, pessoa);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(pessoa, resposta.getBody());
    }

    @Test
    void buscarPessoaPorId_DeveRetornarOk() {
        Long id = 1L;
        Pessoa pessoa = criarPessoaValida();
        when(pessoaService.buscarPessoaPorId(id)).thenReturn(ResponseEntity.ok(pessoa));

        ResponseEntity<Pessoa> resposta = pessoaController.buscarPessoaPorId(id);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(pessoa, resposta.getBody());
    }
}