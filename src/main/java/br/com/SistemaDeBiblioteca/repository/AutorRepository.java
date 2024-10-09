package br.com.SistemaDeBiblioteca.repository;

import br.com.SistemaDeBiblioteca.model.AutorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<AutorModel, Long> { }