package com.desafioalura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> subjects;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "livros_idiomas",
                joinColumns = @JoinColumn(name = "livros_id"),
                inverseJoinColumns = @JoinColumn(name = "idiomas_id"))
    private List<Idioma> idiomas = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "livros_autores",
    joinColumns = @JoinColumn(name = "livros_id"),
    inverseJoinColumns = @JoinColumn(name = "autores_id"))
    private List<Autor> authors = new ArrayList<>();

    @JsonProperty("languages")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> languages = new ArrayList<>();

    @JsonProperty("download_count")
    private Integer downloadCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    public List<Autor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Autor> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", title='" + title + '\'' +
                ", subjects=" + subjects +
                ", idiomas=" + idiomas +
                ", authors=" + authors +
                ", languages=" + languages +
                ", downloadCount=" + downloadCount +
                '}';
    }
}
