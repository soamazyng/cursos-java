package com.example.carros.domain.dto;

import com.example.carros.domain.Carro;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;

@Data
public class CarroDto {

    private Long id;
    private String name;
    private String type;

    public static CarroDto create (Carro carro){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carro, CarroDto.class);
    }
}
