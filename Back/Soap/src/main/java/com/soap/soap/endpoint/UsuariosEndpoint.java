package com.soap.soap.endpoint;

import com.soap.soap.converter.UsuariosConverter;
import com.soap.soap.gen.*;
import com.soap.soap.model.UsuariosModel;
import com.soap.soap.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UsuariosEndpoint {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private UsuariosConverter usuariosConverter;

    // Endpoint para registrar un usuario
    @PayloadRoot(namespace = "http://www.soap.com/", localPart = "registrarUsuarioRequest")
    @ResponsePayload
    public RegistrarUsuarioResponse registrarUsuario(@RequestPayload RegistrarUsuarioRequest request) {
        RegistrarUsuarioResponse response = new RegistrarUsuarioResponse();

        // Crear un nuevo usuario
        UsuariosModel usuario = new UsuariosModel();
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setTipousuario(request.getTipoUsuario());
        usuario.setEstadousuario("activo");  // Por defecto, el usuario está activo

        // Guardar el usuario en la base de datos
        usuariosRepository.save(usuario);

        // Configurar respuesta con el ID del usuario generado
        response.setIdUsuario(usuario.getId());
        response.setEstado("success");
        return response;
    }

    // Endpoint para suspender un usuario
    @PayloadRoot(namespace = "http://www.soap.com/", localPart = "suspenderUsuarioRequest")
    @ResponsePayload
    public SuspenderUsuarioResponse suspenderUsuario(@RequestPayload SuspenderUsuarioRequest request) {
        SuspenderUsuarioResponse response = new SuspenderUsuarioResponse();

        // Buscar al usuario por ID
        UsuariosModel usuario = usuariosRepository.findById(request.getIdUsuario()).orElse(null);
        if (usuario == null) {
            response.setEstado("error");
            response.setMotivo("Usuario no encontrado.");
            return response;
        }

        // Suspender el usuario y actualizar su estado
        usuario.setEstadousuario("suspendido");
        usuariosRepository.save(usuario);

        // Configurar respuesta
        response.setEstado("success");
        response.setMotivo(request.getMotivo());
        return response;
    }
}
