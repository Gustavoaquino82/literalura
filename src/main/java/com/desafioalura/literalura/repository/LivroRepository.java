package com.desafioalura.literalura.repository;

import com.desafioalura.literalura.model.Livro;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    @JsonIgnoreProperties(ignoreUnknown = true)
    boolean existsByTitle(String title);

    @Override
    Optional<Livro> findById(Long id);
    List<Livro> findByLanguagesContaining(String language);
}
