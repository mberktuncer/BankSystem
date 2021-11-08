package com.mustafaberk.bank.demo.service;

import com.mustafaberk.bank.demo.entity.CreditCard;
import com.mustafaberk.bank.demo.request.CreditCardRequest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface CreditCardService {

    public ResponseEntity<Object> create(CreditCardRequest request);
    public ResponseEntity<Object> use(BigDecimal amount,String cardNumber);
    public ResponseEntity<Object> close(String cardNumber);
    List<CreditCard> findByCustomerNumber(long customerNumber);



}
