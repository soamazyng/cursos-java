package br.com.upmasters.configurations;

import br.com.upmasters.VendasApplication;
import org.apache.logging.log4j.LogManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.apache.logging.log4j.Logger;

@Configuration
public class ProjectConfiguration {

  @Bean("LoggerGlobal")
  public Logger logger(){
    return LogManager.getLogger(VendasApplication.class);
  }

}
