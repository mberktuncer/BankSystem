package com.mustafaberk.bank.demo.controller;

import com.mustafaberk.bank.demo.entity.Account;
import com.mustafaberk.bank.demo.request.AccountCreateRequest;
import com.mustafaberk.bank.demo.service.AccountService;
import com.mustafaberk.bank.demo.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController("/account")
public class AccountController implements AccountService {

    @Autowired
    AccountServiceImpl accountService;

    @Override
    @GetMapping("/list")
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @Override
    @GetMapping("/find/customerNumber/{customerNumber}")
    public List<Account> findByCustomerNumber(@PathVariable long customerNumber) {
        return accountService.findByCustomerNumber(customerNumber);
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody AccountCreateRequest account) {
        return accountService.create(account);
    }

    @Override
    @PostMapping("/close/{accountNumber}")
    public ResponseEntity<Object> close(@PathVariable long accountNumber) {
        return accountService.close(accountNumber);
    }



    @Override
    @GetMapping("/active")
    public List<Account> listActiveAccounts() {
        return accountService.listActiveAccounts();
    }

    @Override
    @GetMapping("/closed")
    public List<Account> listClosedAccounts() {
        return accountService.listClosedAccounts();
    }

    @Override
    @GetMapping("/active/{customerNumber}")
    public List<Account> listActiveAccountsByCustomerNumber(@PathVariable long customerNumber) {
        return accountService.listActiveAccountsByCustomerNumber(customerNumber);
    }
}
