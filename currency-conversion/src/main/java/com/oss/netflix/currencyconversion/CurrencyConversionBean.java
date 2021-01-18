package com.oss.netflix.currencyconversion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversionBean {

    private Long id;
    private String from;
    private String to;
    private Double conversionMultiple;
    private Double quantity;
    private Double totalCalculatedAmount;
    private Integer port;
}
