package br.com.SistemaDeBiblioteca.service;

import br.com.SistemaDeBiblioteca.model.AutorModel;
import br.com.SistemaDeBiblioteca.model.CategoriaModel;
import br.com.SistemaDeBiblioteca.model.LivroModel;
import br.com.SistemaDeBiblioteca.repository.AutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;
    private AutorModel autorModel = new AutorModel();
    private LivroModel livroModel = new LivroModel();
    private Set<LivroModel> livroModelList = new HashSet<>();
    private Set<CategoriaModel> categoriaModelSet = new HashSet<>();
    private CategoriaModel categoriaModel = new CategoriaModel();

    public void menu(Scanner scanner) {
        while (true) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU DO AUTOR<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println("OPÇÕES DISPONÍVEIS");
            System.out.println("0. Sair");
            System.out.println("1. Criar autor");
            System.out.println("2. Visualizar autor");
            System.out.println("3. Deletar autor");
            System.out.println("4. Editar autor");
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
                    criarAutor(scanner);
                    break;
                case 2:
                    visualizarAutor(scanner);
                    break;
                case 3:
                    deletarAutor(scanner);
                    break;
                case 4:
                    editarAutor(scanner);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    @Transactional
    private void criarAutor(Scanner scanner) {
        System.out.print("Informe o nome do autor: ");
        autorModel.setNome(scanner.nextLine());

        while (true) {
            System.out.print("Informe o título do livro: ");
            livroModel.setTitulo(scanner.nextLine());

            System.out.print("Informe o ISBN (código de identificação do livro): ");
            livroModel.setIsbn(scanner.nextLine());

            while (true) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>CATEGORIAS<<<<<<<<<<<<<<<<<<<<<<<<");
                System.out.println("1. Terror");
                System.out.println("2. Ação");
                System.out.println("3. Aventura");
                System.out.println("4. Comédia");
                System.out.println("5. Romance");
                System.out.println("6. Drama");

                int result = 0;
                boolean validInput = false;

                while (!validInput) {
                    try {
                        result = scanner.nextInt();
                        scanner.nextLine();
                        validInput = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Informe apenas números");
                        scanner.nextLine();
                    }
                }

                switch (result) {
                    case 1:
                        categoriaModel.setNome("Terror");
                        break;
                    case 2:
                        categoriaModel.setNome("Ação");
                        break;
                    case 3:
                        categoriaModel.setNome("Aventura");
                        break;
                    case 4:
                        categoriaModel.setNome("Comédia");
                        break;
                    case 5:
                        categoriaModel.setNome("Romance");
                        break;
                    case 6:
                        categoriaModel.setNome("Drama");
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, escolha um número entre 1 e 6.");
                        continue;
                }
                categoriaModelSet.add(categoriaModel);

                System.out.println("Deseja informar outra categoria (s/n)?");
                String resp = scanner.nextLine().trim().toUpperCase();
                if (resp.equals("N")) {
                    livroModel.setCategorias(categoriaModelSet);
                    break;
                } else if (!resp.equals("S")) {
                    System.out.println("OPÇÃO inválida!");
                }
            }

            livroModelList.add(livroModel);

            System.out.print("Deseja adicionar outro livro ao autor (s/n) ? ");
            String resp = scanner.nextLine().trim().toUpperCase();
            if (resp.equals("N")) {
                break;
            } else if (!resp.equals("S")) {
                System.out.println("OPÇÃO inválida!");
            }
        }

        autorModel.setLivros(livroModelList);
        livroModel.setAutor(autorModel);

        try {
            autorRepository.save(autorModel);
            System.out.println("Autor salvo com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro inesperado ao salvar o autor: " + e.getMessage());
        }
    }


    private void visualizarAutor(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Informe o seu ID: ");
                autorModel.setId(scanner.nextLong());
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.err.println("Erro: O formato do id fornecido é inválido.");
                System.out.println();
            }
        }

        Optional<AutorModel> autorModelOptional = autorRepository.findById(autorModel.getId());
        if (autorModelOptional.isEmpty()) {
            System.out.println("O autor com o id " + autorModel.getId() + " não foi encontrado.");
        } else {
            System.out.println(autorModelOptional);
        }
    }

    @Transactional
    private void deletarAutor(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Informe o seu ID: ");
                autorModel.setId(scanner.nextLong());
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.err.println("Erro: O formato do id fornecido é inválido.");
                System.out.println();
            }
        }

        try {
            autorRepository.deleteById(autorModel.getId());
            System.out.println("Usuário deletado com sucesso!");
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Erro: O autor com o id " + autorModel.getId() + " não foi encontrado.");
        } catch (DataAccessException e) {
            System.err.println("Erro de acesso ao banco de dados ao tentar deletar o autor com o id: " + autorModel.getId());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: O id fornecido é inválido.");
        }

    }

    @Transactional
    private void editarAutor(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Informe o seu ID: ");
                autorModel.setId(scanner.nextLong());
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.err.println("Erro: O formato do id fornecido é inválido.");
                System.out.println();
            }
        }

        Optional<AutorModel> autorModelOptional = autorRepository.findById(autorModel.getId());

        if (autorModelOptional.isEmpty()) {
            System.out.println("O autor com o id " + autorModel.getId() + " não foi encontrado.");
            return;
        } else {
            autorModel = autorModelOptional.get();
        }

        System.out.print("Informe o novo nome do autor: ");
        autorModel.setNome(scanner.nextLine());

        try {
            autorRepository.save(autorModel);
            System.out.println("Autor atualizado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro inesperado ao salvar o autor: " + e.getMessage());
        }
    }

}
