package com.upmasters.hrworkerv2.repositories;

import com.upmasters.hrworkerv2.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}