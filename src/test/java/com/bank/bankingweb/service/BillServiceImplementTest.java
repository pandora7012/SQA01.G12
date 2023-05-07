package com.bank.bankingweb.service;

import com.bank.bankingweb.entity.Loan;
import com.bank.bankingweb.entity.Payment;
import com.bank.bankingweb.repository.LoanRepository;
import com.bank.bankingweb.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BillServiceImplementTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private BillServiceImplement billService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPaymentByLoan() {
        Long loanId = 1L;
        Loan loan = new Loan();
        loan.setId(loanId);

        Payment payment1 = new Payment();
        Payment payment2 = new Payment();
        List<Payment> expectedPayments = new ArrayList<>();
        expectedPayments.add(payment1);
        expectedPayments.add(payment2);

        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));
        when(paymentRepository.findByLoan(loan)).thenReturn(expectedPayments);

        List<Payment> actualPayments = billService.getAllPaymentByLoan(loanId);

        assertEquals(expectedPayments, actualPayments);
        verify(loanRepository, times(1)).findById(loanId);
        verify(paymentRepository, times(1)).findByLoan(loan);
    }

    @Test
    void testGetAllPaymentByLoanWithInvalidLoanId() {
        Long loanId = 1L;

        when(loanRepository.findById(loanId)).thenReturn(Optional.empty());

        List<Payment> actualPayments = billService.getAllPaymentByLoan(loanId);

        assertEquals(null, actualPayments);
        verify(loanRepository, times(1)).findById(loanId);
        verify(paymentRepository, never()).findByLoan(any(Loan.class));
    }

    @Test
    void testGetLoanById() {
        Long loanId = 1L;
        Loan expectedLoan = new Loan();
        expectedLoan.setId(loanId);

        when(loanRepository.findById(loanId)).thenReturn(Optional.of(expectedLoan));

        Optional<Loan> actualLoan = billService.getLoanById(loanId);

        assertEquals(expectedLoan, actualLoan.get());
        verify(loanRepository, times(1)).findById(loanId);
    }

    @Test
    void testGetLoanByIdWithInvalidLoanId() {
        Long loanId = 1L;

        when(loanRepository.findById(loanId)).thenReturn(Optional.empty());

        Optional<Loan> actualLoan = billService.getLoanById(loanId);

        assertEquals(Optional.empty(), actualLoan);
        verify(loanRepository, times(1)).findById(loanId);
    }

    @Test
    void testUpdateAllPayment() {
        Payment payment1 = new Payment();
        Payment payment2 = new Payment();
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment1);
        paymentList.add(payment2);

        when(paymentRepository.saveAll(paymentList)).thenReturn(paymentList);

        List<Payment> updatedPayments = billService.updateAllPayment(paymentList);

        assertEquals(paymentList, updatedPayments);
        verify(paymentRepository, times(1)).saveAll(paymentList);
    }
}