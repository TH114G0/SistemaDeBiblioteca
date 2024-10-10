package br.com.SistemaDeBiblioteca.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "autor")
@Data
public class AutorModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull(message = "O nome não pode ser nulo")
    @NotEmpty(message = "O nome não pode ser vazio")
    private String nome;

    // Um autor pode ter muitos livros
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private Set<LivroModel> livros;

    public AutorModel() {}

    public AutorModel(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutorModel that = (AutorModel) o;
        return Objects.equals(id, that.id); // Comparar apenas pelo ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Usar apenas o ID para o hash
    }


    @Override
    public String toString() {
        return "AutorModel{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
