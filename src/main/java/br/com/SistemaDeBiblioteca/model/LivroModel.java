package br.com.SistemaDeBiblioteca.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

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

    // Muitos livros podem ser escritos por um único autor
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private AutorModel autor;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "livro_categoria",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<CategoriaModel> categorias;
    public LivroModel() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Se for o mesmo objeto
        if (o == null || getClass() != o.getClass()) return false; // Verifica se é nulo ou se é de outra classe
        LivroModel that = (LivroModel) o; // Converte para LivroModel
        return Objects.equals(id, that.id); // Compara apenas pelo ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "LivroModel{" +
                "titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}