package com.upmasters.hrworker.resources;

import com.upmasters.hrworker.entities.Worker;
import com.upmasters.hrworker.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerResource {

  @Autowired
  private WorkerRepository repository;

  @GetMapping()
  public ResponseEntity<List<Worker>> findAll(){
    List<Worker> list = repository.findAll();
    return ResponseEntity.ok(list);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Worker> findById(@PathVariable Long id){
    Worker worker = repository.findById(id).get();
    return ResponseEntity.ok(worker);
  }

}