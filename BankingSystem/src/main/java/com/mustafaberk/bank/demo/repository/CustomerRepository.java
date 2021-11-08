package com.mustafaberk.bank.demo.repository;

import com.mustafaberk.bank.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CustomerRepository extends JpaRepository<Customer , Long> {

    public Customer findByIdentificationNumber(String identificationNumber);
    public Customer findByCustomerNumber(long customerNumber);
    public Customer findById(long id);
    public List<Customer> findAll();


}
