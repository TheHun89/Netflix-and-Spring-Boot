package com.oss.netflix.currencyconversion;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CurrencyConversionController {

    private final CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
                                                  @PathVariable Double quantity) {

        CurrencyConversionBean response = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);

        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
                (quantity * response.getConversionMultiple()), response.getPort());
    }

//    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
//    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
//                                                  @PathVariable Double quantity) {
//
//        Map<String, String> uriVariables = new HashMap<>();
//        uriVariables.put("from", from);
//        uriVariables.put("to", to);
//
//        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
//                currencyExchangeUrl, CurrencyConversionBean.class,
//                uriVariables);
//
//        CurrencyConversionBean response = responseEntity.getBody();
//
//        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
//                (quantity * response.getConversionMultiple()), response.getPort());
//    }
}
