package taller1.taller1.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taller1.taller1.Dto.LibrosDTO;
import taller1.taller1.Servicio.LibrosService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class LibrosController {

    @Autowired
    private LibrosService libroService;

    // Endpoint para consultar libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<LibrosDTO> consultarLibroPorId(@PathVariable Long id) {
        Optional<LibrosDTO> libroDTO = libroService.consultarLibroPorId(id);
        return libroDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // Endpoint para consultar libro por título
    @GetMapping
    public ResponseEntity<LibrosDTO> consultarLibroPorTitulo(@RequestParam String titulo) {
        Optional<LibrosDTO> libroDTO = libroService.consultarLibroPorTitulo(titulo);
        return libroDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // Endpoint para consultar libros por categoría
    @GetMapping("/categoria")
    public ResponseEntity<List<LibrosDTO>> consultarLibrosPorCategoria(@RequestParam String categoria) {
        List<LibrosDTO> librosDTO = libroService.consultarLibrosPorCategoria(categoria);
        return librosDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(librosDTO);
    }
}
