package com.example.carros.domain;

import com.example.carros.api.exception.ObjectNotFoundException;
import com.example.carros.domain.dto.CarroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarrosService {

    @Autowired //injeção de dependência
    private CarroRepository repo;

    public List<CarroDto> getCarros(){
        return repo.findAll(Sort.by(Sort.Direction.ASC, "name")).stream().map(CarroDto::create).collect(Collectors.toList());
    }

    public CarroDto getCarroById(Long id){
        Optional<Carro> carro = repo.findById(id);
        return carro.map(CarroDto::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
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

    public CarroDto updateCarro(Carro carro, Long id) {

        Assert.notNull(id, "Carro não existe");

        Optional<Carro> carroUpdate = repo.findById(id);

        if(!carroUpdate.isPresent()){
            return null;
            //throw new RuntimeException("Carro não existe");
        }

        Carro db = carroUpdate.get();
        db.setName(carro.getName());
        db.setType(carro.getType());
        System.out.println("Carro Id" + db.getId());

        repo.save(db);

        return CarroDto.create(db);
    }

    public void deleteCarro(Long id) {
        repo.deleteById(id);
    }
}
