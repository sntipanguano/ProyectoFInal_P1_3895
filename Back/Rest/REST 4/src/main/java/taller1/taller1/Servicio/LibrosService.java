package taller1.taller1.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taller1.taller1.Dto.LibrosDTO;
import taller1.taller1.Mapeadores.LibrosMapper;
import taller1.taller1.Modelo.Libros;
import taller1.taller1.Repositorio.LibrosRepository;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibrosService {

    @Autowired
    private LibrosRepository libroRepository;

    // Listar todos los libros
    public List<LibrosDTO> listarLibros() {
        List<Libros> libros = libroRepository.findAll();
        return libros.stream()
                     .map(LibrosMapper::toDTO)
                     .collect(Collectors.toList());
    }

    // Actualizar información de un libro
    public Optional<LibrosDTO> actualizarLibro(Long id, LibrosDTO libroDTO) {
        Optional<Libros> libroOpt = libroRepository.findById(id);
        if (libroOpt.isPresent()) {
            Libros libro = libroOpt.get();
            libro.setTitulo(libroDTO.getTitulo());
            libro.setAutor(libroDTO.getAutor());
            libro.setCategoria(libroDTO.getCategoria());
            libro.setDisponibles(libroDTO.getDisponibles());
            libroRepository.save(libro);
            return Optional.of(LibrosMapper.toDTO(libro));
        }
        return Optional.empty();
    }
}
