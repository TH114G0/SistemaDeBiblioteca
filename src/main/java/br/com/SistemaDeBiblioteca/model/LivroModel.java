package br.com.SistemaDeBiblioteca.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "livro")
@Data
public class LivroModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O título do livro não pode ser nulo")
    @NotEmpty(message = "O título do livro não pode ser vazio")
    private String titulo;

    @NotNull(message = "O código de identificação do livro não pode ser nulo")
    @NotEmpty(message = "O código de identificação do livro não pode ser vazio")
    private String isbn;

    public LivroModel() {}
}