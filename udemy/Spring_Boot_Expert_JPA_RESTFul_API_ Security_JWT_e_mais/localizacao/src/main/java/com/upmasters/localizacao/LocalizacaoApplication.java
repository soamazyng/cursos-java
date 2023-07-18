package com.upmasters.localizacao;

import com.upmasters.localizacao.entity.Cidade;
import com.upmasters.localizacao.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

  @Autowired
  private CidadeService cidadeService;

  @Override
  public void run(String... args) throws Exception {
    var cidade = new Cidade(null, "São", null);
    cidadeService.listarCidadesPorNomeSqlNativo();
  }

  public static void main(String[] args) {
    SpringApplication.run(LocalizacaoApplication.class, args);
  }

}