package com.desafioalura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroData(
        @JsonAlias("id") Integer idBook,
        String title,
        List<AutorData> authors,
        @JsonAlias("languages")List<String> languages,
        @JsonAlias("download_count") Integer downloadCount
) {
}
