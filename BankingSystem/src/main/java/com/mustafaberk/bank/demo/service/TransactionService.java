package com.mustafaberk.bank.demo.service;

import com.mustafaberk.bank.demo.entity.Transaction;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.*;

public interface TransactionService {

    public ResponseEntity<Object> send(long senderAccountNumber, long receiverAccountNumber, BigDecimal transferAmount,String description);
    public List<Transaction> listAll();
    public List<Transaction> listByCustomerNumber(long customerNumber);
    public List<Transaction> listByDate(Date first,Date last);
    public List<Transaction> listByAccountNumber(long accountNumber);
    public List<Transaction> listSuccessfulTransaction();
    public List<Transaction> listSuccessfulTransactionByCustomerNumber(long customerNumber);
    public ResponseEntity<Object> addBalance(long accountNumber, BigDecimal amount);
}
