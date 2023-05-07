package com.bank.bankingweb.service;

import com.bank.bankingweb.entity.Account;
import com.bank.bankingweb.entity.Loan;
import com.bank.bankingweb.entity.Payment;
import com.bank.bankingweb.repository.AccountRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LoanServiceImplementTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private LoanServiceImplement loanService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateLoan() {
        Long accountId = 1L;
        Long loanId = 1L;

        Loan loan = new Loan();
        loan.setId(loanId);

        Account account = new Account();
        account.setId(accountId);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(loanRepository.save(loan)).thenReturn(loan);

        Loan createdLoan = loanService.createLoan(loan, accountId);

        assertEquals(loan, createdLoan);
        assertEquals(account, loan.getAccount());
        verify(accountRepository, times(1)).findById(accountId);
        verify(loanRepository, times(1)).save(loan);
    }

    @Test
    void testCreateLoanWithInvalidAccountId() {
        Long accountId = 1L;
        Long loanId = 1L;

        Loan loan = new Loan();
        loan.setId(loanId);

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        Loan createdLoan = loanService.createLoan(loan, accountId);

        assertEquals(null, createdLoan);
        verify(accountRepository, times(1)).findById(accountId);
        verify(loanRepository, never()).save(any(Loan.class));
    }

    @Test
    void testGetAllLoanByAccount() {
        Long accountId = 1L;

        Account account = new Account();
        account.setId(accountId);

        Loan loan1 = new Loan();
        Loan loan2 = new Loan();
        List<Loan> expectedLoans = new ArrayList<>();
        expectedLoans.add(loan1);
        expectedLoans.add(loan2);

        when(loanRepository.findAllByAccount(account)).thenReturn(expectedLoans);

        List<Loan> actualLoans = loanService.getAllLoanByAccount(account);

        assertEquals(expectedLoans, actualLoans);
        verify(loanRepository, times(1)).findAllByAccount(account);
    }

    @Test
    void testUpdateAccount() {
        Account account = new Account();

        when(accountRepository.save(account)).thenReturn(account);

        Account updatedAccount = loanService.updateAccount(account);

        assertEquals(account, updatedAccount);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void testGetLoanById() {
        Long loanId = 1L;

        Loan expectedLoan = new Loan();
        expectedLoan.setId(loanId);

        when(loanRepository.findById(loanId)).thenReturn(Optional.of(expectedLoan));

        Loan actualLoan = loanService.getLoanById(loanId);

        assertEquals(expectedLoan, actualLoan);
        verify(loanRepository, times(1)).findById(loanId);
    }

    @Test
    void testGetLoanByIdWithInvalidLoanId() {
        Long loanId = 1L;

        when(loanRepository.findById(loanId)).thenReturn(Optional.empty());

        Loan actualLoan = loanService.getLoanById(loanId);

        assertEquals(null, actualLoan);
        verify(loanRepository, times(1)).findById(loanId);
    }

    @Test
    void testUpdateLoan() {
        Loan loan = new Loan();

        when(loanRepository.save(loan)).thenReturn(loan);

        Loan updatedLoan = loanService.updateLoan(loan);

        assertEquals(loan, updatedLoan);
        verify(loanRepository, times(1)).save(loan);
    }

    @Test
    void testGetAllPaymentByLoan() {
        Loan loan = new Loan();

        Payment payment1 = new Payment();
        Payment payment2 = new Payment();
        List<Payment> expectedPayments = new ArrayList<>();
        expectedPayments.add(payment1);
        expectedPayments.add(payment2);

        when(paymentRepository.findByLoan(loan)).thenReturn(expectedPayments);

        List<Payment> actualPayments = loanService.getAllPaymentByLoan(loan);

        assertEquals(expectedPayments, actualPayments);
        verify(paymentRepository, times(1)).findByLoan(loan);
    }


}

