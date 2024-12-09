package com.soap.soap.repository;

import com.soap.soap.model.PrestamosModel;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamosRepository extends JpaRepository<PrestamosModel, Integer> {
    @Query("SELECT p FROM prestamos p WHERE p.estadoprestamo = 'activo' " +
           "AND (p.fechaprestamo BETWEEN :fechaInicio AND :fechaFin OR :fechaInicio IS NULL OR :fechaFin IS NULL)")
    List<PrestamosModel> findActiveLoans(@Param("fechaInicio") XMLGregorianCalendar fechaInicio, 
                                         @Param("fechaFin") XMLGregorianCalendar fechaFin);

    @Query("SELECT p FROM PrestamosModel p WHERE p.idusuario = :idUsuario AND p.estadoprestamo = 'activo'")
    List<PrestamosModel> findByUsuarioId(@Param("idUsuario") Integer idUsuario);
}
