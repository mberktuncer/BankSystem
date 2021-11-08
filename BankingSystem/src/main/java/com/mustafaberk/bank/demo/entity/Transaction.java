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
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long receiverAccountNumber;
    private long senderAccountNumber;
    private Date transactionDate = new Date();
    private BigDecimal transferAmount;
    private String description;
    private String failReason;
    private int successful;
    private String type = "AA";

}
