package com.bank.bankingweb.service;

import com.bank.bankingweb.entity.Account;
import com.bank.bankingweb.entity.Customer;
import com.bank.bankingweb.repository.AccountRepository;
import com.bank.bankingweb.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RegisterServiceImplementTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private RegisterServiceImplement registerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomerByUsername() {
        String username = "testUser";

        Customer expectedCustomer = new Customer();
        expectedCustomer.setUsername(username);

        when(customerRepository.findCustomerByUsername(username)).thenReturn(expectedCustomer);

        Customer actualCustomer = registerService.getCustomerByUsername(username);

        assertEquals(expectedCustomer, actualCustomer);
        verify(customerRepository, times(1)).findCustomerByUsername(username);
    }

    @Test
    void testGetCustomerByCard() {
        String card = "1234567890";

        Customer expectedCustomer = new Customer();
        expectedCustomer.setCard(card);

        when(customerRepository.findCustomerByCard(card)).thenReturn(expectedCustomer);

        Customer actualCustomer = registerService.getCustomerByCard(card);

        assertEquals(expectedCustomer, actualCustomer);
        verify(customerRepository, times(1)).findCustomerByCard(card);
    }

    @Test
    void testCreateCustomer() {
        Customer customer = new Customer();

        when(customerRepository.save(customer)).thenReturn(customer);

        Customer createdCustomer = registerService.createCustomer(customer);

        assertEquals(customer, createdCustomer);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testCreateAccount() {
        Account account = new Account();

        when(accountRepository.save(account)).thenReturn(account);

        Account createdAccount = registerService.createAccount(account);

        assertEquals(account, createdAccount);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void testCountCustomer() {
        long expectedCount = 5;

        when(customerRepository.count()).thenReturn(expectedCount);

        long actualCount = registerService.countCustomer();

        assertEquals(expectedCount, actualCount);
        verify(customerRepository, times(1)).count();
    }
}
