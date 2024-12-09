package taller1.taller1.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taller1.taller1.Dto.LibrosDTO;
import taller1.taller1.Mapeadores.LibrosMapper;
import taller1.taller1.Modelo.Libros;
import taller1.taller1.Repositorio.LibrosRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibrosService {

    @Autowired
    private LibrosRepository librosRepository;

    // Cambiado para devolver un Optional<LibroDTO>
    public Optional<LibrosDTO> consultarLibroPorId(Long id) {
        Optional<Libros> libro = librosRepository.findById(id);
        return libro.map(LibrosMapper::toDTO);
    }

    // Cambiado para devolver un Optional<LibroDTO>
    public Optional<LibrosDTO> consultarLibroPorTitulo(String titulo) {
        Optional<Libros> libro = librosRepository.findByTitulo(titulo);
        return libro.map(LibrosMapper::toDTO);
    }

    // No necesita cambios ya que devuelve una lista de DTOs
    public List<LibrosDTO> consultarLibrosPorCategoria(String categoria) {
        List<Libros> libros = librosRepository.findByCategoria(categoria);
        return libros.stream()
                     .map(LibrosMapper::toDTO)
                     .collect(Collectors.toList());
    }
}
