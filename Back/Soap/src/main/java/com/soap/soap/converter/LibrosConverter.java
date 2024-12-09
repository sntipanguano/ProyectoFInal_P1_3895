package com.soap.soap.converter;

import com.soap.soap.gen.Libros;  // Asumiendo que tienes esta clase generada
import com.soap.soap.model.LibrosModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LibrosConverter {

    // Convierte un objeto Libro a un modelo LibrosModel
    public LibrosModel convertLibroToLibrosModel(Libros libro) {
        LibrosModel librosModel = new LibrosModel();
        librosModel.setId(libro.getId());
        librosModel.setTitulo(libro.getTitulo());
        librosModel.setAutor(libro.getAutor());
        librosModel.setCategoria(libro.getCategoria());
        librosModel.setIsbn(libro.getIsbn());
        librosModel.setCantidad(libro.getCantidad());
        librosModel.setDisponibles(libro.getDisponibles());
        return librosModel;
    }

    // Convierte un modelo LibrosModel a un objeto Libro
    public Libros convertLibrosModelToLibro(LibrosModel librosModel) {
        Libros libro = new Libros();
        libro.setId(librosModel.getId());
        libro.setTitulo(librosModel.getTitulo());
        libro.setAutor(librosModel.getAutor());
        libro.setCategoria(librosModel.getCategoria());
        libro.setIsbn(librosModel.getIsbn());
        libro.setCantidad(librosModel.getCantidad());
        libro.setDisponibles(librosModel.getDisponibles());
        return libro;
    }

    // Convierte una lista de objetos Libro a una lista de modelos LibrosModel
    public List<LibrosModel> convertLibrosToLibrosModels(List<Libros> libros) {
        List<LibrosModel> librosModels = new ArrayList<>();
        for (Libros libro : libros) {
            librosModels.add(convertLibroToLibrosModel(libro));
        }
        return librosModels;
    }

    // Convierte una lista de modelos LibrosModel a una lista de objetos Libro
    public List<Libros> convertLibrosModelsToLibros(List<LibrosModel> librosModels) {
        List<Libros> libros = new ArrayList<>();
        for (LibrosModel librosModel : librosModels) {
            libros.add(convertLibrosModelToLibro(librosModel));
        }
        return libros;
    }
}
