package br.com.upmasters.hrworker.repositories;

import br.com.upmasters.hrworker.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
