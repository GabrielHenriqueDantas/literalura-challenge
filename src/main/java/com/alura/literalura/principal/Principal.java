package com.alura.literalura.principal;

import com.alura.literalura.dto.DadosLivro;
import com.alura.literalura.dto.DadosResposta;
import com.alura.literalura.model.Livro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LivroRepository;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConverteDados;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    // ESTE CONSTRUTOR É OBRIGATÓRIO PARA TIRAR O VERMELHO DOS REPOSITORIES
    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                1 - buscar livro pelo título (Web)
                2 - listar livros registrados
                3 - listar autores registrados
                4 - listar autores vivos em um determinado ano
                5 - listar livros em um determinado idioma
                0 - sair
                """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1 -> buscarLivro(); // Nome corrigido aqui
                case 2 -> listarLivrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosNoAno();
                case 5 -> listarLivrosPorIdioma();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private void buscarLivro() {
        System.out.println("Digite o nome do livro para busca:");
        var nomeLivro = leitura.nextLine();
        var json = consumoApi.obterDados(ENDERECO + nomeLivro.replace(" ", "+"));
        DadosResposta dadosResposta = conversor.obterDados(json, DadosResposta.class);

        if (dadosResposta.results() != null && !dadosResposta.results().isEmpty()) {
            DadosLivro dadosLivro = dadosResposta.results().get(0);
            Livro livro = new Livro(dadosLivro);

            Optional<Livro> livroExistente = livroRepository.findByTituloContainingIgnoreCase(livro.getTitulo());

            if (livroExistente.isPresent()) {
                System.out.println("Este livro já está cadastrado no banco de dados!");
            } else {
                try {
                    livroRepository.save(livro);
                    System.out.println("\n--- LIVRO SALVO COM SUCESSO ---");
                    System.out.println(livro);
                } catch (Exception e) {
                    System.out.println("Erro ao salvar: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Livro não encontrado na API.");
        }
    } // ESSA CHAVE ESTAVA FALTANDO!

    private void listarAutoresRegistrados() {
        var autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Digite o idioma (es, en, fr, pt):");
        var idioma = leitura.nextLine().toLowerCase();
        var livros = livroRepository.findByIdiomaContains(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            livros.forEach(System.out::println);
        }
    }

    private void listarLivrosRegistrados() {
        var livros = livroRepository.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutoresVivosNoAno() {
        System.out.println("Digite o ano:");
        var ano = leitura.nextInt();
        var autores = autorRepository.buscarAutoresVivosNoAno(ano);
        autores.forEach(System.out::println);
    }
}