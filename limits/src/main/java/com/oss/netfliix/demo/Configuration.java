package com.oss.netfliix.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("limits-service")
public class Configuration {

    private int minimum;
    private int maximum;
}
