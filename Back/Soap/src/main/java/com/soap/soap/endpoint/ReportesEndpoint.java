package com.soap.soap.endpoint;

import com.soap.soap.converter.ReportesConverter;
import com.soap.soap.gen.*;
import com.soap.soap.model.PrestamosModel;
import com.soap.soap.repository.PrestamosRepository;
import com.soap.soap.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class ReportesEndpoint {

    @Autowired
    private PrestamosRepository prestamosRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private ReportesConverter reportesConverter;

    // Endpoint para generar un reporte de préstamos activos
    @PayloadRoot(namespace = "http://www.soap.com/", localPart = "reportePrestamosActivosRequest")
    @ResponsePayload
    public ReportePrestamosActivosResponse reportePrestamosActivos(@RequestPayload ReportePrestamosActivosRequest request) {
        ReportePrestamosActivosResponse response = new ReportePrestamosActivosResponse();

        // Obtener los préstamos activos (con filtro opcional por fechas)
        List<PrestamosModel> prestamos = prestamosRepository.findActiveLoans(request.getFechaInicio(), request.getFechaFin());
        
        for (PrestamosModel prestamo : prestamos) {
            // Convertir a formato adecuado y agregar al reporte
            Prestamos reporte = new Prestamos();
            reporte.setIdPrestamo(prestamo.getId());
            reporte.setIdUsuario(prestamo.getIdusuario());
            reporte.setIdLibro(prestamo.getIdlibro());
            reporte.setEstadoPrestamo(prestamo.getEstadoprestamo());
            response.getPrestamos().add(reporte);
        }

        return response;
    }

    // Endpoint para generar el historial de un usuario
    @PayloadRoot(namespace = "http://www.soap.com/", localPart = "reporteHistorialUsuarioRequest")
    @ResponsePayload
    public ReporteHistorialUsuarioResponse reporteHistorialUsuario(@RequestPayload ReporteHistorialUsuarioRequest request) {
        ReporteHistorialUsuarioResponse response = new ReporteHistorialUsuarioResponse();
        
        // Obtener historial de préstamos de un usuario
        List<PrestamosModel> prestamos = prestamosRepository.findByUsuarioId(request.getIdUsuario());
        
        for (PrestamosModel prestamo : prestamos) {
            // Convertir a formato adecuado y agregar al reporte
            Prestamos reporte = new Prestamos();
            reporte.setIdPrestamo(prestamo.getId());
            reporte.setIdUsuario(prestamo.getIdusuario());
            reporte.setIdLibro(prestamo.getIdlibro());
            reporte.setEstadoPrestamo(prestamo.getEstadoprestamo());
            reporte.setMulta(prestamo.getMulta());
            response.getPrestamos().add(reporte);
        }

        return response;
    }
}
