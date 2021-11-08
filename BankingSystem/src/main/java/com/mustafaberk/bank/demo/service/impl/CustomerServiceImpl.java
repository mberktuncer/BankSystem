package com.mustafaberk.bank.demo.service.impl;

import com.mustafaberk.bank.demo.entity.Customer;
import com.mustafaberk.bank.demo.repository.CustomerRepository;
import com.mustafaberk.bank.demo.request.CustomerCreateRequest;
import com.mustafaberk.bank.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Customer save(CustomerCreateRequest request){

        Customer customer = new Customer();

        customer.setGender(request.getGender());
        customer.setBirthCity(request.getBirthCity());
        customer.setBirthTown(request.getBirthTown());
        customer.setBirthDate(request.getBirthDate());
        customer.setName(request.getName());
        customer.setIdentificationNumber(request.getIdentificationNumber());

        if(request.getSecondName() != null)
            customer.setSecondName(request.getSecondName());

        customer.setLastName(request.getLastName());

        customerRepository.save(customer);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findByCustomerNumber(long customerNumber) {
        return customerRepository.findByCustomerNumber(customerNumber);
    }

    @Override
    public Customer findByIdentificationNumber(String identificationNumber) {
        return customerRepository.findByIdentificationNumber(identificationNumber);
    }

    @Override
    public Customer findById(long id) {
        return customerRepository.findById(id);
    }

}
