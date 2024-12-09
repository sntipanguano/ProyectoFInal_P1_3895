package taller1.taller1.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taller1.taller1.Modelo.Prestamos;

import java.util.List;

@Repository
public interface PrestamosRepository extends JpaRepository<Prestamos, Long> {

    // Consultar los préstamos por ID de usuario
    List<Prestamos> findByUsuarioId(Long usuarioId);
}
