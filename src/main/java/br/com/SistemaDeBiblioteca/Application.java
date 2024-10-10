package br.com.SistemaDeBiblioteca;

import br.com.SistemaDeBiblioteca.service.AutorService;
import br.com.SistemaDeBiblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private AutorService autorService;
    @Autowired
    private LivroService livroService;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU PRINCIPAL<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println("OPÇÕES DISPONÍVEIS");
            System.out.println("0. Sair");
            System.out.println("1. Autor");
            System.out.println("2. Livro");
            int escolha = 0;
            try {
                escolha = scanner.nextInt();
                scanner.nextLine();
            }catch (IllegalArgumentException e) {
                System.err.println("Informe apenas os NÚMEROS solicitados." + e);
            }
            switch (escolha) {
                case 0:
                    System.out.println("Finalizando aplicação!");
                    return;
                case 1:
                    autorService.menu(scanner);
                    break;
                case 2:
                    livroService.menu(scanner);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}