package com.bank.bankingweb.service;


import com.bank.bankingweb.entity.Account;
import com.bank.bankingweb.entity.Loan;
import com.bank.bankingweb.entity.Payment;
import com.bank.bankingweb.repository.AccountRepository;
import com.bank.bankingweb.repository.LoanRepository;
import com.bank.bankingweb.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImplement implements LoanService {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Loan createLoan(Loan loan, Long accountid) {
        Optional<Account> accountOptional = accountRepository.findById(accountid);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            loan.setAccount(account);
            return loanRepository.save(loan);
        } else {
            return null;
        }
    }

    @Override
    public List<Loan> getAllLoanByAccount(Account account) {
        return loanRepository.findAllByAccount(account);
    }

    @Override
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Loan getLoanById(Long id) {
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        if (optionalLoan.isPresent()) {
            return optionalLoan.get();
        } else {
            return null;
        }
    }

    @Override
    public Loan updateLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public List<Payment> getAllPaymentByLoan(Loan loan) {
        return paymentRepository.findByLoan(loan);
    }

}
