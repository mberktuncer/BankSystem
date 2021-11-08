package com.mustafaberk.bank.demo.service;

import com.mustafaberk.bank.demo.entity.Account;
import com.mustafaberk.bank.demo.request.AccountCreateRequest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.*;
public interface AccountService {

    public List<Account> findAll();
    public List<Account> findByCustomerNumber(long customerNumber);
    public ResponseEntity<Object> create(AccountCreateRequest account);
    public ResponseEntity<Object> close(long accountNumber);
    public List<Account> listActiveAccounts();
    public List<Account> listClosedAccounts();
    public List<Account> listActiveAccountsByCustomerNumber(long customerNumber);
}
