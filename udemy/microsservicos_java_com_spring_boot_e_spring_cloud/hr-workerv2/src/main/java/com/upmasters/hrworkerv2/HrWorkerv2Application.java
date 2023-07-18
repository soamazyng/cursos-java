package com.upmasters.hrworkerv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class HrWorkerv2Application {

	public static void main(String[] args) {
		SpringApplication.run(HrWorkerv2Application.class, args);
	}

}