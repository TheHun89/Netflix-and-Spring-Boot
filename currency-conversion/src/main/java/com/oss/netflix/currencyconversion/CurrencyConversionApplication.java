package com.oss.netflix.currencyconversion;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/** Feign, Ribbon, Eureka can have issues importing.  If this occurs do a maven clean package
 * and then reimport all maven projects **/
@EnableFeignClients("com.oss.netflix.currencyconversion")
@SpringBootApplication
public class CurrencyConversionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
