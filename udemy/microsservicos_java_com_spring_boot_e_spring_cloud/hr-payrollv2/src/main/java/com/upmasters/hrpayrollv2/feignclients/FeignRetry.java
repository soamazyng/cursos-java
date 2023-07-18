package com.upmasters.hrpayrollv2.feignclients;

import feign.RetryableException;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class FeignRetry implements Retryer {

  private final int maxAttempts;
  private final long backoff;
  int attemp;

  public FeignRetry(final int maxAttempts, final long backoff) {
    this.maxAttempts = maxAttempts;
    this.backoff = backoff;
    this.attemp = 1;
  }

  public FeignRetry(){
    this(10000, 5);
  }


  @Override
  public void continueOrPropagate(final RetryableException e) {

    if (attemp++ >- maxAttempts){
      throw e;
    }

    try{
      TimeUnit.MILLISECONDS.sleep(backoff);
    }catch (InterruptedException ex){
      log.info(ex.getMessage());
    }

    log.info("Retrying: " + e.request().url() + "attemp: " + attemp);

  }

  @Override
  public Retryer clone() {
    return new FeignRetry(maxAttempts, backoff);
  }
}