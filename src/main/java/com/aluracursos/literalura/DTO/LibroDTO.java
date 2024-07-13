package com.aluracursos.literalura.DTO;

import com.aluracursos.literalura.model.Lenguaje;

public record LibroDTO(
        Long id,
        String titulo,
        Lenguaje lenguaje,
        Integer numeroDescargas
) {
}
