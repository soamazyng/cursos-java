package com.example.carros.domain;

import com.example.carros.domain.dto.CarroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarrosService {

    @Autowired //injeção de dependência
    private CarroRepository repo;

    public List<CarroDto> getCarros(){
        return repo.findAll().stream().map(CarroDto::create).collect(Collectors.toList());
    }

    public Optional<CarroDto> getCarroById(Long id){
        return repo.findById(id).map(CarroDto::create);
    }

//    public List<Carro> getCarrosFake(){
//        List<Carro> carros = new ArrayList<>();
//
//        carros.add(new Carro(1L, "Sentra"));
//        carros.add(new Carro(2L, "Voyagem"));
//        carros.add(new Carro(3L, "Palio"));
//
//        return carros;
//    }

    public List<CarroDto> getCarroByType(String tipo) {
        return repo.findByType(tipo).stream().map(CarroDto::create).collect(Collectors.toList());

    }

    public CarroDto saveCarro(Carro carro) {
        Assert.isNull(carro.getId(), "Não foi possível inserir o registro");
        return CarroDto.create(repo.save(carro));
    }

    public Carro updateCarro(Carro carro, Long id) {

        Assert.notNull(id, "Carro não existe");

        Optional<Carro> carroUpdate = repo.findById(id);

        if(!carroUpdate.isPresent())
            throw new RuntimeException("Carro não existe");

        Carro db = carroUpdate.get();
        db.setName(carro.getName());
        db.setType(carro.getType());
        System.out.println("Carro Id" + db.getId());

        repo.save(db);

        return db;
    }

    public void deleteCarro(Long id) {
        Optional<CarroDto> carro = getCarroById(id);
        if (carro.isPresent())
            repo.deleteById(id);
    }
}
