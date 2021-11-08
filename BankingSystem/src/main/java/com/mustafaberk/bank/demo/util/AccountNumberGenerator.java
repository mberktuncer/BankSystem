package com.mustafaberk.bank.demo.util;


import java.util.Random;

public class AccountNumberGenerator {


    public static long generateAccountNumber(){

        long leftLimit = 1L;
        long rightLimit = 10L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

    }



}
