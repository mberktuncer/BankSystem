package com.mustafaberk.bank.demo.entity;

import lombok.*;

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
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String cardNo;
    private int status;
    private BigDecimal totalLimit;
    private BigDecimal usableLimit;
    private BigDecimal remainingLimit;
    private long customerNumber;
    private Date expiredDate;

}
