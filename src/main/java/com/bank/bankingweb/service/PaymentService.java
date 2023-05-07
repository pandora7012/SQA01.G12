package com.bank.bankingweb.service;



import com.bank.bankingweb.entity.Payment;

import java.util.List;

public interface PaymentService {

    public List<Payment> createAllPayment(List<Payment> list);
    public Payment getPaymentById(Long id);
    public Payment updatePayment(Payment payment);

}
