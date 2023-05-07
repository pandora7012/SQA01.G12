package com.bank.bankingweb.service;

import static org.junit.jupiter.api.Assertions.*;

import com.bank.bankingweb.entity.Account;
import com.bank.bankingweb.entity.Customer;
import com.bank.bankingweb.repository.AccountRepository;
import com.bank.bankingweb.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerServiceImplementTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AccountRepository accountRepository;



    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomerByUsernameAndPassword() {
        String username = "testUser";
        String password = "testPassword";

        Customer expectedCustomer = new Customer();
        expectedCustomer.setUsername(username);
        expectedCustomer.setPassword(password);

        when(customerRepository.findCustomerByUsernameAndPassword(username, password))
                .thenReturn(expectedCustomer);

        Customer actualCustomer = customerRepository.findCustomerByUsernameAndPassword(username, password);

        assertEquals(expectedCustomer, actualCustomer);
        verify(customerRepository, times(1)).findCustomerByUsernameAndPassword(username, password);
    }

    @Test
    void testGetAccountByCustomer() {
        Customer customer = new Customer();

        Account expectedAccount = new Account();

        when(accountRepository.findAccountByCustomer(customer)).thenReturn(expectedAccount);

        Account actualAccount = accountRepository.findAccountByCustomer(customer);

        assertEquals(expectedAccount, actualAccount);
        verify(accountRepository, times(1)).findAccountByCustomer(customer);
    }

    @Test
    void testGetAllCustomer() {
        List<Customer> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new Customer());
        expectedCustomers.add(new Customer());

        when(customerRepository.findAll()).thenReturn(expectedCustomers);

        List<Customer> actualCustomers = customerRepository.findAll();

        assertEquals(expectedCustomers, actualCustomers);
        verify(customerRepository, times(1)).findAll();
    }
}
