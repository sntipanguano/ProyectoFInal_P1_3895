package taller1.taller1.Mapeadores;

import taller1.taller1.Dto.LibrosDTO;
import taller1.taller1.Modelo.Libros;

public class LibrosMapper {

    public static LibrosDTO toDTO(Libros libro) {
        LibrosDTO dto = new LibrosDTO();
        dto.setId(libro.getId());
        dto.setTitulo(libro.getTitulo());
        dto.setAutor(libro.getAutor());
        dto.setCategoria(libro.getCategoria());
        dto.setDisponibles(libro.getDisponibles());
        dto.setIsbn(libro.getIsbn());
        dto.setCantidad(libro.getCantidad());
        dto.setFecharegistro(libro.getFecharegistro());
        return dto;
    }

    public static Libros toEntity(LibrosDTO dto) {
        Libros libro = new Libros();
        libro.setId(dto.getId());
        libro.setTitulo(dto.getTitulo());
        libro.setAutor(dto.getAutor());
        libro.setCategoria(dto.getCategoria());
        libro.setDisponibles(dto.getDisponibles());
        libro.setIsbn(dto.getIsbn());
        libro.setCantidad(dto.getCantidad());
        dto.setFecharegistro(libro.getFecharegistro());
        return libro;
    }
}
