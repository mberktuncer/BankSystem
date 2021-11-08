package com.mustafaberk.bank.demo.service.impl;

import com.mustafaberk.bank.demo.entity.CreditCard;
import com.mustafaberk.bank.demo.entity.Transaction;
import com.mustafaberk.bank.demo.repository.CreditCardRepository;
import com.mustafaberk.bank.demo.repository.CustomerRepository;
import com.mustafaberk.bank.demo.repository.TransactionRepository;
import com.mustafaberk.bank.demo.request.CreditCardRequest;
import com.mustafaberk.bank.demo.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public ResponseEntity<Object> create(CreditCardRequest request) {

        CreditCard card = new CreditCard();

        if(customerRepository.findByCustomerNumber(request.getCustomerNumber()) == null)
            return ResponseEntity.status(404).body("Customer not found");

        card.setCardNo(UUID.randomUUID().toString());
        card.setStatus(1);
        card.setRemainingLimit(request.getTotalLimit());
        card.setTotalLimit(request.getTotalLimit());
        card.setUsableLimit(request.getTotalLimit());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 2); // to get previous year add -1
        Date nextYear = cal.getTime();

        card.setExpiredDate(nextYear);
        card.setCustomerNumber(request.getCustomerNumber());

        creditCardRepository.save(card);

        return ResponseEntity.status(200).body(card);
    }

    @Override
    public ResponseEntity<Object> use(BigDecimal amount, String cardNumber) {

        if(creditCardRepository.findByCardNo(cardNumber) == null)
            return ResponseEntity.status(404).body("Card not found");

        CreditCard card = creditCardRepository.findByCardNo(cardNumber);
        Transaction transaction = new Transaction();
        transaction.setDescription("Credit card use");
        transaction.setType("Credit card");
        transaction.setTransferAmount(amount);
        transaction.setTransactionDate(new Date());


        if(!(card.getUsableLimit().compareTo(amount) >= 0)){

            transaction.setSuccessful(0);
            transaction.setFailReason("Insuefficent balance");
            transactionRepository.save(transaction);

            return ResponseEntity.status(404).body("Card balance is insuefficent");
        }

        card.setUsableLimit(card.getUsableLimit().subtract(amount));
        transaction.setSuccessful(1);

        creditCardRepository.save(card);
        transactionRepository.save(transaction);

        return ResponseEntity.status(200).body(transaction);

    }

    @Override
    public ResponseEntity<Object> close(String cardNumber) {

        CreditCard card = creditCardRepository.findByCardNo(cardNumber);

        if(card == null)
            return ResponseEntity.status(404).body("Card not found");

        if(card.getUsableLimit().compareTo(card.getTotalLimit()) < 0)
            return ResponseEntity.status(404).body("Credit card have a debt. Before close operation debt has to be payed!");

        card.setStatus(0);

        creditCardRepository.save(card);


        return ResponseEntity.status(200).body("Credit card closed!");
    }

    @Override
    public List<CreditCard> findByCustomerNumber(long customerNumber) {
        return creditCardRepository.findByCustomerNumber(customerNumber);
    }
}
