package com.upmasters.hrpayrollv2.feignclients;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

  @Bean
  public Retryer retryer(){
    return new FeignRetry();
  }

}