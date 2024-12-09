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
@Table(name = "reportes")
public class ReportesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tiporeporte", nullable = false)
    private String tiporeporte;

    @Column(name = "fechageneracion", nullable = false)
    private java.time.LocalDateTime fechageneracion;

    @Column(name = "contenido", nullable = false)
    private String contenido;
}
