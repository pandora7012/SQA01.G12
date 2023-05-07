package com.bank.bankingweb.repository;

import com.bank.bankingweb.entity.Loan;
import com.bank.bankingweb.entity.Payment;
import com.bank.bankingweb.repository.LoanRepository;
import com.bank.bankingweb.repository.PaymentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
public class PaymentRepositoryTest {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    LoanRepository loanRepository;

    @Test
    void TestfindByLoan(){
        Optional<Loan> loanOptional = loanRepository.findById(2L);
        Loan loan = loanOptional.get();
        List<Payment> list = paymentRepository.findByLoan(loan);
        Assertions.assertNotNull(list);
    }
    @Test
    void TestfindPaymentById_Right(){
        Payment payment = paymentRepository.findPaymentById(2L);
        Assertions.assertNotNull(payment);
    }

    @Test
    void TestfindPaymentById_Wrong(){
        Payment payment = paymentRepository.findPaymentById(12L);
        Assertions.assertNull(payment);
    }

    @Test
    void Testsave(){
        Optional<Loan> loanOptional = loanRepository.findById(2L);
        Loan loan = loanOptional.get();
        Payment payment = new Payment(loan, BigInteger.ONE,BigInteger.ZERO,BigInteger.ZERO,true,"01/01/2000",BigInteger.ZERO,"02/02/2000");
        Assertions.assertNotNull(paymentRepository.save(payment));

    }

    @Test
    void TestsaveAll(){
        Optional<Loan> loanOptional = loanRepository.findById(2L);
        Loan loan = loanOptional.get();
        List<Payment> list = paymentRepository.findByLoan(loan);
        Assertions.assertNotNull(paymentRepository.saveAll(list));
    }
}
