package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.DatosAutor;
import com.aluracursos.literalura.model.DatosLibro;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.IAutorRepository;
import com.aluracursos.literalura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConversorDatos conversor = new ConversorDatos();
    private List<DatosLibro> datosLibros = new ArrayList<>();
    private final String URL_API = "https://gutendex.com/books/";
    private List<Libro> libros;
    private List<Autor> autores;
    private Optional<Libro> libroBuscado;
    private Optional<Autor> autorbuscado;
    private ILibroRepository libroRepository;
    private IAutorRepository autorRepository;
    public Principal(ILibroRepository libroRepository, IAutorRepository autorRepository){
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository; }


    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Elija una opción a través de su número :)
                                        
                    1 - Buscar libro por título 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                                             
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibros();
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    mostrarAutoresBuscados();
                    break;
                case 4:
                    autoresVivos();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void mostrarLibrosPorIdioma() {
        System.out.println("""
                Elija el lenguaje que desea consultar:
                1- Español
                2- Inglés
                        """);
        try{
            var lenguaje = teclado.nextInt();
            teclado.nextLine();

            switch (lenguaje){
                case 1:
                    libros = libroRepository.findByLenguaje("es");
                    break;
                case 2:
                    libros = libroRepository.findByLenguaje("en");
                    break;
                default:
                    System.out.println("Ingrese una opción válida.");
            }
            libros.stream().forEach(System.out::println);

        }catch(Exception e){
            System.out.println("Ingrese una opción válida.");
        }

    }

    private void autoresVivos() {
        System.out.println("Ingrese el año por el cual quiere buscar: ");
        var anioBusqueda = teclado.nextInt();
        teclado.nextLine();

        autores = autorRepository.buscarAutorPorAnio(anioBusqueda);
        if(autores!=null){
            autores.stream().forEach(System.out::println);
        }else{
            System.out.println("No se encontró ningún autor vivo en la fecha indicada.");
        }
    }

    private void mostrarAutoresBuscados() {
        autores = autorRepository.findAll();
        if (autores != null) {
            autores.forEach(a-> System.out.println("""
                        Nombre: %s
                        Año de nacimiento: %s.                                        
                        """.formatted(a.getName(), a.getAñoNacimiento())));

        } else {
            System.out.println("No hay autores guardados. Intente con opción 1");
        }

    }

    private void buscarLibros() {
        DatosLibro datos = buscarLibroPorTitulo();
        try {
            Libro libro = new Libro(datos.resultados().get(0));
            Autor autor = new Autor(datos.resultados().get(0).autorList().get(0));
            System.out.println("""
                    Libro: 
                    título: %s,
                    autor: %s,
                    lenguaje: %s,
                    n.° descargas: %s
                                        
                    """.formatted(libro.getTitulo(), libro.getAutor(),
                    libro.getLenguaje(), libro.getNumeroDescargas()));
            libroRepository.save(libro);
            autorRepository.save(autor);
            System.out.println(libro);
        } catch (Exception e) {
            System.out.println("No se encontró un libro con ese título.");
        }

    }

    private DatosLibro buscarLibroPorTitulo() {
        System.out.println("Escriba el título del libro que desea buscar:");
        var tituloLibro = teclado.nextLine().toLowerCase().replace(" ", "%20");
        var json = consumoAPI.obtenerDatos(URL_API + "?search=" + tituloLibro);
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
        return datos;
    }

    private void mostrarLibrosBuscados() {
        libros = libroRepository.findAll();
        if (libros != null) {
            libros.stream()
                    .sorted(Comparator.comparing(Libro::getLenguaje))
                    .forEach(System.out::println);
        } else {
            System.out.println("No hay libros guardados. Intente con opción 1");
        }

    }
}
