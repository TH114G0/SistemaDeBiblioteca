package br.com.SistemaDeBiblioteca.repository;

import br.com.SistemaDeBiblioteca.model.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaFactory extends JpaRepository<CategoriaModel, Long> { }