package com.desafioalura.literalura.repository;

import com.desafioalura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {
    @Query("SELECT a FROM Autor a WHERE a.birthYear <= :year AND (a.deathYear IS NULL OR a.deathYear >= :year)")
    List<Autor> findAutoresByYear(@Param("year")int year);

    Optional<Autor> findByNameAndBirthYearAndDeathYear(String name, int birthYear, int deathYear);
}
