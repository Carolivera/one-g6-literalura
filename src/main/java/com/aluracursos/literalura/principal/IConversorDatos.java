package com.aluracursos.literalura.principal;

public interface IConversorDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
    }

