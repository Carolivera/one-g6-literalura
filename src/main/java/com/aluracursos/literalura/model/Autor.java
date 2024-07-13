package com.aluracursos.literalura.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Autor")
@Table(name = "autores")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    Integer añoNacimiento;
    Integer añoFallecimiento;

    public Autor(DatosAutor datosAutor){
        this.name = datosAutor.nombre();
        this.añoNacimiento = datosAutor.añoNacimiento();
        this.añoFallecimiento = datosAutor.añoFallecimiento();
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", añoNacimiento=" + añoNacimiento +
                ", añoFallecimiento=" + añoFallecimiento +
                '}';
    }
}
