package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibroRtdo(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autorList,
        @JsonAlias("languages") List<String> lenguaje,
        @JsonAlias("download_count") Integer numeroDescargas) {
}
