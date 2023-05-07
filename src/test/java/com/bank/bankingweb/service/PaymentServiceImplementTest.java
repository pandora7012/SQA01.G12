package com.bank.bankingweb.service;

import com.bank.bankingweb.entity.Payment;
import com.bank.bankingweb.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentServiceImplementTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImplement paymentService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAllPayment() {
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment());
        payments.add(new Payment());

        when(paymentRepository.saveAll(payments)).thenReturn(payments);

        List<Payment> savedPayments = paymentService.createAllPayment(payments);

        assertEquals(payments, savedPayments);
        verify(paymentRepository, times(1)).saveAll(payments);
    }

    @Test
    void testGetPaymentById() {
        Long paymentId = 1L;

        Payment expectedPayment = new Payment();
        expectedPayment.setId(paymentId);

        when(paymentRepository.findPaymentById(paymentId)).thenReturn(expectedPayment);

        Payment actualPayment = paymentService.getPaymentById(paymentId);

        assertEquals(expectedPayment, actualPayment);
        verify(paymentRepository, times(1)).findPaymentById(paymentId);
    }

    @Test
    void testUpdatePayment() {
        Payment payment = new Payment();

        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment updatedPayment = paymentService.updatePayment(payment);

        assertEquals(payment, updatedPayment);
        verify(paymentRepository, times(1)).save(payment);
    }
}
