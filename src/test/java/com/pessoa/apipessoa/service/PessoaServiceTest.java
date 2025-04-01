package com.pessoa.apipessoa.service;

import com.pessoa.apipessoa.entity.Pessoa;
import com.pessoa.apipessoa.exception.NotFoundException;
import com.pessoa.apipessoa.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    private Pessoa pessoa;

    @BeforeEach
    void setUp() {
        pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Fulano");
        pessoa.setCpf("12345678901");
        pessoa.setDataNascimento(LocalDate.now());
        pessoa.setEmail("teste@teste.com");
    }

    @Test
    void listarPessoas_DeveRetornarListaVaziaQuandoNaoHouverRegistros() {
        when(pessoaRepository.findAll()).thenReturn(List.of());

        ResponseEntity<List<Pessoa>> resposta = pessoaService.listarPessoas();

        assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());

        assertNull(resposta.getBody());
    }


    @Test
    void listarPessoas_DeveRetornarPessoasQuandoExistiremRegistros() {
        when(pessoaRepository.findAll()).thenReturn(List.of(pessoa));

        ResponseEntity<List<Pessoa>> resposta = pessoaService.listarPessoas();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(1, resposta.getBody().size());
    }

    @Test
    void cadastrar_DeveRetornarPessoaCriadaComStatus201() {
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        ResponseEntity<Pessoa> resposta = pessoaService.cadastrar(pessoa);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(pessoa, resposta.getBody());
    }

    @Test
    void buscarPessoaPorId_DeveRetornarPessoaQuandoExistir() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        ResponseEntity<Pessoa> resposta = pessoaService.buscarPessoaPorId(1L);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(pessoa, resposta.getBody());
    }

    @Test
    void buscarPessoaPorId_DeveLancarExcecaoQuandoNaoExistir() {
        when(pessoaRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> pessoaService.buscarPessoaPorId(2L));
    }

    @Test
    void alterarPessoa_DeveRetornarPessoaAtualizada() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        ResponseEntity<Pessoa> resposta = pessoaService.alterarPessoa(1L, pessoa);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(pessoa, resposta.getBody());
    }

    @Test
    void alterarPessoa_DeveLancarExcecaoQuandoNaoExistir() {
        Long pessoaId = 1L;
        Pessoa pessoaAlterada = new Pessoa();
        pessoaAlterada.setId(pessoaId);

        when(pessoaRepository.findById(pessoaId)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            pessoaService.alterarPessoa(pessoaId, pessoaAlterada);
        });

        assertEquals("Pessoa não encontrada", thrown.getMessage());

        verify(pessoaRepository).findById(pessoaId);
    }

    @Test
    void deletarPessoa_DeveRetornarNoContentQuandoExistir() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        doNothing().when(pessoaRepository).delete(any(Pessoa.class));

        ResponseEntity<Object> resposta = pessoaService.deletarPessoa(1L);

        assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());

        verify(pessoaRepository).delete(pessoa);
    }

    @Test
    void deletarPessoa_DeveLancarExcecaoQuandoNaoExistir() {
        lenient().when(pessoaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> pessoaService.deletarPessoa(99L));
    }

}
