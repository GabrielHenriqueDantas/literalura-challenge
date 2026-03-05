package com.alura.literalura.model;

import com.alura.literalura.dto.DadosLivro;
import jakarta.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String idioma;

    private Double downloadCount;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Autor autor;

    public Livro() {}

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();

        // Ajuste aqui conforme o nome no seu Record DadosLivro
        this.idioma = (dadosLivro.idiomas() != null && !dadosLivro.idiomas().isEmpty())
                ? dadosLivro.idiomas().get(0)
                : "Desconhecido";

        // Se no Record estiver 'numeroDownloads', use dadosLivro.numeroDownloads()
        this.downloadCount = Optional.ofNullable(dadosLivro.numeroDownloads()).orElse(0.0);

        // Se no Record estiver 'autores', use dadosLivro.autores()
        if (dadosLivro.autores() != null && !dadosLivro.autores().isEmpty()) {
            this.autor = new Autor(dadosLivro.autores().get(0));
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public Double getDownloadCount() { return downloadCount; }
    public void setDownloadCount(Double downloadCount) { this.downloadCount = downloadCount; }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    @Override
    public String toString() {
        return "\n----------- LIVRO -----------" +
                "\nTítulo: " + titulo +
                "\nAutor: " + (autor != null ? autor.getNome() : "Desconhecido") +
                "\nIdioma: " + idioma +
                "\nDownloads: " + downloadCount +
                "\n-----------------------------";
    }
}