package br.com.SistemaDeBiblioteca.service;

import br.com.SistemaDeBiblioteca.model.LivroModel;
import br.com.SistemaDeBiblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;
    private LivroModel livroModel = new LivroModel();

    public void menu(Scanner scanner) {
        while (true) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU DO LIVRO<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println("OPÇÕES DISPONÍVEIS");
            System.out.println("0. Sair");
            System.out.println("1. Editar livro");
            System.out.println("2. Deletar livro");
            System.out.println("3. Visualizar livro");
            int escolha = 0;
            try {
                escolha = scanner.nextInt();
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.err.println("Informe apenas os NÚMEROS solicitados." + e);
            }
            switch (escolha) {
                case 0:
                    return;
                case 1:
                    editarLivro(scanner);
                    break;
                case 2:
                    deletarLivro(scanner);
                    break;
                case 3:
                    visualizarLivro(scanner);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void editarLivro(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Informe o seu ID: ");
                livroModel.setId(scanner.nextLong());
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.err.println("Erro: O formato do id fornecido é inválido.");
                System.out.println();
            }
        }

        Optional<LivroModel> livroModelOptional = livroRepository.findById(livroModel.getId());

        if (livroModelOptional.isEmpty()) {
            System.out.println("O autor com o id " + livroModel.getId() + " não foi encontrado.");
            return;
        } else {
            livroModel = livroModelOptional.get();
        }

        System.out.print("Informe o novo título do livro: ");
        livroModel.setTitulo(scanner.nextLine());
        System.out.print("Informe o novo isbn(código de identificação do livro): ");
        livroModel.setIsbn(scanner.nextLine());

        try {
            livroRepository.save(livroModel);
        } catch (Exception e) {
            System.err.println("Erro inesperado ao salvar o livro: " + e.getMessage());
        }
    }

    private void deletarLivro(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Informe o seu ID: ");
                livroModel.setId(scanner.nextLong());
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.err.println("Erro: O formato do id fornecido é inválido.");
                System.out.println();
            }
        }

        try {
            livroRepository.deleteById(livroModel.getId());
            System.out.println("Livro deletado com sucesso!");
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Erro: O livro com o id " + livroModel.getId() + " não foi encontrado.");
        } catch (DataAccessException e) {
            System.err.println("Erro de acesso ao banco de dados ao tentar deletar o livro com o id: " + livroModel.getId());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: O id fornecido é inválido.");
        }
    }

    private void visualizarLivro(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Informe o seu ID: ");
                livroModel.setId(scanner.nextLong());
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.err.println("Erro: O formato do id fornecido é inválido.");
                System.out.println();
            }
        }
        Optional<LivroModel> livroModelOptional = livroRepository.findById(livroModel.getId());
        if (livroModelOptional.isEmpty()) {
            System.out.println("O autor com o id " + livroModel.getId() + " não foi encontrado.");
        } else {
            System.out.println(livroModelOptional);
        }
    }
}
