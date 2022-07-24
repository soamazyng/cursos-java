package com.example.carros.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    // Para funcionar esse método, depois do By o nome da coluna deve ser identico ao nome que está na model
    List<Carro> findByType(String tipo);
}
