package com.alura.literalura.repository;

import com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    // Este método permite buscar se o título existe, ignorando maiúsculas/minúsculas
    Optional<Livro> findByTituloContainingIgnoreCase(String titulo);

    // Este método busca livros por idioma (ajuste o nome do campo se for 'idioma' no singular)
    List<Livro> findByIdiomaContains(String idioma);
}