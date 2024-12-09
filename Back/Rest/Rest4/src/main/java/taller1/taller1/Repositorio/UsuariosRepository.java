package taller1.taller1.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import taller1.taller1.Modelo.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
}
