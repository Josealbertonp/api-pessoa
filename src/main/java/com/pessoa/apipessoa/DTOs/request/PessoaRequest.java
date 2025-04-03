package com.pessoa.apipessoa.DTOs.request;


import com.pessoa.apipessoa.entity.Pessoa;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class PessoaRequest extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-incremento no banco de dados
    private Long id;

    @NotEmpty(message = "Nome não pode ser vazio")
    @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
    private String nome;

    @NotEmpty(message = "CPF não pode ser vazio")
    @Size(min = 11, max = 11, message = "CPF deve ter 11 caracteres")
    private String cpf;

    @NotEmpty(message = "Data de nascimento não pode ser vazia")
    private LocalDate dataNascimento;

    @Email(message = "Email deve ser válido")
    @NotEmpty(message = "Email não pode ser vazio")
    private String email;
}