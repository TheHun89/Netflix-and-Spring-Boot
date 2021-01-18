package com.oss.netflix.currencyconversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/** Feign, Ribbon, Eureka can have issues importing.  If this occurs do a maven clean package
 * and then reimport all maven projects **/
@EnableDiscoveryClient
@EnableFeignClients("com.oss.netflix.currencyconversion")
@SpringBootApplication
public class CurrencyConversionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionApplication.class, args);
	}

}
