package com.soap.soap.repository;

import com.soap.soap.model.ReportesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportesRepository extends JpaRepository<ReportesModel, Integer> {
}
