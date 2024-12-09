package taller1.taller1.Mapeadores;

import taller1.taller1.Dto.UsuariosDTO;
import taller1.taller1.Modelo.Usuarios;

public class UsuariosMapper {

    public static UsuariosDTO toDTO(Usuarios usuario) {
        UsuariosDTO dto = new UsuariosDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setTipousuario(usuario.getTipousuario());  // Cambié 'tipo' por 'tipousuario'
        return dto;
    }

    public static Usuarios toEntity(UsuariosDTO dto) {
        Usuarios usuario = new Usuarios();
        usuario.setId(dto.getId());
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setTipousuario(dto.getTipousuario());  // Cambié 'tipo' por 'tipousuario'
        return usuario;
    }
}
