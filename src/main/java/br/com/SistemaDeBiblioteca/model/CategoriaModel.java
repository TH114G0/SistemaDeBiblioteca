package br.com.SistemaDeBiblioteca.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Entity
@Table(name = "categoria")
@Data
public class CategoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToMany(mappedBy = "categorias")
    private Set<LivroModel> livros;
    public CategoriaModel() {
    }

    @Override
    public String toString() {
        return "CategoriaModel{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
