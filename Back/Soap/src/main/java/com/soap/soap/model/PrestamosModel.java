package com.soap.soap.model;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "prestamos")
public class PrestamosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "idusuario", nullable = false)
    private int idusuario;

    @Column(name = "idlibro", nullable = false)
    private int idlibro;

    @Column(name = "fechaprestamo", nullable = false)
    private java.time.LocalDateTime fechaprestamo;

    @Column(name = "fechadevolucion", nullable = false)
    private java.time.LocalDateTime fechadevolucion;

    @Column(name = "estadoprestamo", nullable = false)
    private String estadoprestamo;

    @Column(name = "multa", nullable = false)
    private BigDecimal multa;
}
