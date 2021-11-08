package com.mustafaberk.bank.demo.controller;

import com.mustafaberk.bank.demo.entity.Customer;
import com.mustafaberk.bank.demo.request.CustomerCreateRequest;
import com.mustafaberk.bank.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController implements CustomerService {


    @Autowired
    private CustomerService customerService;


    @Override
    @PostMapping("/save")
    public Customer save(@RequestBody CustomerCreateRequest customer) {

        return customerService.save(customer);
    }

    @Override
    @GetMapping("/list")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @Override
    @GetMapping("/find/customerNumber/{customerNumber}")
    public Customer findByCustomerNumber(@PathVariable long customerNumber) {
        return customerService.findByCustomerNumber(customerNumber);
    }

    @Override
    public Customer findByIdentificationNumber(String identificationNumber) {
        return customerService.findByIdentificationNumber(identificationNumber);
    }

    @Override
    @GetMapping("/find/id/{id}")
    public Customer findById(@PathVariable long id) {
        return customerService.findById(id);
    }


}
