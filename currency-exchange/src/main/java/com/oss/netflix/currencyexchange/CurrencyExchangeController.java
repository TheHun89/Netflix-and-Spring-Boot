package com.oss.netflix.currencyexchange;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
public class CurrencyExchangeController {

    private final Environment env;
    private final ExchangeValueRepository repo;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        log.info("DEBUG repo size is {}", repo.count());
        ExchangeValue exchangeValue =
                repo.findByFromAndTo(from, to);

        exchangeValue.setPort(
                Integer.parseInt(env.getProperty("local.server.port")));

        log.info("{}", exchangeValue);

        return exchangeValue;

//        return new ExchangeValue(1L, from, to, 1.0,
//                Integer.parseInt(env.getProperty("local.server.port")));
    }

    @GetMapping("/currency-exchange")
    public List<ExchangeValue> findAll() {
        return repo.findAll();
    }

    @PostMapping("/currency-exchange")
    public void save(@Valid @RequestBody ExchangeValue exchangeValue) {
        repo.save(exchangeValue);
    }
}
