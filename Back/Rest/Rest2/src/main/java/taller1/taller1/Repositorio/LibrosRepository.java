package taller1.taller1.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import taller1.taller1.Modelo.Libros;

public interface LibrosRepository extends JpaRepository<Libros, Long> {
}
