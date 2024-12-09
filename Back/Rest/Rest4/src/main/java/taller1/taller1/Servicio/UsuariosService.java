package taller1.taller1.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taller1.taller1.Dto.UsuariosDTO;
import taller1.taller1.Mapeadores.UsuariosMapper;
import taller1.taller1.Modelo.Usuarios;
import taller1.taller1.Repositorio.UsuariosRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuarioRepository;

    // Listar todos los usuarios
    public List<UsuariosDTO> listarUsuarios() {
        List<Usuarios> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                       .map(UsuariosMapper::toDTO)
                       .collect(Collectors.toList());
    }

    // Actualizar la información de un usuario
    public UsuariosDTO actualizarUsuario(Long id, UsuariosDTO usuarioDTO) {
        Optional<Usuarios> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuarios usuario = usuarioOpt.get();
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setCorreo(usuarioDTO.getCorreo());
            usuario.setTipousuario(usuarioDTO.getTipousuario());  // Cambié 'tipo' por 'tipousuario'
            usuarioRepository.save(usuario);
            return UsuariosMapper.toDTO(usuario);
        }
        return null;
    }
}
