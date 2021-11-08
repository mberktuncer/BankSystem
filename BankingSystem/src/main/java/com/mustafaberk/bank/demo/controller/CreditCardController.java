package com.mustafaberk.bank.demo.controller;

import com.mustafaberk.bank.demo.entity.CreditCard;
import com.mustafaberk.bank.demo.request.CreditCardRequest;
import com.mustafaberk.bank.demo.service.CreditCardService;
import com.mustafaberk.bank.demo.service.impl.CreditCardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/creditCard")
public class CreditCardController implements CreditCardService {

    @Autowired
    private CreditCardServiceImpl creditCardService;

    @Override
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody CreditCardRequest request) {
        return creditCardService.create(request);
    }

    @Override
    @PostMapping("/use/{amount}/{cardNumber}")
    public ResponseEntity<Object> use(@PathVariable BigDecimal amount,@PathVariable String cardNumber) {
        return creditCardService.use(amount,cardNumber);
    }

    @Override
    @PostMapping("/close/{cardNumber}")
    public ResponseEntity<Object> close(@PathVariable String cardNumber) {
        return creditCardService.close(cardNumber);
    }

    @Override
    @GetMapping("/find/{customerNumber}")
    public List<CreditCard> findByCustomerNumber(@PathVariable long customerNumber) {
        return creditCardService.findByCustomerNumber(customerNumber);
    }
}
