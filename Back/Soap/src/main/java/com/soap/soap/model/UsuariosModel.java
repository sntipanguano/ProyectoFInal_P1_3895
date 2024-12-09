package com.soap.soap.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios")
public class UsuariosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "tipousuario", nullable = false)
    private String tipousuario;

    @Column(name = "estadousuario", nullable = false)
    private String estadousuario;

    @Column(name = "fecharegistro", nullable = false)
    private java.time.LocalDateTime fecharegistro;

    @Column(name = "password", nullable = false)
    private String password;  // Valor por defecto de 0
}
