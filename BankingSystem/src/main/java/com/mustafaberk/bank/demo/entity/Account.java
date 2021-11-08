package com.mustafaberk.bank.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private int status;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long accountNumber;

    private String accountType;
    private long customerNumber;
    private BigDecimal balance;
    private Date openingDate;
    private Date closingDate;



}
