package taller1.taller1.Dto;

import java.time.LocalDate;

public class UsuariosDTO {

    private Long id;
    private String nombre;
    private String correo;
    private String tipousuario;
    private String estadousuario;
    private LocalDate fecharegistro;

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

    public String getTipousuario() {  // Ahora devuelve 'tipo'
        return tipousuario;
    }

    public void setTipousuario(String tipousuario) {  // Ahora establece 'tipo'
        this.tipousuario = tipousuario;
    }

    public String getEstadousuario() {  // Este método devuelve 'tipousuario'
        return estadousuario;
    }

    public void setEstadousuario(String estadousuario) {  // Este método establece 'tipousuario'
        this.estadousuario = estadousuario;
    }

    public LocalDate getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(LocalDate fecharegistro) {
        this.fecharegistro = fecharegistro;
    }
}
