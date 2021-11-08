package com.mustafaberk.bank.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long customerNumber;

    @NotNull
    private String name;

    private String secondName;

    @NotNull
    private String lastName;

    @NotNull
    private String gender;

    @NotNull
    @Size(min = 11,max = 11,message = "Size must be 11!")
    private String identificationNumber;

    private String birthDate;
    private String birthCity;
    private String birthTown;



}
