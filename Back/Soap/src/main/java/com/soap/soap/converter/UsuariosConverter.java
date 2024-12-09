package com.soap.soap.converter;

import com.soap.soap.gen.Usuarios;  // Asumiendo que tienes esta clase generada
import com.soap.soap.model.UsuariosModel;
import org.springframework.stereotype.Component;

@Component
public class UsuariosConverter {

    // Convierte un objeto Usuario a un modelo UsuariosModel
    public UsuariosModel convertUsuarioToUsuariosModel(Usuarios usuario) {
        UsuariosModel usuariosModel = new UsuariosModel();
        usuariosModel.setId(usuario.getId());
        usuariosModel.setNombre(usuario.getNombre());
        usuariosModel.setCorreo(usuario.getCorreo());
        usuariosModel.setTipousuario(usuario.getTipousuario());
        usuariosModel.setEstadousuario(usuario.getEstadousuario());
        usuariosModel.setPassword(usuario.getPassword());
        return usuariosModel;
    }

    // Convierte un modelo UsuariosModel a un objeto Usuario
    public Usuarios convertUsuariosModelToUsuario(UsuariosModel usuariosModel) {
        Usuarios usuario = new Usuarios();
        usuario.setId(usuariosModel.getId());
        usuario.setNombre(usuariosModel.getNombre());
        usuario.setCorreo(usuariosModel.getCorreo());
        usuario.setTipousuario(usuariosModel.getTipousuario());
        usuario.setEstadousuario(usuariosModel.getEstadousuario());
        usuario.setPassword(usuariosModel.getPassword());
        return usuario;
    }
}
