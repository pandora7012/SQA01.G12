package com.bank.bankingweb.repository;

import com.bank.bankingweb.entity.Loan;
import com.bank.bankingweb.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByLoan(Loan loan);
    Payment findPaymentById(Long id);

}
