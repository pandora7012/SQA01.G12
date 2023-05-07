package com.bank.bankingweb.service;



import com.bank.bankingweb.entity.Loan;
import com.bank.bankingweb.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface BillService {

    public List<Payment> getAllPaymentByLoan(Long id);
    public Optional<Loan> getLoanById(Long id);
    public List<Payment> updateAllPayment(List<Payment> list);

}
