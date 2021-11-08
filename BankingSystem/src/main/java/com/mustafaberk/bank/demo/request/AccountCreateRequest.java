package com.mustafaberk.bank.demo.request;

import lombok.Data;

@Data
public class AccountCreateRequest {

    private String accountType;
    private long customerNumber;

}
