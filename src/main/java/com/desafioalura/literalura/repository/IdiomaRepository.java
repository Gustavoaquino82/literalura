package com.desafioalura.literalura.repository;

import com.desafioalura.literalura.model.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdiomaRepository extends JpaRepository<Idioma, Long> {
    List<Idioma> findByName(String name);
}
