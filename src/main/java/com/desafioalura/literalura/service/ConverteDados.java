package com.desafioalura.literalura.service;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados {
    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> T obterDados(String json, Class<T> classe){
        try{
            return objectMapper.readValue(json,classe);
        } catch (Exception e){
            throw new RuntimeException("Erro ao converter JSON", e);
        }
    }
}
