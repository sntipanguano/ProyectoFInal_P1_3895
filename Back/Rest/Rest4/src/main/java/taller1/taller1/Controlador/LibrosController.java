package taller1.taller1.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taller1.taller1.Dto.LibrosDTO;
import taller1.taller1.Servicio.LibrosService;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibrosController {

    @Autowired
    private LibrosService libroService;

    // Endpoint para listar todos los libros
    @GetMapping
    public ResponseEntity<List<LibrosDTO>> listarLibros() {
        List<LibrosDTO> librosDTO = libroService.listarLibros();
        return librosDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(librosDTO);
    }

    // Endpoint para actualizar un libro
    @PutMapping("/{id}")
    public ResponseEntity<LibrosDTO> actualizarLibro(@PathVariable Long id, @RequestBody LibrosDTO libroDTO) {
        Optional<LibrosDTO> libroActualizadoOpt = libroService.actualizarLibro(id, libroDTO);
        if (libroActualizadoOpt.isPresent()) {
            return ResponseEntity.ok(libroActualizadoOpt.get());
        }
        return ResponseEntity.badRequest().body(null);
    }
}
