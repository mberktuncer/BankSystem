package com.mustafaberk.bank.demo.service;


import com.mustafaberk.bank.demo.entity.Customer;
import com.mustafaberk.bank.demo.request.CustomerCreateRequest;

import java.util.*;

public interface CustomerService {

    public Customer save(CustomerCreateRequest customer);
    public List<Customer> findAll();
    public Customer findByCustomerNumber(long customerNumber);
    public Customer findByIdentificationNumber(String identificationNumber);
    public Customer findById(long id);

}
