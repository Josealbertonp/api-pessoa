package com.pessoa.apipessoa.DTOs.request;


import com.pessoa.apipessoa.entity.Pessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class PessoaRequest extends Pessoa {
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
}