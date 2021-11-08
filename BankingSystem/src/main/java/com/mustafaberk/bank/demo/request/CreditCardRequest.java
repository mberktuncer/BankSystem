package com.mustafaberk.bank.demo.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CreditCardRequest {



    private BigDecimal totalLimit;
     private long customerNumber;


}
