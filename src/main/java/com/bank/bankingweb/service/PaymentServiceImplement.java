package com.bank.bankingweb.service;


import com.bank.bankingweb.entity.Payment;
import com.bank.bankingweb.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImplement implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> createAllPayment(List<Payment> list) {
        return paymentRepository.saveAll(list);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findPaymentById(id);
    }

    @Override
    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

}
