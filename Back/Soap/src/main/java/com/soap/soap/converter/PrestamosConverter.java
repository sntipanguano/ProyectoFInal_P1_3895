package com.soap.soap.converter;

import com.soap.soap.gen.Prestamos;  // Asumiendo que tienes esta clase generada
import com.soap.soap.model.PrestamosModel;
import org.springframework.stereotype.Component;

@Component
public class PrestamosConverter {

    // Convierte un objeto Prestamo a un modelo PrestamosModel
    public PrestamosModel convertPrestamoToPrestamosModel(Prestamos prestamo) {
        PrestamosModel prestamosModel = new PrestamosModel();
        prestamosModel.setId(prestamo.getIdPrestamo());
        prestamosModel.setIdusuario(prestamo.getIdUsuario());
        prestamosModel.setIdlibro(prestamo.getIdLibro());
        prestamosModel.setFechaprestamo(prestamo.getFechaPrestamo().toGregorianCalendar().toZonedDateTime().toLocalDateTime());
        prestamosModel.setFechadevolucion(prestamo.getFechaDevolucion().toGregorianCalendar().toZonedDateTime().toLocalDateTime());
        prestamosModel.setEstadoprestamo(prestamo.getEstadoPrestamo());
        prestamosModel.setMulta(prestamo.getMulta());
        return prestamosModel;
    }

    // Convierte un modelo PrestamosModel a un objeto Prestamo
    public Prestamos convertPrestamosModelToPrestamo(PrestamosModel prestamosModel) {
        Prestamos prestamo = new Prestamos();
        prestamo.setIdPrestamo(prestamosModel.getId());
        prestamo.setIdUsuario(prestamosModel.getIdusuario());
        prestamo.setIdLibro(prestamosModel.getIdlibro());
        prestamo.setEstadoPrestamo(prestamosModel.getEstadoprestamo());
        prestamo.setMulta(prestamosModel.getMulta());
        return prestamo;
    }
}
