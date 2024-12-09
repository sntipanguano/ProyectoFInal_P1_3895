package taller1.taller1.Dto;

import java.time.LocalDate;

public class LibrosDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String categoria;
    private int disponibles;
    private String isbn;
    private int cantidad;
    private LocalDate fecharegistro;
    
        // Getters y Setters
    
        public Long getId() {
            return id;
        }
    
        public void setId(Long id) {
            this.id = id;
        }
    
        public String getTitulo() {
            return titulo;
        }
    
        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }
    
        public String getAutor() {
            return autor;
        }
    
        public void setAutor(String autor) {
            this.autor = autor;
        }
    
        public String getCategoria() {
            return categoria;
        }
    
        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }
    
        public int getDisponibles() {
            return disponibles;
        }
    
        public void setDisponibles(int disponibles) {
            this.disponibles = disponibles;
        }
    
        public String getIsbn() {
            return isbn;
        }
    
        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }
    
        public int getCantidad() {
            return cantidad;
        }
    
        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public LocalDate getFecharegistro() {
            return fecharegistro;
        }
    
        public void setFecharegistro(LocalDate fecharegistro) {
            this.fecharegistro = fecharegistro;
        }
}
