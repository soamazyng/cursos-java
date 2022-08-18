package com.example.carros.api;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarrosService;
import com.example.carros.domain.dto.CarroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

  @Autowired
  private CarrosService service;

  @GetMapping()
  @Secured({"USER"})
  public ResponseEntity<List<CarroDto>> get() {
    return ResponseEntity.ok(service.getCarros());
    //return new ResponseEntity<>(service.getCarros(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity getById(@PathVariable("id") Long id) {
    var carroDto = service.getCarroById(id);

    // forma com lambda
    return ResponseEntity.ok(carroDto);
  }

  @GetMapping("/tipo/{tipo}")
  public ResponseEntity<List<CarroDto>> getByType(@PathVariable("tipo") String tipo) {
    var dados = service.getCarroByType(tipo);

    if (dados.isEmpty())
      return ResponseEntity.noContent().build();

    return ResponseEntity.ok(dados);
  }

  @PostMapping()
  public ResponseEntity Post(@RequestBody Carro carro) {
    CarroDto c = service.saveCarro(carro);
    var location = getUri(c.getId());
    return ResponseEntity.created(location).build();
  }

  private URI getUri(Long id) {
    return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(id).toUri();
  }

  @PutMapping("/{id}")
  public ResponseEntity Put(@PathVariable("id") Long id, @RequestBody Carro carro) {
    try {
      carro.setId(id);

      CarroDto carroDto = service.updateCarro(carro, id);

      if (carroDto == null)
        return ResponseEntity.notFound().build();

      return ResponseEntity.ok().build();
    } catch (Exception ex) {
      return ResponseEntity.badRequest().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity Delete(@PathVariable("id") Long id) {

    service.deleteCarro(id);
    return ResponseEntity.noContent().build();

  }

}
