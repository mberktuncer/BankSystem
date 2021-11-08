package com.mustafaberk.bank.demo.controller;

import com.mustafaberk.bank.demo.entity.Transaction;
import com.mustafaberk.bank.demo.service.TransactionService;
import com.mustafaberk.bank.demo.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController implements TransactionService {

    @Autowired
    private TransactionServiceImpl transactionService;

    @Override
    @PostMapping("/send/{senderAccountNumber}/{receiverAccountNumber}/{transferAmount}/{description}")
    public ResponseEntity<Object> send(@PathVariable long senderAccountNumber,@PathVariable long receiverAccountNumber ,@PathVariable BigDecimal transferAmount , @PathVariable String description) {
        return transactionService.send(senderAccountNumber,receiverAccountNumber,transferAmount,description);
    }

    @Override
    @GetMapping("/list")
    public List<Transaction> listAll() {
        return transactionService.listAll();
    }

    @Override
    @GetMapping("/list/{customerNumber}")
    public List<Transaction> listByCustomerNumber(@PathVariable long customerNumber) {
        return transactionService.listByCustomerNumber(customerNumber);
    }

    @Override
    @GetMapping("/list/{first}/{last}")
    public List<Transaction> listByDate(@PathVariable Date first,@PathVariable Date last) {
        return transactionService.listByDate(first,last);
    }

    @Override
    @GetMapping("/list/{accountNumber}")
    public List<Transaction> listByAccountNumber(@PathVariable long accountNumber) {
        return transactionService.listByAccountNumber(accountNumber);
    }

    @Override
    @GetMapping("/list/successful")
    public List<Transaction> listSuccessfulTransaction() {
        return transactionService.listSuccessfulTransaction();
    }

    @Override
    @GetMapping("/list/successful/{customerNumber}")
    public List<Transaction> listSuccessfulTransactionByCustomerNumber(@PathVariable long customerNumber) {
        return transactionService.listSuccessfulTransactionByCustomerNumber(customerNumber);
    }

    @Override
    @PostMapping("/add/balance/{accountNumber}/{amount}")
    public ResponseEntity<Object> addBalance(@PathVariable long accountNumber,@PathVariable BigDecimal amount) {
        return transactionService.addBalance(accountNumber,amount);
    }
}
