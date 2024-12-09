package com.soap.soap.repository;

import com.soap.soap.model.LibrosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrosRepository extends JpaRepository<LibrosModel, Integer> {
}
