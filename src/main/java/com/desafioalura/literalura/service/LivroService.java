package com.desafioalura.literalura.service;

import com.desafioalura.literalura.model.Autor;
import com.desafioalura.literalura.model.Idioma;
import com.desafioalura.literalura.model.Livro;
import com.desafioalura.literalura.repository.AutorRepository;
import com.desafioalura.literalura.repository.LivroRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;


    private final ConsumoApi consumoApi= new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "http://gutendex.com/books/";

    public void saveLivro(String nomeLivro){
        String searchUrl = ENDERECO+"?search="+ nomeLivro.replace(" ", "%20");
        boolean livroSalvo = false;

        do {
            String json = consumoApi.obterDados(searchUrl);
            ConsumoApiResponse consumoApiResponse = conversor.obterDados(json, ConsumoApiResponse.class);

            if (consumoApiResponse != null && consumoApiResponse.getResults() !=null) {
                for (Livro livro : consumoApiResponse.getResults()){
                    if (livroSalvo) {
                        break;
                    }
                    if (!livroRepository.existsByTitle(livro.getTitle())) {
                        List<Autor> autores = new ArrayList<>();
                        for (Autor autor : livro.getAuthors()) {
                            Autor autorexistente = autorRepository.findByNameAndBirthYearAndDeathYear(
                                    autor.getName(),autor.getBirthYear(), autor.getDeathYear()
                            ).orElse(autor);
                            autores.add(autorexistente);
                        }
                        autorRepository.saveAll(autores);
                        livro.setAuthors(autores);

                        List<Idioma> idiomas = new ArrayList<>();
                        for (Idioma idioma :livro.getIdiomas()) {
                            Idioma idiomaExistente = new Idioma(idioma.getName());
                            idiomas.add(idiomaExistente);
                        }
                        livro.setIdiomas(idiomas);

                        livroRepository.save(livro);
                        livroSalvo = true;
                    }
                }
                searchUrl = consumoApiResponse.getNext();
            } else {
                searchUrl = null;
            }
        } while (searchUrl != null && !livroSalvo);
    }
    public List<Livro> listBooks(){
        return livroRepository.findAll();
    }
    @Transactional(readOnly = true)
    public List<Livro> listBookByLanguage(String language){
        return livroRepository.findByLanguagesContaining(language);
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ConsumoApiResponse{
        private int count;
        private List<Livro> results;
        private String next;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<Livro> getResults() {
            return results;
        }

        public void setResults(List<Livro> results) {
            this.results = results;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }
    }
}
