package com.github.saipradyu.performance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
public class JpaPerformanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaPerformanceApplication.class, args);
	}

}
