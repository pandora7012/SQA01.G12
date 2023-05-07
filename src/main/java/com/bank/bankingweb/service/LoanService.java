package com.bank.bankingweb.service;



import com.bank.bankingweb.entity.Account;
import com.bank.bankingweb.entity.Loan;
import com.bank.bankingweb.entity.Payment;

import java.util.List;

public interface LoanService {

    public Loan createLoan(Loan loan, Long accountid);
    public List<Loan> getAllLoanByAccount(Account account);
    public Account updateAccount(Account account);
    public Loan getLoanById(Long id);
    public Loan updateLoan(Loan loan);
    public List<Payment> getAllPaymentByLoan(Loan loan);

}
