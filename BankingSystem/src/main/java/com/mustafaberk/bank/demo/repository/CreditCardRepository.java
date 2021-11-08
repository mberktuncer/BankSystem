package com.mustafaberk.bank.demo.repository;

import com.mustafaberk.bank.demo.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard ,Long> {

    CreditCard findByCardNo(String cardNo);
    List<CreditCard> findByCustomerNumber(long customerNumber);

}
