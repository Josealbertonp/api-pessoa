package com.pessoa.apipessoa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "pessoa")
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", length = 50)
    private String nome;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "dataNascimento")
    private LocalDate dataNascimento;

    @Column(name = "email", length = 30)
    private String email;

    public Pessoa(long id, String nome, String cpf, LocalDate dataNascimento, String email) {
    }
}
