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
@Table(name = "libros")
public class LibrosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "autor", nullable = false)
    private String autor;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "disponibles", nullable = false)
    private int disponibles;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "cantidad", nullable = false)
    private int cantidad = 0;  // Valor por defecto de 0
}
