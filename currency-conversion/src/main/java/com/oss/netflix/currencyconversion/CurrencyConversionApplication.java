package com.oss.netflix.currencyconversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/** Feign can have issues importing.  If this occurs do a maven clean package
 * and then reimport all maven projects **/
@EnableFeignClients("com.oss.netflix.currencyconversion")
@SpringBootApplication
public class CurrencyConversionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionApplication.class, args);
	}

}
