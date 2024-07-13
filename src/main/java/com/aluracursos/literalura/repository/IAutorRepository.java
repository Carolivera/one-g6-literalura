package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.añoNacimiento <= :anioBusqueda AND a.añoFallecimiento >= :anioBusqueda " +
            "ORDER BY a.añoNacimiento ASC")
    List<Autor> buscarAutorPorAnio(int anioBusqueda);
}
