package com.oss.netflix.currencyexchange;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CurrencyExchangeController {

    private final Environment env;
    private final ExchangeValueRepository repo;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

        ExchangeValue exchangeValue =
                repo.findByFromAndTo(from, to);

        exchangeValue.setPort(
                Integer.parseInt(env.getProperty("local.server.port")));

        log.info("{}", exchangeValue);

        return exchangeValue;

//        return new ExchangeValue(1L, from, to, 1.0,
//                Integer.parseInt(env.getProperty("local.server.port")));
    }
}
