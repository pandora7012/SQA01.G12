package com.bank.bankingweb.service;


import com.bank.bankingweb.entity.Loan;
import com.bank.bankingweb.entity.Payment;
import com.bank.bankingweb.repository.LoanRepository;
import com.bank.bankingweb.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImplement implements BillService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public List<Payment> getAllPaymentByLoan(Long id) {
        Optional<Loan> loanOptional = loanRepository.findById(id);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            return paymentRepository.findByLoan(loan);
        } else {
            return null;
        }
    }

    @Override
    public Optional<Loan> getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    @Override
    public List<Payment> updateAllPayment(List<Payment> list) {
        return paymentRepository.saveAll(list);
    }

}
