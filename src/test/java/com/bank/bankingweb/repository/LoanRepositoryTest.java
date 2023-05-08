package com.bank.bankingweb.repository;

import com.bank.bankingweb.entity.Account;
import com.bank.bankingweb.entity.Loan;
import com.bank.bankingweb.repository.AccountRepository;
import com.bank.bankingweb.repository.LoanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class LoanRepositoryTest {
    @Autowired
    LoanRepository loanRepository;
    @Autowired
    AccountRepository accountRepository;

    @Test
    void TestfindAllByAccount_Right(){
        Optional<Account> accountOptional = accountRepository.findById(2L);
        Account account = accountOptional.get();
        List<Loan> list = loanRepository.findAllByAccount(account);
        Assertions.assertNotNull(list);
    }

    @Test
    void TestfindById_Right(){
        Optional<Loan> loanOptional = loanRepository.findById(2L);
        Assertions.assertTrue(loanOptional.isPresent());
    }

    @Test
    void TestfindById_Wrong(){
        Optional<Loan> loanOptional = loanRepository.findById(4L);
        Assertions.assertFalse(loanOptional.isPresent());
    }

    @Test
    void Testsave(){
        Optional<Account> accountOptional = accountRepository.findById(2L);
        Account account = accountOptional.get();
        Loan loan = new Loan(account, BigInteger.ONE,"0",3,3.0,"01/01/0002",true);
        Loan actualLoan = loanRepository.save(loan);
        Assertions.assertNotNull(actualLoan);
    }
}
