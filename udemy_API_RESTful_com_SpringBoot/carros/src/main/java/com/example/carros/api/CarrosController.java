package com.example.carros.api;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarrosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    private CarrosService service = new CarrosService();

    @GetMapping()
    public List<Carro> Get(){
        return service.getCarros();
    }

}
