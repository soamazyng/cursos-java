package com.example.carros.api;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarrosService;
import com.example.carros.domain.dto.CarroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.Servlet;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    @Autowired
    private CarrosService service;

    @GetMapping()
    public ResponseEntity<List<CarroDto>> get(){
        return ResponseEntity.ok(service.getCarros());
        //return new ResponseEntity<>(service.getCarros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id){
        var optional = service.getCarroById(id);

        // forma com lambda
        return optional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

        // uma das formas de fazer, forma simples!
//        if(optional.isPresent())
//            return ResponseEntity.ok(optional.get());
//
//        return ResponseEntity.notFound().build();
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CarroDto>> getByType(@PathVariable("tipo") String tipo){
        var dados = service.getCarroByType(tipo);

        if(dados.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(dados);
    }

    @PostMapping()
    public ResponseEntity Post(@RequestBody Carro carro){
        try {
            CarroDto c = service.saveCarro(carro);
            var location = getUri(c.getId());
            return ResponseEntity.created(location).build();
        }catch(Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public String Put(@PathVariable("id") Long id, @RequestBody Carro carro) {
        Carro c = service.updateCarro(carro, id);

        return "Carro atualizado com sucesso!" + c.getId();
    }

    @DeleteMapping("/{id}")
    public String Delete(@PathVariable("id") Long id) {
        service.deleteCarro(id);
        return "Carro deletado com sucesso!";
    }

}
