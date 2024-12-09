package taller1.taller1.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taller1.taller1.Dto.PrestamosDTO;
import taller1.taller1.Servicio.PrestamosService;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class PrestamosController {

    @Autowired
    private PrestamosService prestamoService;

    // Endpoint para consultar el historial de préstamos de un usuario
    @GetMapping("/{id}/prestamos")
    public ResponseEntity<List<PrestamosDTO>> consultarHistorialPrestamos(@PathVariable Long id) {
        List<PrestamosDTO> prestamosDTO = prestamoService.consultarHistorialPrestamos(id);
        return prestamosDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(prestamosDTO);
    }
}
