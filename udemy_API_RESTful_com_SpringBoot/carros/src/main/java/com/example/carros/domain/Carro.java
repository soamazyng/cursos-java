package com.example.carros.domain;

import lombok.Data;

import javax.persistence.*;

@Data // Lombok já cria automaticamente os gets sets e o contrutor default que é obrigatório
@Entity(name = "carro") // só precisa colocar o nome do entity se o nome da tabela for diferente do nome da classe
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "tipo")
    private String type;

}
