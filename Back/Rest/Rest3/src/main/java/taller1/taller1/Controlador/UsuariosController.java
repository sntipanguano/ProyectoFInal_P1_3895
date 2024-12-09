package taller1.taller1.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taller1.taller1.Dto.UsuariosDTO;
import taller1.taller1.Servicio.UsuariosService;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuariosController {

    @Autowired
    private UsuariosService usuarioService;

    // Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuariosDTO>> listarUsuarios() {
        List<UsuariosDTO> usuariosDTO = usuarioService.listarUsuarios();
        return usuariosDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(usuariosDTO);
    }

    // Actualizar la información de un usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsuariosDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuariosDTO usuarioDTO) {
        UsuariosDTO usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioDTO);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok(usuarioActualizado);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
