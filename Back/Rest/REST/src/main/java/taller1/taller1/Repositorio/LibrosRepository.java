package taller1.taller1.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import taller1.taller1.Modelo.Libros;

import java.util.List;
import java.util.Optional;

public interface LibrosRepository extends JpaRepository<Libros, Long> {

    Optional<Libros> findByTitulo(String titulo);

    List<Libros> findByCategoria(String categoria);
}
