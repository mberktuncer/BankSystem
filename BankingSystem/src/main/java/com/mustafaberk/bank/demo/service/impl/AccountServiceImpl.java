package com.mustafaberk.bank.demo.service.impl;

import com.mustafaberk.bank.demo.entity.Account;
import com.mustafaberk.bank.demo.entity.Customer;
import com.mustafaberk.bank.demo.repository.AccountRepository;
import com.mustafaberk.bank.demo.repository.CustomerRepository;
import com.mustafaberk.bank.demo.request.AccountCreateRequest;
import com.mustafaberk.bank.demo.service.AccountService;
import com.mustafaberk.bank.demo.util.AccountNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findByCustomerNumber(long customerNumber) {
        return accountRepository.findByCustomerNumber(customerNumber);
    }

    public ResponseEntity<Object> create(AccountCreateRequest accountCreateRequest){

        Customer customer = customerRepository.findByCustomerNumber(accountCreateRequest.getCustomerNumber());

        if(customer == null)
            return ResponseEntity.status(404).body("Customer not found on database!");

        Account account = new Account();

        account.setBalance(BigDecimal.ZERO);
        account.setStatus(1);
        account.setOpeningDate(new Date());
        account.setAccountType(accountCreateRequest.getAccountType());
        account.setCustomerNumber(accountCreateRequest.getCustomerNumber());
        account.setAccountNumber(AccountNumberGenerator.generateAccountNumber());

        accountRepository.save(account);

        return ResponseEntity.status(200).body(account);

    }

    @Override
    public ResponseEntity<Object> close(long accountNumber) {

        Account account = accountRepository.findByAccountNumber(accountNumber);

        if(!isAccountExist(account)){
            return ResponseEntity.status(404).body("Account not found!");
        }
        if(account.getBalance().compareTo(BigDecimal.ZERO) >= 0)
            return ResponseEntity.badRequest().body("Account have a balance.Before close operation get the all money from account!");

        account.setStatus(0);
        account.setClosingDate(new Date());
        accountRepository.save(account);

        return ResponseEntity.ok("Account closed!");
    }



    @Override
    public List<Account> listActiveAccounts() {

        List<Account> accounts = accountRepository.findAll();

        return accounts.stream().filter(a -> a.getStatus() == 1).collect(Collectors.toList());
    }

    @Override
    public List<Account> listClosedAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().filter(a -> a.getStatus() == 0).collect(Collectors.toList());
    }

    @Override
    public List<Account> listActiveAccountsByCustomerNumber(long customerNumber) {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().filter(isActive().and(hasCustomer(customerNumber))).collect(Collectors.toList());
    }

    public static boolean isAccountExist(Account account){
        return account != null;
    }

    public static boolean isAccountActive(Account account){
        return account.getStatus() == 1;
    }
    public Predicate<Account> isActive(){
        return fr -> fr.getStatus() == 1;
    }

    public Predicate<Account> hasCustomer(long customerNumber){
        return fr -> fr.getCustomerNumber() == customerNumber;
    }

}
