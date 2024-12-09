package taller1.taller1.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taller1.taller1.Dto.PrestamosDTO;
import taller1.taller1.Mapeadores.PrestamosMapper;
import taller1.taller1.Modelo.Prestamos;
import taller1.taller1.Repositorio.PrestamosRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestamosService {

    @Autowired
    private PrestamosRepository prestamoRepository;

    // Consultar el historial de préstamos de un usuario
    public List<PrestamosDTO> consultarHistorialPrestamos(Long usuarioId) {
        List<Prestamos> prestamos = prestamoRepository.findByUsuarioId(usuarioId);
        return prestamos.stream()
                         .map(PrestamosMapper::toDTO)
                         .collect(Collectors.toList());
    }
}
