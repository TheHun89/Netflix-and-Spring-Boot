package com.oss.netfliix.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LimitsController {

    private final Configuration configuration;

    @GetMapping("/limits")
    public Limit returnLimit() {
        return new Limit(configuration.getMinimum(), configuration.getMaximum());
    }

//    @GetMapping("/limits")
//    public Limit retrieveLimitsFromConfigurations() {
//        Limit limitConfiguration = new Limit(configuration.(),
//                configuration.getMinimum());
//        return limitConfiguration;
//    }

//    @GetMapping("/fault-tolerance-example")
//    @HystrixCommand(fallbackMethod="fallbackRetrieveConfiguration")
//    public Limit retrieveConfiguration() {
//        throw new RuntimeException("Not available");
//    }

    public Limit fallbackRetrieveConfiguration() {
        return new Limit(999, 9);
    }
}
