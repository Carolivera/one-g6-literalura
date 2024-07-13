package com.aluracursos.literalura.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(name = "Libro")
@Table(name = "libros")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String autor;
    private String lenguaje;
    private Integer numeroDescargas;

    public Libro(DatosLibroRtdo datosLibro){
        this.titulo = datosLibro.titulo();
        this.autor = datosLibro.autorList().get(0).nombre();
        this.lenguaje = datosLibro.lenguaje().get(0);
        this.numeroDescargas = datosLibro.numeroDescargas();
    }
    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", lenguaje=" + lenguaje +
                ", numeroDescargas=" + numeroDescargas +
                '}';
    }
}
