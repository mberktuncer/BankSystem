package com.mustafaberk.bank.demo.repository;


import com.mustafaberk.bank.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    public List<Account> findByCustomerNumber(long customerNumber);
    public Account findByAccountNumber(long accountNumber);
    public List<Account> findAll();

}
