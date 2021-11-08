package com.mustafaberk.bank.demo.service.impl;

import com.mustafaberk.bank.demo.entity.Account;
import com.mustafaberk.bank.demo.entity.Transaction;
import com.mustafaberk.bank.demo.repository.AccountRepository;
import com.mustafaberk.bank.demo.repository.CustomerRepository;
import com.mustafaberk.bank.demo.repository.TransactionRepository;
import com.mustafaberk.bank.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.mustafaberk.bank.demo.service.impl.AccountServiceImpl.isAccountActive;
import static com.mustafaberk.bank.demo.service.impl.AccountServiceImpl.isAccountExist;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountServiceImpl accountService;


    @Override
    public ResponseEntity<Object> send(long senderAccountNumber, long receiverAccountNumber,BigDecimal transferAmount,String description) {

        Account sender = accountRepository.findByAccountNumber(senderAccountNumber);
        Account receiver = accountRepository.findByAccountNumber(receiverAccountNumber);

        if(sender == null)
            return ResponseEntity.status(404).body("Sender account not found!");

        if(receiver == null)
            return ResponseEntity.status(404).body("Receiver account not found!");

        if(sender.getStatus() != 1)
            return ResponseEntity.status(404).body("Sender account not active!");

        if(receiver.getStatus() != 1)
            return ResponseEntity.status(404).body("Receiver account not active!");


        Transaction transaction = new Transaction();

        String responseBody;
        int status = 0;

        transaction.setTransferAmount(transferAmount);
        transaction.setSenderAccountNumber(senderAccountNumber);
        transaction.setReceiverAccountNumber(receiverAccountNumber);
        transaction.setDescription(description);

        if(!(sender.getBalance().compareTo(transferAmount) >= 0)){

            transaction.setFailReason("Sender account balance is insufficient!");
            transaction.setSuccessful(0);
            status = 404;
            responseBody = "Sender account balance is insufficient!";
        }
        else {

            responseBody = "Transfer is successful!";
            status = 200;
            transaction.setSuccessful(1);
        }

        transactionRepository.save(transaction);
        return ResponseEntity.status(status).body(responseBody);

    }

    @Override
    public List<Transaction> listAll() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> listByCustomerNumber(long customerNumber) {

        List<Account> accountList = accountRepository.findByCustomerNumber(customerNumber);
        List<Transaction> transactions = transactionRepository.findAll();

        List<Transaction> transactionList = new ArrayList<>();

        for(Transaction transaction : transactions){

            for(Account account : accountList){

                if(account.getAccountNumber() == transaction.getReceiverAccountNumber() || account.getAccountNumber() == transaction.getSenderAccountNumber()) {
                    transactionList.add(transaction);
                }
            }
        }

        return transactionList;

    }

    @Override
    public List<Transaction> listByDate(Date first, Date last) {
        return transactionRepository.findAll()
                .stream()
                .filter( a ->!a.getTransactionDate().before(first) && a.getTransactionDate().after(last))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> listByAccountNumber(long accountNumber) {
        return transactionRepository.findAll()
                .stream()
                .filter(a -> a.getReceiverAccountNumber() == accountNumber || a.getSenderAccountNumber() == accountNumber)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> listSuccessfulTransaction() {
        return transactionRepository.findAll()
                .stream()
                .filter(a -> a.getSuccessful() == 1)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> listSuccessfulTransactionByCustomerNumber(long customerNumber) {

        List<Account> accountList = accountRepository.findByCustomerNumber(customerNumber);
        List<Transaction> transactionList = this.listSuccessfulTransaction();

        List<Transaction> transactions = new ArrayList<>();

        for(Account account : accountList){

            for(Transaction transaction : transactionList){

                if(transaction.getReceiverAccountNumber() == account.getAccountNumber() || transaction.getSenderAccountNumber() == account.getAccountNumber()){
                    transactions.add(transaction);
                }

            }

        }

      return  transactions;

    }

    @Override
    public ResponseEntity<Object> addBalance(long accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);

        if(!isAccountExist(account))
            return ResponseEntity.status(404).body("Account not found!");

        if(! isAccountActive(account))
            return ResponseEntity.status(404).body("Account is not active!");

        BigDecimal currentBalance = account.getBalance();
        BigDecimal newBalance =  currentBalance.add(amount);
        account.setBalance(newBalance);
        accountRepository.save(account);

        Transaction transaction = new Transaction();

        transaction.setType("HA");
        transaction.setTransactionDate(new Date());
        transaction.setDescription("Add balance between owner account");
        transaction.setSenderAccountNumber(accountNumber);
        transaction.setReceiverAccountNumber(accountNumber);
        transaction.setSuccessful(1);

        transactionRepository.save(transaction);

        return ResponseEntity.status(200).body("Balance added");

    }

    public boolean isTransferMoneyBiggerThanBalance(Account account, BigDecimal transferAmount){

        return !(account.getBalance().compareTo(transferAmount) >= 0);

    }
}
