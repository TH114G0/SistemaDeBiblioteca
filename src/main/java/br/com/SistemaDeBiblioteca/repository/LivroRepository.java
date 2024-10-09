package br.com.SistemaDeBiblioteca.repository;

import br.com.SistemaDeBiblioteca.model.LivroModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<LivroModel, Long> {}