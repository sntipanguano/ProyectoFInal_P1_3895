package taller1.taller1.Mapeadores;

import taller1.taller1.Dto.PrestamosDTO;
import taller1.taller1.Modelo.Prestamos;

public class PrestamosMapper {

    public static PrestamosDTO toDTO(Prestamos prestamo) {
        PrestamosDTO dto = new PrestamosDTO();
        dto.setTituloLibro(prestamo.getLibro().getTitulo());  // Obtener el título del libro
        dto.setFechaPrestamo(prestamo.getFechaPrestamo().toString());  // Convertir la fecha a String
        dto.setEstadoPrestamo(prestamo.getEstadoPrestamo());  // Obtener el estado del préstamo
        return dto;
    }
}
