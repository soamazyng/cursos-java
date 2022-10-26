package com.upmasters.hrpayrollv2.services;

import com.upmasters.hrpayrollv2.feignclients.WorkerFeignClient;
import com.upmasters.hrpayrollv2.entities.Payment;
import com.upmasters.hrpayrollv2.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

  @Autowired
  private WorkerFeignClient workerFeignClient;

  public Payment getPayment(long workerId, int days){

    Worker worker = workerFeignClient.findById(workerId).getBody();

    return new Payment(worker.getName(), worker.getDailyIncome(), days);
  }

}