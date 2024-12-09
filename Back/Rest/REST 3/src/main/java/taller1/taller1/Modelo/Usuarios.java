package taller1.taller1.Modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")  // Asegúrate de que sea 'usuarios'
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    @Column(name = "tipousuario", nullable = false)  // Cambiado a 'tipousuario' para coincidir con la base de datos
    private String tipousuario;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipousuario() {  // Este método devuelve 'tipousuario'
        return tipousuario;
    }

    public void setTipousuario(String tipousuario) {  // Este método establece 'tipousuario'
        this.tipousuario = tipousuario;
    }
}
