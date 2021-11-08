package com.mustafaberk.bank.demo.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CustomerCreateRequest {


    private String name;
    private String secondName;
    private String lastName;
    private String gender;
    private String identificationNumber;
    private String birthDate;
    private String birthCity;
    private String birthTown;


}
