package com.upmasters.hrpayrollv2.feignclients;

import com.upmasters.hrpayrollv2.entities.Worker;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class WorkerFeignFallback implements WorkerFeignClient {

  @Override
  public ResponseEntity<Worker> findById(final Long id) {
    return null;
  }
}