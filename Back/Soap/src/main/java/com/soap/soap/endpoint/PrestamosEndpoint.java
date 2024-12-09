package com.soap.soap.endpoint;

import com.soap.soap.converter.PrestamosConverter;
import com.soap.soap.gen.*;
import com.soap.soap.model.PrestamosModel;
import com.soap.soap.repository.PrestamosRepository;
import com.soap.soap.repository.LibrosRepository;
import com.soap.soap.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigDecimal;

@Endpoint
public class PrestamosEndpoint {

    @Autowired
    private PrestamosRepository prestamosRepository;

    @Autowired
    private LibrosRepository librosRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PrestamosConverter prestamosConverter;

    // Endpoint para registrar un préstamo
    @PayloadRoot(namespace = "http://www.soap.com/", localPart = "registrarPrestamoRequest")
    @ResponsePayload
    public RegistrarPrestamoResponse registrarPrestamo(@RequestPayload RegistrarPrestamoRequest request) {
        RegistrarPrestamoResponse response = new RegistrarPrestamoResponse();
        int idUsuario = request.getIdUsuario();
        int idLibro = request.getIdLibro();

        // Verificar disponibilidad del libro
        if (librosRepository.findById(idLibro).get().getDisponibles() > 0) {
            // Crear registro de préstamo
            PrestamosModel prestamo = new PrestamosModel();
            prestamo.setIdusuario(idUsuario);
            prestamo.setIdlibro(idLibro);
            prestamo.setEstadoprestamo("activo");

            // Actualizar el libro como prestado
            librosRepository.findById(idLibro).ifPresent(libro -> {
                libro.setDisponibles(libro.getDisponibles() - 1);
                librosRepository.save(libro);
            });

            // Guardar el préstamo
            prestamosRepository.save(prestamo);

            // Configurar respuesta
            response.setIdPrestamo(prestamo.getId());
            response.setEstado("activo");
        } else {
            response.setEstado("error");
        }

        return response;
    }

    // Endpoint para registrar una devolución
    @PayloadRoot(namespace = "http://www.soap.com/", localPart = "registrarDevolucionRequest")
    @ResponsePayload
    public RegistrarDevolucionResponse registrarDevolucion(@RequestPayload RegistrarDevolucionRequest request) {
        RegistrarDevolucionResponse response = new RegistrarDevolucionResponse();
        int idPrestamo = request.getIdPrestamo();

        // Buscar el préstamo y el libro
        PrestamosModel prestamo = prestamosRepository.findById(idPrestamo).orElse(null);
        if (prestamo != null && prestamo.getEstadoprestamo().equals("activo")) {
            // Calcular multa si aplica
            BigDecimal multa = BigDecimal.ZERO;

            // Actualizar el préstamo
            prestamo.setEstadoprestamo("devuelto");
            prestamo.setMulta(multa);
            prestamosRepository.save(prestamo);

            // Actualizar el inventario del libro
            librosRepository.findById(prestamo.getIdlibro()).ifPresent(libro -> {
                libro.setDisponibles(libro.getDisponibles() + 1);
                librosRepository.save(libro);
            });

            // Configurar respuesta
            response.setIdPrestamo(prestamo.getId());
            response.setEstado("devuelto");
            response.setMulta(multa);
        } else {
            response.setEstado("error");
        }

        return response;
    }
}
